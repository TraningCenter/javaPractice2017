package com.netcracker.unc.commands.building;


import com.netcracker.unc.commands.interfaces.IBuildingCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.WaitingCall;
import com.netcracker.unc.logic.interfaces.IElevator;

import java.util.List;
import java.util.Queue;

public class CallElevatorBuildingCommand implements IBuildingCommand {
    private List<IElevator> elevators;
    private Floor floor;
    private Floor destFloor;
    private State direction;
    private Queue<WaitingCall> waiting;

    public CallElevatorBuildingCommand(List<IElevator> elevators, Floor floor, Floor destFloor, State direction, Queue<WaitingCall> waiting) {
        this.elevators = elevators;
        this.floor = floor;
        this.destFloor = destFloor;
        this.direction = direction;
        this.waiting = waiting;
    }

    public void execute() {
        IElevator nearestElevator = null;
        int distanceNE; //nearestElevator
        int distanceE; //elevator
        boolean allUnavailable = true;

        // поиск близжайшего лифта или который уже едет (в ту же сторону)
        for (IElevator elevator : elevators) {
            // если этаж для лифта недоступен
            if (!elevator.getAvailableFloors().contains(floor) || !elevator.getAvailableFloors().contains(destFloor))
                continue;
            else
                allUnavailable = false;
            // если лифт едет в противоположное направление
            if (elevator.getState() != State.STOPPED && elevator.getState() != direction)
                continue;
            if (nearestElevator == null) {
                nearestElevator = elevator;
                continue;
            }
            distanceNE = Math.abs(nearestElevator.getCurrentFloor().compareTo(floor));
            distanceE = Math.abs(elevator.getCurrentFloor().compareTo(floor));
            if (distanceE < distanceNE)
                nearestElevator = elevator;
        }
        if (nearestElevator == null) {
            if (!allUnavailable)
                waiting.add(new WaitingCall(floor, destFloor));
        } else {
            // если на этот этаж лифт уже вызвали
            if (direction == State.DOWN && floor.isPushedButtonDown())
                return;
            else if (direction == State.UP && floor.isPushedButtonUp())
                return;
            nearestElevator.addFloorInQueue(floor);
            if (direction == State.UP)
                floor.setPushedButtonUp(true);
            else
                floor.setPushedButtonDown(true);
        }
    }
}
