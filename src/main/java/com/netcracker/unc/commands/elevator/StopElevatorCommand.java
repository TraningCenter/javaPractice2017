package com.netcracker.unc.commands.elevator;


import com.netcracker.unc.commands.interfaces.IElevatorCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.interfaces.IElevator;

public class StopElevatorCommand implements IElevatorCommand {
    private IElevator elevator;

    public StopElevatorCommand(IElevator elevator) {
        this.elevator = elevator;
    }

    public void execute() {
        Floor floor = elevator.getCurrentFloor();
        elevator.deleteFloorFromQueue();
        if (elevator.getState() == State.DOWN)
            floor.setPushedButtonDown(false);
        else if (elevator.getState() == State.UP)
            floor.setPushedButtonUp(false);
        if (elevator.getFloorsToVisit().isEmpty())
            elevator.setState(State.STOPPED);
    }
}
