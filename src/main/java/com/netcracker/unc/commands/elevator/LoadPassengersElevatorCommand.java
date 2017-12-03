package com.netcracker.unc.commands.elevator;


import com.netcracker.unc.commands.passenger.ChooseFloorPassengerCommand;
import com.netcracker.unc.commands.interfaces.IElevatorCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadPassengersElevatorCommand implements IElevatorCommand {

    private IElevator elevator;
    private Floor floor;

    public LoadPassengersElevatorCommand(IElevator elevator, Floor floor) {
        this.elevator = elevator;
        this.floor = floor;
    }

    public void execute() {
        List<IPassenger> passengers = new ArrayList<IPassenger>(floor.getPassengers());
        Random rnd = new Random(System.currentTimeMillis());
        int prob;
        for (IPassenger passenger : passengers) {
            prob = rnd.nextInt(101);
            if (prob > passenger.getProbabilityOfChoice()) {
                if (elevator.addPassenger(passenger)) {
                    floor.deletePassenger(passenger);
                    new ChooseFloorPassengerCommand(elevator, passenger.getDestinationFloor()).execute();
                    continue;
                }
            }
            if (passenger.getDirection() == State.DOWN)
                floor.setPushedButtonDown(true);
            else
                floor.setPushedButtonUp(true);
        }
    }
}
