package tadite.javase.elevatorSimulator.model.building;

import tadite.javase.elevatorSimulator.model.elevator.IndoorTransport;
import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.passenger.Passenger;

import java.util.Iterator;

/**
 * Contains all building items, can check for simulation end
 */
public interface Building{
    Iterator<Floor> getFloorIterator();
    Iterator<Passenger> getPassengerIterator();
    Iterator<IndoorTransport> getTransportIterator();

    /**
     * Check for simulation end
     */
    boolean isActive();
    int getFloorsCount();
    int getSlotsCount();
}
