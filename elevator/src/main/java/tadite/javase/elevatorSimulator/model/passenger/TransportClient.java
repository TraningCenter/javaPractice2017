package tadite.javase.elevatorSimulator.model.passenger;

import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.elevator.TransportClientKeeper;

public interface TransportClient {
    void goIn(TransportClientKeeper keeper);
    void goOut();
    void updateLocation(Location location);
}
