package tadite.javase.elevatorSimulator.model.elevator;

import tadite.javase.elevatorSimulator.model.misc.Location;

import java.util.Iterator;

/**
 * Can update his state, give location, give list of used locations
 */
public interface IndoorTransport {
    void updateState();
    Location getLocation();
    IndoorTransportType getType();
    Iterator<Location> getUsedLocations();
}
