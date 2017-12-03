package tadite.javase.elevatorSimulator.model.elevator;

import tadite.javase.elevatorSimulator.model.passenger.TransportClient;

/**
 * Keep TransportClients, save then inside
 */
public interface TransportClientKeeper {
    void addClient(TransportClient client);
    void removeClient(TransportClient client);
}
