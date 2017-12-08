package com.netcracker.unc.logic;


import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class implements IElevator interface
 */
public class Elevator implements IElevator {
    private int id;
    private State state;
    private Floor currentFloor;
    private List<Floor> availableFloors;
    private List<IPassenger> passengers;
    private List<Floor> floorsToVisit;
    private int capacity;
    private int remainingCapacity;
    private boolean isUnLoaded = false;
    private boolean isLoaded = false;
    private boolean isOpened = false;
    private boolean inFloor = true;

    public Elevator(int id) {
        this.id = id;
        state = State.STOPPED;
        availableFloors = new ArrayList<>();
        passengers = new ArrayList<>();
        floorsToVisit = new LinkedList<>();
    }

    public Elevator(int id, Floor currentFloor, List<Floor> availableFloors, int capacity) {
        this.id = id;
        this.state = State.STOPPED;
        this.currentFloor = currentFloor;
        this.availableFloors = availableFloors;
        this.passengers = new ArrayList<>();
        this.floorsToVisit = new LinkedList<>();
        this.capacity = remainingCapacity = capacity;
    }

    public List<IPassenger> getPassengers() {
        return passengers;
    }

    public boolean addPassenger(IPassenger passenger) {
        if (remainingCapacity - passenger.getWeight() > 0) {
            passengers.add(passenger);
            remainingCapacity -= passenger.getWeight();
            return true;
        }
        return false;
    }

    public void deletePassenger(IPassenger passenger) {
        passengers.remove(passenger);
        remainingCapacity += passenger.getWeight();
    }

    public List<Floor> getAvailableFloors() {
        return availableFloors;
    }

    public void addAvailableFloor(Floor floor) {
        availableFloors.add(floor);
    }

    public void addFloorInQueue(Floor floor) {
        int n = floorsToVisit.size();
        if (floorsToVisit.contains(floor))
            return;
        if (!availableFloors.contains(floor))
            return;
        switch (state) {
            case UP:
                // по всем этажам в списке посещений
                for (int i = 0; i < n; i++) {
                    // если этаж выше текущего
                    if (currentFloor.compareTo(floor) < 0) {
                        // и добавляемый этаж ниже того что в списке
                        if (floor.compareTo(floorsToVisit.get(i)) < 0) {
                            floorsToVisit.add(i, floor);
                            return;
                        }
                    } else
                        // если этаж ниже текущего и добавляемый этаж выше того что в списке
                        if (floorsToVisit.get(i).compareTo(floor) < 0) {
                            floorsToVisit.add(i, floor);
                            return;
                        }
                }
                floorsToVisit.add(floor);
                break;
            case DOWN:
                // по всем этажам в списке посещений
                for (int i = 0; i < n; i++) {
                    //если текущий этаж выше
                    if (floor.compareTo(currentFloor) < 0) {
                        // и добавляемый этаж выше того что в списке
                        if (floorsToVisit.get(i).compareTo(floor) < 0) {
                            floorsToVisit.add(i, floor);
                            return;
                        }

                    } else
                        //если этаж выше текущего и добавляемый этаж ниже того что в списке
                        if (floor.compareTo(floorsToVisit.get(i)) < 0) {
                            floorsToVisit.add(i, floor);
                            return;
                        }
                }
                floorsToVisit.add(floor);
                break;
            case STOPPED:
                floorsToVisit.add(floor);
                if (floor.compareTo(currentFloor) < 0)
                    state = State.DOWN;
                else if (floor.compareTo(currentFloor) > 0)
                    state = State.UP;
                break;
        }
    }

    public Floor getNextDestinationFloor() {
        return floorsToVisit.get(0);
    }

    public void deleteFloorFromQueue() {
        floorsToVisit.remove(0);
        if (floorsToVisit.isEmpty())
            state = State.STOPPED;
        else
            state = (getNextDestinationFloor().compareTo(currentFloor) < 0) ? State.DOWN : State.UP;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public boolean setCurrentFloor(Floor currentFloor) {
        if (!availableFloors.contains(currentFloor))
            return false;
        this.currentFloor = currentFloor;
        return true;
    }

    public List<Floor> getFloorsToVisit() {
        return floorsToVisit;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = remainingCapacity = capacity;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public boolean isUnLoaded() {
        return isUnLoaded;
    }

    public void setUnLoaded(boolean unLoaded) {
        isUnLoaded = unLoaded;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean isInFloor() {
        return inFloor;
    }

    public void setInFloor(boolean inFloor) {
        this.inFloor = inFloor;
    }
}

