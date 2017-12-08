package com.netcracker.unc.visualization.elements;


import java.util.Collections;

import static com.netcracker.unc.visualization.VisualizerConfig.ELEVATOR_HEIGHT;
import static com.netcracker.unc.visualization.VisualizerConfig.ELEVATOR_WIDTH;

/**
 * Class which visualizes elevator with passengers, open and close doors
 */
public class ElevatorPicture implements IPicture {

    private int id;
    private int yCoordinate;
    private int countOfPassengers;
    private boolean isOpened;

    public ElevatorPicture(int id, int yCoordinate, int countOfPassengers, boolean isOpened) {
        this.id = id;
        this.yCoordinate = yCoordinate;
        this.countOfPassengers = countOfPassengers;
        this.isOpened = isOpened;
    }


    public String draw(int line) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| ");
        int i = line - yCoordinate;
        if (i == 0) {
            stringBuilder.append("|").append(printNTimes("¯", ELEVATOR_WIDTH)).append("|");
        } else if (i == ELEVATOR_HEIGHT / 2) {
            int countSize = String.valueOf(countOfPassengers).length();
            int leftSize = (ELEVATOR_WIDTH - countSize) / 2;
            if (isOpened) {
                stringBuilder
                        .append("|")
                        .append(printNTimes(" ", leftSize - 1))
                        .append("<").append(countOfPassengers).append(">")
                        .append(printNTimes(" ", ELEVATOR_WIDTH - leftSize - countSize - 1))
                        .append("|");
            } else {
                stringBuilder
                        .append("|")
                        .append(printNTimes(" ", leftSize))
                        .append(countOfPassengers)
                        .append(printNTimes(" ", ELEVATOR_WIDTH - leftSize - countSize))
                        .append("|");
            }
        } else if (i == ELEVATOR_HEIGHT - 1) {
            stringBuilder.append("|").append(printNTimes("_", ELEVATOR_WIDTH)).append("|");
        } else if (i > 0 && i < (ELEVATOR_HEIGHT - 1)) {
            stringBuilder.append("|").append(printNTimes(" ", ELEVATOR_WIDTH)).append("|");
        } else {
            stringBuilder.append(printNTimes(" ", ELEVATOR_WIDTH + 2));
        }
        stringBuilder.append(" |");
        return stringBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public int getCountOfPassengers() {
        return countOfPassengers;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setCountOfPassengers(int countOfPassengers) {
        this.countOfPassengers = countOfPassengers;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    private static String printNTimes(String string, int n) {
        return String.join("", Collections.nCopies(n, string));
    }

}
