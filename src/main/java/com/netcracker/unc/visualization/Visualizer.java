package com.netcracker.unc.visualization;

import com.netcracker.unc.logic.Building;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.visualization.elements.ElevatorPicture;
import com.netcracker.unc.visualization.elements.FloorPicture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.netcracker.unc.visualization.VisualizerConfig.*;

/**
 * Class which visualizes all building
 */
public class Visualizer {

    private List<ElevatorPicture> elevatorPictureList;
    private List<FloorPicture> floorPicturesList;

    public Visualizer() {
        elevatorPictureList = new ArrayList<>();
        floorPicturesList = new ArrayList<>();
    }
    
    public void setConfiguration(Building building) {
        List<Floor> floors = building.getFloors();
        List<IElevator> elevators = building.getElevators();
        for (IElevator elevator : elevators) {
            Floor currentFloor = elevator.getCurrentFloor();
            int yCoordinate = (floors.size() - currentFloor.getId()) * VisualizerConfig.ELEVATOR_HEIGHT + 1;
            elevatorPictureList.add(new ElevatorPicture(elevator.getId() + 1, yCoordinate, 0, false));
        }
        Floor floor;
        int yCord = 1;
        for (int i = floors.size() - 1; i >= 0; i--) {
            floor = floors.get(i);
            floorPicturesList.add(new FloorPicture(floor.getId(), yCord, floor.getPassengers().size(), floor.isPushedButtonUp(), floor.isPushedButtonDown()));
            yCord += VisualizerConfig.FLOOR_HEIGHT;
        }
    }


    public void visualize(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String roof = drawRoof();
        String floors = drawFloors();
        System.out.println(roof + floors);
    }

    private String drawFloors() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < floorPicturesList.size(); i++) {
            for (int line = 1 + FLOOR_HEIGHT * i; line <= FLOOR_HEIGHT * (i + 1); line++) {
                builder.append("|");
                for (ElevatorPicture anElevatorPictureList : elevatorPictureList) {
                    builder
                        .append(printNTimes(" ", FLOOR_WIDTH))
                        .append(anElevatorPictureList.draw(line));
                }
                builder.append(printNTimes(" ", FLOOR_WIDTH)).append(printNTimes("#", SEPARATOR_WIDTH));
                builder.append(floorPicturesList.get(i).draw(line));
                builder.append("|\n");
            }
            builder.append(printNTimes("-", (FLOOR_WIDTH + 4 + ELEVATOR_WIDTH) * elevatorPictureList.size() + 2 * FLOOR_WIDTH + SEPARATOR_WIDTH + 2));
            builder.append("\n");
        }
        return builder.toString();
    }

    private String drawRoof() {
        StringBuilder builder = new StringBuilder();
        builder.append(printNTimes(" ", ROOF_HEIGHT + 1));
        int bottomWidth = (FLOOR_WIDTH + 4 + ELEVATOR_WIDTH) * elevatorPictureList.size() + 2 * FLOOR_WIDTH + SEPARATOR_WIDTH + 2;
        int topWidth = bottomWidth - 2 * ROOF_HEIGHT - 1;
        builder.append(printNTimes("-", topWidth));
        builder.append("\n");
        for (int i = ROOF_HEIGHT, line = 0; i > 0; i--, line++) {
            builder.append(
                printNTimes(" ", i))
                .append('/')
                .append(printNTimes(" ", topWidth + line * 2))
                .append("\\\n");
        }
        builder.append(printNTimes("-", bottomWidth));
        builder.append('\n');
        return builder.toString();
    }

    private static String printNTimes(String string, int n) {
        return String.join("", Collections.nCopies(n, string));
    }

    public List<ElevatorPicture> getElevatorPictureList() {
        return elevatorPictureList;
    }

    public List<FloorPicture> getFloorPicturesList() {
        return floorPicturesList;
    }

    public FloorPicture getFloorPictureById(int id) {
        FloorPicture floorPicture = null;
        for (FloorPicture fp : floorPicturesList) {
            if (fp.getId() == id)
                floorPicture = fp;
        }
        return floorPicture;
    }
}
