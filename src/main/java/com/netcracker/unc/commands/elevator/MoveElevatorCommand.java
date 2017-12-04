package com.netcracker.unc.commands.elevator;


import com.netcracker.unc.commands.interfaces.IElevatorCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.interfaces.IElevator;

import java.util.List;

public class MoveElevatorCommand implements IElevatorCommand {
    private IElevator elevator;
    private List<Floor> floors;

    public MoveElevatorCommand(IElevator elevator, List<Floor> floors) {
        this.elevator = elevator;
        this.floors = floors;
    }

    public void execute() {
        elevator.setLoaded(false);
        elevator.setUnLoaded(false);
        Floor floor = elevator.getCurrentFloor();
        Floor nextFloor;
        if (elevator.getState() == State.STOPPED)
            return;
        if (elevator.getState() == State.UP)
            nextFloor = floors.get(floor.getId());
        else
            nextFloor = floors.get(floor.getId() - 2);
        if (elevator.getAvailableFloors().contains(nextFloor))
            elevator.setCurrentFloor(nextFloor);
        if (elevator.getNextDestinationFloor() == elevator.getCurrentFloor())
            elevator.setState(State.STOPPED);
    }
}
