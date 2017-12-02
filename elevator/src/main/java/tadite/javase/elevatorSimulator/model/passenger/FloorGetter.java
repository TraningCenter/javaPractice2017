package tadite.javase.elevatorSimulator.model.passenger;

import tadite.javase.elevatorSimulator.model.floor.Floor;

public interface FloorGetter {
    Floor getCurrentFloor(Passenger passenger);
}
