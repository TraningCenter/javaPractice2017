package com.eugene.сontroller.actions;

import com.eugene.сontroller.actions.ButtonActions.ButtonTurnsOff;
import com.eugene.сontroller.actions.ButtonActions.ButtonTurnsOn;
import com.eugene.сontroller.actions.LiftActions.LiftMove;
import com.eugene.сontroller.actions.LiftActions.LiftStop;
import com.eugene.сontroller.actions.PassengerActions.PassengerEnterToLift;
import com.eugene.сontroller.actions.PassengerActions.PassengerExitFromLift;
import com.eugene.сontroller.Entities.*;
import com.eugene.сontroller.Listeners.ButtonListener;
import com.eugene.сontroller.Listeners.LiftListener;
import com.eugene.сontroller.Listeners.PassengerListener;
import com.eugene.сontroller.Snapshot;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for processing all units actions
 * alternately treats all events on the principle of the pattern "team"
 */
public class ActionsHandler implements PassengerListener, ButtonListener, LiftListener {

    private House house;
    private List<Snapshot> snapshots;
    private List<Passenger> waitingPassengers;

    public ActionsHandler(House house, List<Snapshot> snapshots) {
        this.house = house;
        this.snapshots = snapshots;
        waitingPassengers = new LinkedList<>();
    }

    private void addHouseState() {
        snapshots.add(new Snapshot(house));
    }

    public void startActions() {
        //add initial house state
        addHouseState();
        for (Passenger p : house.getPassengers()) {
            //all passengers are waiting for lifts
            waitingPassengers.add(p);
            //all passengers call lifts
            Button b = passengerFindNeededButton(p.getStartFloor(), p.getDirection());
            Action a = new ButtonTurnsOn(b);
            a.execute();
        }
        addHouseState();
    }

    public void handleActions() {
        do {
            //lifts continue their actions
            liftsContinue();
            //send lifts to the nearest passengers
            if (checkPassengersWaiting()) {
                sendEmptyLift();
            }
        } while (!checkAllPassengersArrived());
    }

    private void liftsContinue() {
        for (Lift l : house.getLifts()) {
            //if lift doesn't have to go anywhere, it is waiting next task
            if (l.getFloors().isEmpty()) {
                l.stop();
                continue;
            }
            //if we need to this floor, stop
            if (l.getFloors().contains(l.getFloor())) {
                Action a = new LiftStop(l, this);
                a.execute();
            }
            //if there is a person here who needs the same direction and we can take it, we take
            pickupPassenger(l);
            //if lift is still somewhere you need-we are moving
            if (!l.getFloors().isEmpty()) {
                Action a = new LiftMove(l);
                a.execute();
                addHouseState();
            }
        }
    }

    private void pickupPassenger(Lift lift) {
        try {
            for (Passenger p : waitingPassengers) {
                if (p.getStartFloor() == lift.getFloor()) {
                    if (!checkOverload(lift))
                        continue;
                    if (!lift.getPassengers().isEmpty() && lift.getDirection() != p.getDirection())
                        continue;
                    Action a = new LiftStop(lift, this);
                    a.execute();
                }
            }
        } catch (Exception e) {
        }
    }

    private void sendEmptyLift() {
        List<Lift> emptyLifts = findEmptyLifts();
        for (int i = 0; i < emptyLifts.size(); i++) {
            int distance = house.getNumFloors();
            int num = -1;
            for (int k = 0; k < waitingPassengers.size(); k++) {
                int dist = Math.abs(emptyLifts.get(i).getFloor() - waitingPassengers.get(k).getFloor());
                if (dist < distance) {
                    num = k;
                    distance = dist;
                }
            }
            if (num != -1) {
                emptyLifts.get(i).getFloors().add(waitingPassengers.get(num).getStartFloor());
                waitingPassengers.remove(waitingPassengers.get(num));
                emptyLifts.remove(i);
                i--;
            }
        }
    }

    @Override
    public void liftStopped(Lift lift) {
        if (lift.getFloors().isEmpty())
            return;
        if (lift.getFloors().contains(lift.getFloor()))
            lift.getFloors().remove((Integer) lift.getFloor());
        List<Passenger> waitingHerePassengers = findWaitingPassengers(lift.getFloor(), lift.getDirection());
        if (lift.getPassengers().isEmpty() && waitingHerePassengers.isEmpty())
            return;
        //open the doors
        openDoors(lift);
        //turn off button
        if (!waitingHerePassengers.isEmpty()) {
            int copyDirection = lift.getDirection();
            if (copyDirection == 0)
                copyDirection = waitingHerePassengers.get(0).getDirection();
            buttonTurnOff(lift.getFloor(), copyDirection);
        }
        //if someone needs to go out, go out
        passengerExitFromLift(lift);
        //if someone needs to enter, they enter
        passengerEnterToLift(lift, waitingHerePassengers);
        //close the doors
        closeDoors(lift);
    }

    private void passengerEnterToLift(Lift lift, List<Passenger> waitingHerePassengers) {
        for (int i = 0; i < waitingHerePassengers.size(); i++) {
            Passenger p = waitingHerePassengers.get(i);
            if (checkOverload(lift)) {
                Action a = new PassengerEnterToLift(lift, p);
                a.execute();
                //passenger is no longer waiting
                if (waitingPassengers.contains(p))
                    waitingPassengers.remove(p);
                addHouseState();
            }
            //otherwise please wait for the next lift
            else if (!waitingPassengers.contains(p))
                waitingPassengers.add(p);
        }
    }

    private void passengerExitFromLift(Lift lift) {
        for (int i = 0; i < lift.getPassengers().size(); i++) {
            Passenger p = lift.getPassengers().get(i);
            if (p.getEndFloor() == lift.getFloor()) {
                //passenger go out from lift
                Action a = new PassengerExitFromLift(p);
                a.execute();
                lift.getPassengers().remove(p);
                addHouseState();
                i--;
            }
        }
    }

    private void openDoors(Lift lift) {
        lift.setDoorsActions(1);
        addHouseState();
    }

    private void closeDoors(Lift lift) {
        lift.setDoorsActions(-1);
        addHouseState();
        lift.setDoorsActions(0);
        addHouseState();
    }

    private void buttonTurnOff(int floor, int direction) {
        try {
            Button button = house.getButtons().stream()
                    .filter(b -> b.getFloor() == floor && b.getDirection() == direction).findFirst().get();
            ButtonTurnsOff a = new ButtonTurnsOff(button);
            a.execute();
            addHouseState();
        } catch (Exception e) {
        }
    }

    private List<Passenger> findWaitingPassengers(int floor, int liftDirection) {
        List<Passenger> result = house.getPassengers().stream()
                .filter(p -> p.getFloor() == floor && !p.getInLift() && p.getFloor() != p.getEndFloor()
                        && (liftDirection == 0 || p.getDirection() == liftDirection))
                .collect(Collectors.toList());
        if (liftDirection != 0)
            return result;
        //case when lift is standing, and it is called simultaneously on the descent and on the rise
        boolean flag = false;
        for (Passenger p : result) {
            //if passengers want in different directions...
            if (p.getDirection() != result.get(0).getDirection()) {
                flag = true;
                break;
            }
        }
        //..first omit, and only then we raise
        if (flag)
            result = result.stream().filter(p -> p.getDirection() == -1).collect(Collectors.toList());
        return result;
    }

    private List<Lift> findEmptyLifts() {
        return house.getLifts().stream()
                .filter(l -> l.getWorking() && l.getPassengers().isEmpty() && l.getFloors().isEmpty())
                .collect(Collectors.toList());
    }

    private Button passengerFindNeededButton(int floor, int direction) {
        return house.getButtons().stream()
                .filter(b -> (b.getFloor() == floor && b.getDirection() == direction))
                .findFirst().get();
    }

    private boolean checkAllPassengersArrived() {
        for (Passenger p : house.getPassengers())
            if (p.getFloor() != p.getEndFloor() || p.getInLift())
                return false;
        return true;
    }

    private boolean checkPassengersWaiting() {
        return !waitingPassengers.isEmpty();
    }

    private boolean checkOverload(Lift lift) {
        return lift.getPassengers().size() < lift.getMaxWeight();
    }
}
