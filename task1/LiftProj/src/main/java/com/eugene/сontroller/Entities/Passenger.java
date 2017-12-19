package com.eugene.сontroller.Entities;

/**
 * Class for button entity
 * moves on floors with the help of lifts
 */
public class Passenger extends Entity {

    private int startFloor;
    private int endFloor;
    private int floor;
    private int direction;
    private boolean inLift;

    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean getInLift() {
        return inLift;
    }

    public void setInLift(boolean inLift) {
        this.inLift = inLift;
    }

    public void enterToLift() {
        inLift = true;
    }

    public Passenger(int startFloor, int endFloor) {
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        floor = startFloor;
        this.direction = (startFloor > endFloor ? -1 : 1);
    }

    public Passenger(Passenger passenger) {
        this.startFloor = passenger.startFloor;
        this.endFloor = passenger.endFloor;
        this.floor = passenger.floor;
        this.direction = passenger.direction;
        this.inLift = passenger.inLift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        if (startFloor != passenger.startFloor) return false;
        if (endFloor != passenger.endFloor) return false;
        if (floor != passenger.floor) return false;
        if (direction != passenger.direction) return false;
        return inLift == passenger.inLift;
    }

    @Override
    public int hashCode() {
        int result = startFloor;
        result = 31 * result + endFloor;
        result = 31 * result + floor;
        result = 31 * result + direction;
        result = 31 * result + (inLift ? 1 : 0);
        return result;
    }

}
