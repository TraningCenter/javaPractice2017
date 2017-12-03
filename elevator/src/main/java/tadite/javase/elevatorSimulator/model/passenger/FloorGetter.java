package tadite.javase.elevatorSimulator.model.passenger;

import tadite.javase.elevatorSimulator.model.floor.Floor;

/**
 * Give Floor instance for given level number
 */
public interface FloorGetter {
    Floor getCurrentFloor(Passenger passenger);
}
