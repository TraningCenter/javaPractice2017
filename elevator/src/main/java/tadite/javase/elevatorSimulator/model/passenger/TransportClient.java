package tadite.javase.elevatorSimulator.model.passenger;

import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.elevator.TransportClientKeeper;

/**
 * Can go in/out TransportClientKeeper and give method for updating his location from TransportClientKeeper
 */
public interface TransportClient {
    void goIn(TransportClientKeeper keeper);
    void goOut();
    void updateLocation(Location location);
}
