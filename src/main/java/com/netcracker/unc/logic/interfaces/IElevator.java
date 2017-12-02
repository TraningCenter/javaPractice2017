package com.netcracker.unc.logic.interfaces;


import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;

import java.util.List;

public interface IElevator {
    List<IPassenger> getPassengers();

    boolean addPassenger(IPassenger passenger);

    void deletePassenger(IPassenger passenger);

    List<Floor> getAvailableFloors();

    void setAvailableFloors(List<Floor> floors);

    void addAvailableFloor(Floor floor);

    void addFloorInQueue(Floor floor);

    Floor getNextDestinationFloor();

    void deleteFloorFromQueue();

    void setState(State state);

    boolean setCurrentFloor(Floor floor);

    void setCapacity(int capacity);

    int getId();

    int getCapacity();
}
