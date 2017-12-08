package com.netcracker.unc.commands.vizualizer;


import com.netcracker.unc.commands.elevator.MoveElevatorCommand;
import com.netcracker.unc.commands.interfaces.IVizualizerCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.visualization.VisualizerConfig;
import com.netcracker.unc.visualization.elements.ElevatorPicture;
import com.netcracker.unc.visualization.elements.FloorPicture;

import java.util.List;

/**
 * Command which moves the elevator by one character
 */
public class MoveElevatorVisualizerCommand implements IVizualizerCommand {
    private IElevator elevator;
    private ElevatorPicture elevatorPicture;
    private FloorPicture floorPicture;
    private List<Floor> floors;

    public MoveElevatorVisualizerCommand(IElevator elevator, ElevatorPicture elevatorPicture, FloorPicture floorPicture, List<Floor> floors) {
        this.elevator = elevator;
        this.elevatorPicture = elevatorPicture;
        this.floorPicture = floorPicture;
        this.floors = floors;
    }

    @Override
    public void execute() {
        elevator.setInFloor(false);
        int nextPiece;
        int nextFloor;
        if (elevator.getState() == State.STOPPED)
            return;
        if (elevator.getState() == State.UP) {
            nextPiece = elevatorPicture.getYCoordinate() - 1;
            nextFloor = floorPicture.getyCoordinate() - VisualizerConfig.FLOOR_HEIGHT;
        } else {
            nextPiece = elevatorPicture.getYCoordinate() + 1;
            nextFloor = floorPicture.getyCoordinate() + VisualizerConfig.FLOOR_HEIGHT;
        }
        elevatorPicture.setYCoordinate(nextPiece);
        if (nextFloor == nextPiece) {
            elevator.setInFloor(true);
            new MoveElevatorCommand(elevator, floors).execute();
        }
    }
}
