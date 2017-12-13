package com.eugene.visualizer;

import com.eugene.сontroller.Entities.Button;
import com.eugene.сontroller.Entities.House;
import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Entities.Passenger;
import com.eugene.сontroller.Snapshot;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * Class for console visualization
 */
public class DefaultVisualizer extends Visualizer {

    private final int entityParts = 3;
    private final int pauseTime = 500;
    private House house;
    private Console console;

    public DefaultVisualizer(List<Snapshot> snapshots) {
        super(snapshots);
        console = System.console();
    }

    @Override
    public void paint() {
        clearConsole();
        paintStartInfo();
        for (Snapshot s : snapshots) {
            this.house = s.getHouse();
            clearConsole();
            paintInfo(s.getDescription());
            paintHouse();
            paintPause();
        }
    }

    private void paintStartInfo() {
        console.printf("3...");
        paintPause();
        console.printf("2...");
        paintPause();
        console.printf("1...");
        paintPause();
        console.printf("GO! \n");
        paintPause();
        clearConsole();
    }

    private void paintInfo(String info) {
        if (info == null)
            info = "";
        console.printf(info + "\n");
    }

    private void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void paintHouse() {
        paintGround();
        for (int i = 0; i < house.getNumFloors(); i++) {
            paintFloor(indexToFloorNum(i));
            paintGround();
        }
        console.printf("\n");
    }

    private void paintGround() {
        for (int i = 0; i < 10; i++) {
            console.printf("___");
        }
        console.printf("\n");
    }

    private void paintFloor(int floor) {
        for (int part = 0; part < entityParts; part++) {
            for (int i = 0; i < house.getLifts().size(); i++) {
                console.printf(paintShaft());
                console.printf(drawLift(floor, i, part));
                console.printf(paintShaft());
                console.printf(paintWall());
            }
            console.printf(paintButtons(floor, part));
            console.printf("\n");
        }
    }

    private String paintShaft() {
        return "|";
    }

    private String paintWall() {
        return " ";
    }

    private String paintButtons(int floor, int part) {
        String tmp = "";
        switch (part) {
            case 0:
                tmp = drawButtonOnFloor(floor, 1);
                break;
            case 1:
                tmp = " " + drawPassengersOnFloor(floor);
                break;
            case 2:
                tmp = drawButtonOnFloor(floor, -1);
                break;
            default:
                tmp = " ";
                break;
        }
        return tmp;
    }

    private String drawButtonOnFloor(int floor, int direction) {
        if (floor == 1 && direction == -1)
            return "";
        if (floor == house.getNumFloors() && direction == 1)
            return "";
        Button button = house.getButtons().stream()
                .filter(b -> (b.getFloor() == floor && b.getDirection() == direction))
                .findFirst().get();
        return (button.getOn() ? "!" : "0");
    }

    private String drawPassengersOnFloor(int floor) {
        String result = "";
        String passengerSymbol = "P";
        List<Passenger> passengers = house.getPassengers().stream()
                .filter(p -> (p.getFloor() == floor && !p.getInLift()))
                .collect(Collectors.toList());
        for (Passenger p : passengers) {
            String direction = "";
            if (p.getFloor() == p.getStartFloor())
                direction = (p.getDirection() == -1 ? "down;" : "up;");
            result = passengerSymbol + (house.getPassengers().indexOf(p) + 1) + direction;
        }
        return result;
    }

    private String drawLift(int floor, int shaft, int part) {
        String result = "";
        Lift lift;
        try {
            lift = house.getLifts().stream()
                    .filter(l -> (l.getFloor() == floor && house.getLifts().indexOf(l) == shaft))
                    .findFirst().get();
        } catch (Exception e) {
            return "     ";
        }
        switch (part) {
            case (0):
                result = " " + (house.getLifts().indexOf(lift) + 1) + "-- ";
                break;
            case (1):
                char liftSymbol = 'L';
                switch (lift.getDoorsActions()) {
                    case 1:
                        result = " <" + liftSymbol + "> ";
                        break;
                    case -1:
                        result = " >" + liftSymbol + "< ";
                        break;
                    case 0:
                    default:
                        result = "  " + liftSymbol + "  ";
                        break;
                }
                break;
            case (2):
                result = " --- ";
                break;
        }
        return result;
    }

    private int indexToFloorNum(int index) {
        return house.getNumFloors() - index;
    }

    private void paintPause() {
        try {
            sleep(pauseTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
