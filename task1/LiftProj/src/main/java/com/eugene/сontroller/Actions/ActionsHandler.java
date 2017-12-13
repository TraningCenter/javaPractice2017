package com.eugene.сontroller.Actions;

import com.eugene.сontroller.Actions.ButtonActions.ButtonAction;
import com.eugene.сontroller.Actions.ButtonActions.ButtonCallLift;
import com.eugene.сontroller.Actions.ButtonActions.ButtonTurnsOff;
import com.eugene.сontroller.Actions.LiftActions.*;
import com.eugene.сontroller.Actions.PassengerActions.*;
import com.eugene.сontroller.Entities.*;
import com.eugene.сontroller.Listeners.ButtonListener;
import com.eugene.сontroller.Listeners.LiftListener;
import com.eugene.сontroller.Listeners.PassengerListener;
import com.eugene.сontroller.Snapshot;

import java.util.ArrayList;
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
    private List<Action> commands;
    private List<Action> commandsCopy;//

    public ActionsHandler(House house, List<Snapshot> snapshots) {
        this.house = house;
        this.snapshots = snapshots;
        this.commands = new LinkedList<>();
        this.commandsCopy = new ArrayList<>();
    }

    private void addHouseState() {
        Action lastCommand = null;
        if (commands != null && !commands.isEmpty())
            lastCommand = commands.get(commands.size() - 1);
        snapshots.add(new Snapshot(house, lastCommand));
    }

    private void addCommand(Action action) {
        commands.add(action);
    }

    private void addPriorityCommand(Action action) {
        commands.add(0, action);
    }

    public void startActions() {
        //добавляем начальное состояние дома
        addHouseState();
        //all persons activates buttons
        for (Passenger p : house.getPassengers()) {
            Button button = passengerFindNeededButton(p.getStartFloor(), p.getDirection());
            PassengerAction action = new PassengerPressButton(button, this);
            addCommand(action);
        }
    }

    public void handleActions() {
        while (!commands.isEmpty()) {
            commandsCopy.add(commands.get(0));
            commands.remove(0).execute();
            addHouseState();
        }
    }

    @Override
    public void wasPressedButton(Button button) {
        ButtonAction action = new ButtonCallLift(button, this);
        addCommand(action);
    }

    @Override
    public void wasCalledLift(int floor, int direction) {
        Lift lift = findLiftOnSameFloor(floor, direction);
        LiftAction action;
        //if we have lifts on the same floor
        if (lift != null) {
            action = new LiftStop(lift, this);
        }
        //lifts on other floor
        else {
            lift = findNearestLift(floor, direction);
            action = new LiftMove(lift, this);
        }
        lift.getFloors().add(floor);
        addCommand(action);
    }

    @Override
    public void liftMoved(Lift lift) {
        LiftAction action;
        if (lift.getFloors().contains(lift.getFloor())) {
            action = new LiftStop(lift, this);
            addPriorityCommand(action);
        } else {
            action = new LiftMove(lift, this);
            addCommand(action);
        }
    }

    @Override
    public void liftStopped(Lift lift) {
        if (lift.getFloors().isEmpty())
            return;
        if (lift.getFloors().contains(lift.getFloor())) {
            LiftAction action = new LiftArrive(lift, this);
            addPriorityCommand(action);
        }
    }

    @Override
    public void liftArrived(Lift lift) {
        lift.setDoorsActions(1);
        LiftAction action = new LiftOpenDoors(lift, this);
        addPriorityCommand(action);
    }

    @Override
    public void liftOpenedDoors(Lift lift) {

        List<Passenger> waitingPassengers;
        if (lift.getPassengers().isEmpty())
            waitingPassengers = findWaitingPassenger(lift.getFloor(), 0);
        else
            waitingPassengers = findWaitingPassenger(lift.getFloor(), lift.getDirection());
        for (Passenger p : waitingPassengers) {
            PassengerAction a = new PassengerEnterToLift(lift, p, this);
            addPriorityCommand(a);
        }

        List<Passenger> arrivedPassengers = findArrivedPassenger(lift);
        for (Passenger p : arrivedPassengers) {
            PassengerAction a = new PassengerExitFromLift(lift, p, this);
            addPriorityCommand(a);
        }

        Button button = liftFindNeededButton(lift);
        if (button != null && button.getOn()) {
            ButtonAction action = new ButtonTurnsOff(button);
            addPriorityCommand(action);
        }
    }

    @Override
    public void passengerEnteredToLift(Lift lift, Passenger passenger) {
        //закрываем двери
        lift.setDoorsActions(-1);
        LiftAction action = new LiftCloseDoors(lift, this);
        addPriorityCommand(action);
    }

    @Override
    public void liftClosedDoors(Lift lift) {
        lift.setDoorsActions(0);
        Action action;
        if (lift.getPassengers().isEmpty())
            action = new LiftStop(lift, this);
        else {
            List<Passenger> passengers = lift.getNewPassengers();
            for (Passenger p : passengers)
                lift.getFloors().add(p.getEndFloor());
            action = new LiftMove(lift, this);
        }
        addCommand(action);
    }

    @Override
    public void passengerExitedFromLift(Lift lift, Passenger passenger) {
        lift.getPassengers().remove(passenger);
        lift.setDoorsActions(-1);
        LiftAction action = new LiftCloseDoors(lift, this);
        addPriorityCommand(action);
    }

    private Button passengerFindNeededButton(int floor, int direction) {
        return house.getButtons().stream()
                .filter(b -> (b.getFloor() == floor && b.getDirection() == direction))
                .findFirst().get();
    }

    private Button liftFindNeededButton(Lift lift) {
        if (lift.getDirection() == 0 || lift.getPassengers().isEmpty())
            return liftFindNeededButtonWithUnknownDirection(lift.getFloor());
        if (!lift.getPassengers().isEmpty()) {
            int floor = lift.getFloor();
            try {
                Passenger passenger = house.getPassengers().stream()
                        .filter(p -> p.getStartFloor() == floor)
                        .findFirst().get();
                return house.getButtons().stream()
                        .filter(b -> (b.getFloor() == floor && b.getDirection() == passenger.getDirection()))
                        .findFirst().get();
            } catch (Exception e) {
            }
        }
        return null;
    }

    private Button liftFindNeededButtonWithUnknownDirection(int floor) {
        Button button = null;
        for (Passenger p : house.getPassengers()) {
            if (p.getStartFloor() == floor)
                button = house.getButtons().stream()
                        .filter(b -> (b.getFloor() == floor && b.getDirection() == p.getDirection()))
                        .findFirst().get();
        }
        return button;
    }

    private List<Passenger> findWaitingPassenger(int floor, int direction) {
        List<Passenger> passengers;
        if (direction != 0)
            passengers = house.getPassengers().stream()
                    .filter(p -> (p.getStartFloor() == floor && (p.getDirection() == direction)))
                    .collect(Collectors.toList());
        else {
            passengers = house.getPassengers().stream()
                    .filter(p -> (p.getStartFloor() == floor && (p.getDirection() == -1)))
                    .collect(Collectors.toList());
            if (passengers.isEmpty())
                passengers = house.getPassengers().stream()
                        .filter(p -> (p.getStartFloor() == floor && (p.getDirection() == 1)))
                        .collect(Collectors.toList());
        }
        return passengers;
    }

    private List<Passenger> findArrivedPassenger(Lift lift) {
        return lift.getPassengers().stream()
                .filter(p -> p.getEndFloor() == lift.getFloor())
                .collect(Collectors.toList());
    }

    private Lift findLiftOnSameFloor(int floor, int direction) {
        for (Lift l : house.getLifts()) {
            if (l.getFloor() != floor)
                continue;
            if (!l.getMoving())
                return l;
            else {
                if (l.getOverload()) //if lift is overload, drive past
                    continue;
                if (l.getDirection() == direction)
                    return l;

            }
        }
        return null;
    }

    private Lift findNearestLift(int floor, int direction) {
        //find all moving and stopping lifts
        List<Lift> movingLifts = new ArrayList<>();
        List<Lift> stoppingLifts = new ArrayList<>();
        for (Lift l : house.getLifts()) {
            if (!l.getMoving()) {
                stoppingLifts.add(l);
                continue;
            }
            if (l.getOverload()) //if lift is overload, drive past
                continue;
            if (l.getDirection() == direction) {
                //lift is almost overloaded. lift doesn't take passengers
                if (l.getPassengers().size() * 80 + 80 > l.getMaxWeight())
                    continue;
                //if the directions and the floor match, lift take passenger
                if ((direction == 1 && l.getFloor() <= floor) || (direction == -1 && l.getFloor() >= floor))
                    movingLifts.add(l);
            }
        }
        //select nearest lift
        Lift nearestLift;
        List<Lift> neededLifts;
        if (!movingLifts.isEmpty()) {
            neededLifts = movingLifts;
        } else {
            neededLifts = stoppingLifts;
        }
        int distance = Math.abs(neededLifts.get(0).getFloor() - floor);
        nearestLift = neededLifts.get(0);
        for (Lift l : neededLifts) {
            if (Math.abs(l.getFloor() - floor) <= distance)
                nearestLift = l;
        }
        return nearestLift;
    }
}
