package com.netcracker.unc.logic;

import com.netcracker.unc.logic.interfaces.IPassenger;

import java.util.ArrayList;
import java.util.List;

public class Floor implements Comparable<Floor> {
    private int id;
    private List<IPassenger> passengers;

    public Floor(int id) {
        this.id = id;
        passengers = new ArrayList<IPassenger>();
    }

    public Floor(int id, List<IPassenger> passengers) {
        this.id = id;
        this.passengers = passengers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<IPassenger> passengers() {
        return passengers;
    }

    public void setListOfPassengers(List<IPassenger> passengers) {
        this.passengers = passengers;
    }

    public void addPassenger(IPassenger passenger) {
        this.passengers.add(passenger);
    }

    public void addPassengers(List<IPassenger> passengers) {
        this.passengers.addAll(passengers);
    }

    public void deletePassenger(IPassenger passenger) {
        this.passengers.remove(passenger);
    }

    public void deletePassengers(List<IPassenger> passengers) {
        this.passengers.removeAll(passengers);
    }


    public int compareTo(Floor floor) {
        return id - floor.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Floor floor = (Floor) o;

        return id == floor.id && passengers.equals(floor.passengers);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + passengers.hashCode();
        return result;
    }
}
