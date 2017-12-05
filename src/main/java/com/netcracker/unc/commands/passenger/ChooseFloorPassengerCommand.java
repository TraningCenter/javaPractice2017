package com.netcracker.unc.commands.passenger;

import com.netcracker.unc.commands.interfaces.IPassengerCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.interfaces.IElevator;

/**
 * Command which clicks on the floor inside the elevator
 */
public class ChooseFloorPassengerCommand implements IPassengerCommand {
    private IElevator elevator;
    private Floor floor;

    public ChooseFloorPassengerCommand(IElevator elevator, Floor floor) {
        this.elevator = elevator;
        this.floor = floor;
    }

    public void execute() {
        elevator.addFloorInQueue(floor);
    }
}
