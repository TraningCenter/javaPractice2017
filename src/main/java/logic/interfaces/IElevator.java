package logic.interfaces;


import logic.Floor;
import logic.State;
import logic.interfaces.buttons.IElevatorButton;

import java.util.List;

public interface IElevator extends IElevatorButton {
    List<IPassenger> getPassengers();

    void addPassenger(IPassenger passenger);

    void deletePassenger(IPassenger passenger);

    List<Floor> getAvailableFloors();

    void setAvailableFloor(List<Floor> floors);

    void addFloorInQueue(Floor floor);

    Floor getNextDestinationFloor();

    void deleteFloorFromQueue(Floor floor);

    void countRemainingCapacity(int currentWeight);

    void setState(State state);

}
