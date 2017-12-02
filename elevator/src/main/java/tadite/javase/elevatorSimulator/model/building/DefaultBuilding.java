package tadite.javase.elevatorSimulator.model.building;

import tadite.javase.elevatorSimulator.model.elevator.IndoorTransport;
import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.passenger.Passenger;

import java.util.Iterator;
import java.util.List;

public class DefaultBuilding implements Building {
    private List<Floor> floors;
    private List<Passenger> passengers;
    private List<IndoorTransport> indoorTransports;

    public DefaultBuilding(List<Floor> floors, List<Passenger> passengers, List<IndoorTransport> indoorTransports) {
        this.floors = floors;
        this.passengers = passengers;
        this.indoorTransports = indoorTransports;
    }

    @Override
    public Iterator<Floor> getFloorIterator() {
        return floors.iterator();
    }

    @Override
    public Iterator<Passenger> getPassengerIterator() {
        return passengers.iterator();
    }

    @Override
    public Iterator<IndoorTransport> getTransportIterator() {
        return indoorTransports.iterator();
    }

    @Override
    public boolean isActive() {
        return passengers.stream().anyMatch(passenger -> passenger.isActive());
    }
}
