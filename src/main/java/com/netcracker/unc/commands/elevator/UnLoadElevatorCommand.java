package com.netcracker.unc.commands.elevator;


import com.netcracker.unc.commands.interfaces.IElevatorCommand;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;

import java.util.ArrayList;
import java.util.List;

public class UnLoadElevatorCommand implements IElevatorCommand {
    private IElevator elevator;

    public UnLoadElevatorCommand(IElevator elevator) {
        this.elevator = elevator;
    }

    public void execute() {
        List<IPassenger> list = new ArrayList<IPassenger>(elevator.getPassengers());
        for (IPassenger passenger: list) {
            if (passenger.getDestinationFloor() == elevator.getCurrentFloor()) {
                elevator.deletePassenger(passenger);
            }
        }
    }
}
