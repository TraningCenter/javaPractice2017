package tadite.javase.elevatorSimulator.model.passenger;

import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.floor.Slot;

public interface Passenger {
    void updateState();
    Location getCurrentLocation();
    Slot getCurrentSlot();
    Floor getCurrentFloor();
    boolean isActive();
}
