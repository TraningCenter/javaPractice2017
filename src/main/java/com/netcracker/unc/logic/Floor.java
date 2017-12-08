package com.netcracker.unc.logic;

import com.netcracker.unc.logic.interfaces.IPassenger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which has list of passengers and buttons (up, down)
 */
public class Floor implements Comparable<Floor> {
    private int id;
    private List<IPassenger> passengers;
    private boolean isPushedButtonUp;
    private boolean isPushedButtonDown;

    public Floor(int id) {
        this.id = id;
        passengers = new ArrayList<>();
        isPushedButtonDown = isPushedButtonUp = false;
    }

    public Floor(int id, List<IPassenger> passengers) {
        this.id = id;
        this.passengers = passengers;
        isPushedButtonDown = isPushedButtonUp = false;
    }

    public int getId() {
        return id;
    }

    public List<IPassenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(IPassenger passenger) {
        this.passengers.add(passenger);
    }

    public void deletePassenger(IPassenger passenger) {
        this.passengers.remove(passenger);
    }

    public int compareTo(Floor floor) {
        return id - floor.id;
    }

    public boolean isPushedButtonUp() {
        return isPushedButtonUp;
    }

    public void setPushedButtonUp(boolean pushedButtonUp) {
        isPushedButtonUp = pushedButtonUp;
    }

    public boolean isPushedButtonDown() {
        return isPushedButtonDown;
    }

    public void setPushedButtonDown(boolean pushedButtonDown) {
        isPushedButtonDown = pushedButtonDown;
    }
}
