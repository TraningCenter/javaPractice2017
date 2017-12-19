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
        drawStartInfo();
        for (Snapshot s : snapshots) {
            this.house = s.getHouse();
            clearConsole();
            drawHouse();
            paintPause();
        }
    }

    private void drawStartInfo() {
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

    private void clearConsole() {
        /*try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
		
		try
		{
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
                console.printf("\033[H\033[2J");
				Runtime.getRuntime().exec("cls");
			}
			else {
                console.printf("\033[H\033[2J");
				Runtime.getRuntime().exec("clear");
			}
		}
		catch (final Exception e)
		{ }
    }

    private void drawHouse() {
        drawGround();
        for (int i = 0; i < house.getNumFloors(); i++) {
            drawFloor(indexToFloorNum(i));
            drawGround();
        }
        console.printf("\n");
    }

    private void drawGround() {
        for (int i = 0; i < 10; i++) {
            console.printf("___");
        }
        console.printf("\n");
    }

    private void drawFloor(int floor) {
        for (int part = 0; part < entityParts; part++) {
            for (int i = 0; i < house.getLifts().size(); i++) {
                console.printf(drawShaft());
                console.printf(drawLift(floor, i, part));
                console.printf(drawShaft());
                console.printf(drawWall());
            }
            console.printf(drawButtons(floor, part));
            console.printf("\n");
        }
    }

    private String drawShaft() {
        return "|";
    }

    private String drawWall() {
        return " ";
    }

    private String drawButtons(int floor, int part) {
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
        StringBuilder result = new StringBuilder("");
        String passengerSymbol = "P";
        List<Passenger> passengers = house.getPassengers().stream()
                .filter(p -> (p.getFloor() == floor && !p.getInLift()))
                .collect(Collectors.toList());
        for (Passenger p : passengers) {
            String direction = "";
            if (p.getFloor() == p.getStartFloor())
                direction = (p.getDirection() == -1 ? "down" : "up");
            result.append(passengerSymbol);
            result.append(house.getPassengers().indexOf(p) + 1);
            result.append(direction);
            result.append("; ");
        }
        return result.toString();
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
