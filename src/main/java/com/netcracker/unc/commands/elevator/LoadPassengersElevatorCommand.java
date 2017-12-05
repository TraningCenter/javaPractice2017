package com.netcracker.unc.commands.elevator;


import com.netcracker.unc.commands.interfaces.IElevatorCommand;
import com.netcracker.unc.commands.passenger.ChooseFloorPassengerCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.WaitingCall;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Command which loads passengers in right direction
 */
public class LoadPassengersElevatorCommand implements IElevatorCommand {

    private IElevator elevator;
    private Queue<WaitingCall> waiting;

    public LoadPassengersElevatorCommand(IElevator elevator, Queue<WaitingCall> waiting) {
        this.elevator = elevator;
        this.waiting = waiting;
    }

    public void execute() {
        Floor floor = elevator.getCurrentFloor();
        List<IPassenger> passengers = new ArrayList<IPassenger>(floor.getPassengers());
        Random rnd = new Random(System.currentTimeMillis());
        int prob;
        for (IPassenger passenger : passengers) {
            prob = rnd.nextInt(101);
            if (elevator.getState() == passenger.getDirection() || elevator.getState() == State.STOPPED) {
                if (!elevator.getAvailableFloors().contains(passenger.getDestinationFloor()) || prob <= passenger.getProbabilityOfChoice() || !elevator.addPassenger(passenger))
                    waiting.add(new WaitingCall(floor, passenger.getDestinationFloor()));
                else {
                    floor.deletePassenger(passenger);
                    new ChooseFloorPassengerCommand(elevator, passenger.getDestinationFloor()).execute();
                }
            }
        }
        // Отключение кнопок на этаже
        if (elevator.getState() == State.STOPPED) {
            floor.setPushedButtonUp(false);
            floor.setPushedButtonDown(false);
        } else {
            if (elevator.getState() == State.DOWN && floor.isPushedButtonDown())
                floor.setPushedButtonDown(false);
            else if (elevator.getState() == State.UP && floor.isPushedButtonUp())
                floor.setPushedButtonUp(false);
        }
        if (elevator.getCurrentFloor() == elevator.getNextDestinationFloor())
            elevator.deleteFloorFromQueue();
        if (!elevator.getFloorsToVisit().isEmpty())
            elevator.setLoaded(true);
    }
}
