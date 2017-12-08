package com.netcracker.unc.visualization.elements;

import com.netcracker.unc.logic.Floor;

import java.util.Collections;

import static com.netcracker.unc.visualization.VisualizerConfig.FLOOR_HEIGHT;
import static com.netcracker.unc.visualization.VisualizerConfig.FLOOR_WIDTH;

/**
 * * Class which visualizes floor with passengers
 */
public class FloorPicture implements IPicture {

    private int id;
    private int yCoordinate;
    private int countOfPassengers;
    private boolean isPushedUp;
    private boolean isPushedDown;

    public FloorPicture(int id, int yCoordinate, int countOfPassengers, boolean isPushedUp, boolean isPushedDown) {
        this.id = id;
        this.yCoordinate = yCoordinate;
        this.countOfPassengers = countOfPassengers;
        this.isPushedUp = isPushedUp;
        this.isPushedDown = isPushedDown;
    }

    public String draw(int line) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = line % FLOOR_HEIGHT;
        if (i == 1) {
            stringBuilder.append(printSpaceNTimes(FLOOR_WIDTH - 1)).append(isPushedUp ? "˄" : " ");
        } else if (i == 0) {
            stringBuilder.append(printSpaceNTimes(FLOOR_WIDTH - 1)).append(isPushedDown ? "˅" : " ");
        } else if (i == FLOOR_HEIGHT / 2 + 1) {
            int countSize = String.valueOf(countOfPassengers).length();
            int leftSize = (FLOOR_WIDTH - countSize) / 2;
            stringBuilder
                    .append(printSpaceNTimes(leftSize))
                    .append(countOfPassengers)
                    .append(printSpaceNTimes(FLOOR_WIDTH - leftSize - countSize));
        } else {
            stringBuilder.append(printSpaceNTimes(FLOOR_WIDTH));
        }
        return stringBuilder.toString();
    }

    public void update(Floor floor) {
        countOfPassengers = floor.getPassengers().size();
        isPushedDown = floor.isPushedButtonDown();
        isPushedUp = floor.isPushedButtonUp();
    }

    public int getId() {
        return id;
    }

    public int getCountOfPassengers() {
        return countOfPassengers;
    }

    public boolean isPushedUp() {
        return isPushedUp;
    }

    public boolean isPushedDown() {
        return isPushedDown;
    }

    private static String printSpaceNTimes(int n) {
        return String.join("", Collections.nCopies(n, " "));
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setPushedUp(boolean pushedUp) {
        isPushedUp = pushedUp;
    }

    public void setPushedDown(boolean pushedDown) {
        isPushedDown = pushedDown;
    }
}
