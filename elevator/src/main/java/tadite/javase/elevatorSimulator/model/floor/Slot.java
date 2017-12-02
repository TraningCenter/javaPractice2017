package tadite.javase.elevatorSimulator.model.floor;

import tadite.javase.elevatorSimulator.model.misc.Location;

public interface Slot {
    Location getLocation();
    SlotType getType();
}
