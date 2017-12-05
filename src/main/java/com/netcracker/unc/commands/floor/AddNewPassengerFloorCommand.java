package com.netcracker.unc.commands.floor;


import com.netcracker.unc.commands.interfaces.IFloorCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.interfaces.IPassenger;

/**
 * Command which adds new passengers to the floor
 */
public class AddNewPassengerFloorCommand  implements IFloorCommand{
    private IPassenger passenger;
    private Floor floor;

    public AddNewPassengerFloorCommand(IPassenger passenger, Floor floor) {
        this.passenger = passenger;
        this.floor = floor;
    }

    public void execute() {
        floor.addPassenger(passenger);
    }
}
