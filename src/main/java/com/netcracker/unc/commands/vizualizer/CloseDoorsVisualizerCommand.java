package com.netcracker.unc.commands.vizualizer;

import com.netcracker.unc.commands.interfaces.IVizualizerCommand;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.visualization.elements.ElevatorPicture;

public class CloseDoorsVisualizerCommand implements IVizualizerCommand{

    private IElevator elevator;
    private ElevatorPicture elevatorPicture;

    public CloseDoorsVisualizerCommand(IElevator elevator, ElevatorPicture elevatorPicture) {
        this.elevator = elevator;
        this.elevatorPicture = elevatorPicture;
    }

    @Override
    public void execute() {
        elevatorPicture.setOpened(false);
        elevator.setOpened(false);
    }
}
