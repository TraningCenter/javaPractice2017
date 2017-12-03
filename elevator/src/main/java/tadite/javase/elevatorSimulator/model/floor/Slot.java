package tadite.javase.elevatorSimulator.model.floor;

import tadite.javase.elevatorSimulator.model.misc.Location;

/**
 * Slot on floor, has location and type
 */
public interface Slot {
    Location getLocation();
    SlotType getType();
}
