package com.eugene.сontroller.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for house entity
 * is the keeper for all elevators, passengers, buttons
 */
public class House extends Entity {

    private List<Passenger> passengers;
    private List<Lift> lifts;
    private List<Button> buttons;
    private int numFloors;

    public House(List<Passenger> passengers, List<Lift> lifts, List<Button> buttons, int numFloors) {
        this.passengers = passengers;
        this.lifts = lifts;
        this.buttons = buttons;
        this.numFloors = numFloors;
    }

    public House(House house) {
        passengers = new ArrayList<>();
        lifts = new ArrayList<>();
        buttons = new ArrayList<>();
        for (Passenger p : house.getPassengers())
            passengers.add(new Passenger(p));
        for (Lift l : house.getLifts())
            lifts.add(new Lift(l));
        for (Button b : house.getButtons())
            buttons.add(new Button(b));
        this.numFloors = house.getNumFloors();
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Lift> getLifts() {
        return lifts;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public int getNumFloors() {
        return numFloors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        if (numFloors != house.numFloors) return false;
        if (!passengers.equals(house.passengers)) return false;
        if (!lifts.equals(house.lifts)) return false;
        return buttons.equals(house.buttons);
    }

    @Override
    public int hashCode() {
        int result = passengers.hashCode();
        result = 31 * result + lifts.hashCode();
        result = 31 * result + buttons.hashCode();
        result = 31 * result + numFloors;
        return result;
    }
}
