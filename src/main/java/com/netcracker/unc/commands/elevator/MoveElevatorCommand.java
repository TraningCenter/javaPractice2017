package com.netcracker.unc.commands.elevator;


import com.netcracker.unc.commands.interfaces.IElevatorCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.interfaces.IElevator;

public class MoveElevatorCommand implements IElevatorCommand {
    private IElevator elevator;

    public MoveElevatorCommand(IElevator elevator) {
        this.elevator = elevator;
    }

    public void execute() {
        Floor floor = elevator.getCurrentFloor();
        Floor nextFloor;
        if (elevator.getState() == State.STOPPED)
            return;
        if (elevator.getState() == State.UP)
            nextFloor = elevator.getAvailableFloors().get(floor.getId());
        else
            nextFloor = elevator.getAvailableFloors().get(floor.getId() - 2);
        if (elevator.getAvailableFloors().contains(nextFloor))
            elevator.setCurrentFloor(nextFloor);
        if (elevator.getNextDestinationFloor() == elevator.getCurrentFloor())
            new StopElevatorCommand(elevator).execute();
    }
}
