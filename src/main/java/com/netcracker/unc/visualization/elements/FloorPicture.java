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
        int countSize = String.valueOf(countOfPassengers).length();
        int leftSize = (FLOOR_WIDTH-countSize)/2;
        switch (line%FLOOR_HEIGHT) {
            case 1:
                stringBuilder.append(printSpaceNTimes(FLOOR_WIDTH-1)).append(isPushedUp?"˄":" ");
                break;
            case 0:
                stringBuilder.append(printSpaceNTimes(FLOOR_WIDTH-1)).append(isPushedDown?"˅":" ");
                break;
            case FLOOR_HEIGHT/2+1:
                stringBuilder
                    .append(printSpaceNTimes(leftSize))
                    .append(countOfPassengers)
                    .append(printSpaceNTimes(FLOOR_WIDTH - leftSize - countSize));
                break;
            default:
                stringBuilder.append(printSpaceNTimes(FLOOR_WIDTH));
                break;
        }
        return stringBuilder.toString();
    }

    public void update(Floor floor){
        countOfPassengers = floor.getPassengers().size();
        isPushedDown = floor.isPushedButtonDown();
        isPushedUp = floor.isPushedButtonUp();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountOfPassengers() {
        return countOfPassengers;
    }

    public void setCountOfPassengers(int countOfPassengers) {
        this.countOfPassengers = countOfPassengers;
    }

    public boolean isPushedUp() {
        return isPushedUp;
    }

    public void setPushedUp(boolean pushedUp) {
        isPushedUp = pushedUp;
    }

    public boolean isPushedDown() {
        return isPushedDown;
    }

    public void setPushedDown(boolean pushedDown) {
        isPushedDown = pushedDown;
    }

    private static String printSpaceNTimes(int n) {
        return String.join("", Collections.nCopies(n, " "));
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
