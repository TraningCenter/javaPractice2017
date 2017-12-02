package tadite.javase.elevatorSimulator.model.floor;

import tadite.javase.elevatorSimulator.model.misc.Location;

public class EmptySlot implements Slot {
    private Location location;

    public EmptySlot(Location location){
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public SlotType getType() {
        return SlotType.EMPTY_SLOT;
    }
}
