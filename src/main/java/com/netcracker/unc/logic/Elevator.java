package com.netcracker.unc.logic;


import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;

import java.util.*;

public class Elevator implements IElevator {
    private int id;
    private State state;
    private Floor currentFloor;
    private List<Floor> availableFloors;
    private List<IPassenger> passengers;
    private List<Floor> floorsToVisit;
    private int capacity;
    private int remainingCapacity;

    public Elevator(int id, Floor currentFloor, List<Floor> availableFloors, int capacity) {
        this.id = id;
        this.state = State.STOPPED;
        this.currentFloor = currentFloor;
        this.availableFloors = availableFloors;
        this.passengers = new ArrayList<IPassenger>();
        this.floorsToVisit = new LinkedList<Floor>();
        this.capacity = capacity;
        this.remainingCapacity = capacity;
    }


    public List<IPassenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(IPassenger passenger) {
        passengers.add(passenger);
    }

    public void addPassengers(List<IPassenger> passengers) {
        this.passengers.addAll(passengers);
    }

    public void deletePassenger(IPassenger passenger) {
        passengers.remove(passenger);
    }

    public void deletePassengers(List<IPassenger> passengers) {
        this.passengers.removeAll(passengers);
    }

    public List<Floor> getAvailableFloors() {
        return availableFloors;
    }

    public void setAvailableFloor(List<Floor> floors) {
        availableFloors = floors;
    }

    public void addFloorInQueue(Floor floor) {
        int n = floorsToVisit.size();
        if (floorsToVisit.contains(floor))
            return;
        if (state == State.UP) {
            for (int i = 0; i < n; i++) {
                //если этаж выше текущего
                if (currentFloor.compareTo(floor) < 0) {
                    // и добавляемый этаж ниже того что в списке
                    if (floor.compareTo(floorsToVisit.get(i)) < 0) {
                        floorsToVisit.add(i, floor);
                        return;
                    }
                    // перешли к этажам в обратном направлении
                    if (floorsToVisit.get(i).compareTo(currentFloor) < 0) {
                        floorsToVisit.add(i, floor);
                        return;
                    }

                } else
                    //если этаж ниже текущего и добавляемый этаж выше того что в списке
                    if (floorsToVisit.get(i).compareTo(floor) < 0) {
                        floorsToVisit.add(i, floor);
                        return;
                    }
            }
            floorsToVisit.add(floor);

        }

    }

    public Floor getNextDestinationFloor() {
        return null;
    }

    public void deleteFloorFromQueue(Floor floor) {

    }

    public void countRemainingCapacity(int currentWeight) {

    }

    public void setState(State state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setAvailableFloors(List<Floor> availableFloors) {
        this.availableFloors = availableFloors;
    }

    public void setPassengers(List<IPassenger> passengers) {
        this.passengers = passengers;
    }

    public List<Floor> getFloorsToVisit() {
        return floorsToVisit;
    }

    public void setFloorsToVisit(List<Floor> floorsToVisit) {
        this.floorsToVisit = floorsToVisit;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }
}
