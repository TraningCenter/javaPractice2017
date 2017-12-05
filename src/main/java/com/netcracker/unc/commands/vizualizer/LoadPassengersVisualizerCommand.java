package com.netcracker.unc.commands.vizualizer;


import com.netcracker.unc.commands.interfaces.IVizualizerCommand;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.visualization.elements.ElevatorPicture;
import com.netcracker.unc.visualization.elements.FloorPicture;

/**
 * Command which loads passengers in a picture
 */
public class LoadPassengersVisualizerCommand implements IVizualizerCommand{

    private IElevator elevator;
    private ElevatorPicture elevatorPicture;
    private FloorPicture floorPicture;

    public LoadPassengersVisualizerCommand(IElevator elevator, ElevatorPicture elevatorPicture, FloorPicture floorPicture) {
        this.elevator = elevator;
        this.elevatorPicture = elevatorPicture;
        this.floorPicture = floorPicture;
    }

    @Override
    public void execute() {
        elevatorPicture.setCountOfPassengers(elevator.getPassengers().size());
        floorPicture.update(elevator.getCurrentFloor());
    }
}
