package com.netcracker.unc.commands.building;


import com.netcracker.unc.commands.interfaces.IBuildingCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.interfaces.IElevator;

import java.util.List;

public class CallElevatorBuildingCommand implements IBuildingCommand {
    private List<IElevator> elevators;
    private Floor floor;
    private State direction;

    public CallElevatorBuildingCommand(List<IElevator> elevators, Floor floor, State direction) {
        this.elevators = elevators;
        this.floor = floor;
        this.direction = direction;
    }

    public void execute() {
        IElevator nearestElevator = null;
        int distanceNE; //nearestElevator
        int distanceE; //elevator
        // поиск близжайшего лифта или который уже едет (в ту же сторону)
        for (IElevator elevator : elevators) {
            // если этаж для лифта недоступен
            if (!elevator.getAvailableFloors().contains(floor))
                continue;
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
        nearestElevator.addFloorInQueue(floor);
    }
}
