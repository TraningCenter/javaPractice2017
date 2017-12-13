package com.eugene.сontroller.Entities;

/**
 * Class for button entity
 * Calls lifts, notifies people about the arrival of elevators
 */
public class Button extends Entity {

    private boolean on;
    private int floor;
    private int direction;

    public boolean getOn() {
        return on;
    }

    public int getFloor() {
        return floor;
    }

    public int getDirection() {
        return direction;
    }

    public void activate() {
        on = true;
    }

    public void deactivate() {
        on = false;
    }

    public Button(int floor, int direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public Button(Button button) {
        this.on = button.on;
        this.floor = button.floor;
        this.direction = button.direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Button button = (Button) o;

        if (on != button.on) return false;
        if (floor != button.floor) return false;
        return direction == button.direction;
    }

    @Override
    public int hashCode() {
        int result = (on ? 1 : 0);
        result = 31 * result + floor;
        result = 31 * result + direction;
        return result;
    }
}
