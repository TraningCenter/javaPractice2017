package com.netcracker.unc.logic.interfaces;


import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;

import java.util.List;

public interface IElevator {
    List<IPassenger> getPassengers();

    boolean addPassenger(IPassenger passenger);

    void deletePassenger(IPassenger passenger);

    List<Floor> getAvailableFloors();

    void addAvailableFloor(Floor floor);

    void addFloorInQueue(Floor floor);

    Floor getNextDestinationFloor();

    void deleteFloorFromQueue();

    List<Floor> getFloorsToVisit();

    State getState();

    void setState(State state);

    boolean setCurrentFloor(Floor floor);

    void setCapacity(int capacity);

    Floor getCurrentFloor();

    int getId();

    int getCapacity();

    int getRemainingCapacity();

    boolean isUnLoaded();

    void setUnLoaded(boolean unLoaded);

    boolean isLoaded();

    void setLoaded(boolean loaded);

    boolean isOpened();

    void setOpened(boolean opened);

    boolean isInFloor();

    void setInFloor(boolean inFloor);
}
