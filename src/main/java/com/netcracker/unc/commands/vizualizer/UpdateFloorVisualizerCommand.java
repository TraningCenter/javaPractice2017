package com.netcracker.unc.commands.vizualizer;


import com.netcracker.unc.commands.interfaces.IVizualizerCommand;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.visualization.elements.FloorPicture;

/**
 * Command which updates floor information
 */
public class UpdateFloorVisualizerCommand implements IVizualizerCommand {

    private FloorPicture floorPicture;
    private Floor floor;

    public UpdateFloorVisualizerCommand(Floor floor, FloorPicture floorPicture) {
        this.floorPicture = floorPicture;
        this.floor = floor;
    }

    @Override
    public void execute() {
        floorPicture.update(floor);
    }
}
