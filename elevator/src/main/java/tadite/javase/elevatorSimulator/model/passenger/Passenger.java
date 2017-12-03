package tadite.javase.elevatorSimulator.model.passenger;

import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.floor.Slot;

/**
 * Can update his state, give current location/slot/floor, check for target achievement
 */
public interface Passenger {
    void updateState();
    Location getCurrentLocation();
    Slot getCurrentSlot();
    Floor getCurrentFloor();
    boolean isActive();
}
