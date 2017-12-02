package com.netcracker.unc.logic.interfaces;


import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;

import java.util.List;

public interface IElevator {
    List<IPassenger> getPassengers();

    boolean addPassenger(IPassenger passenger);

    void deletePassenger(IPassenger passenger);

    List<Floor> getAvailableFloors();

    void setAvailableFloor(List<Floor> floors);

    void addFloorInQueue(Floor floor);

    Floor getNextDestinationFloor();

    void deleteFloorFromQueue();

    void setState(State state);

}
