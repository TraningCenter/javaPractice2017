package tadite.javase.elevatorSimulator.model.elevator;

import tadite.javase.elevatorSimulator.model.passenger.TransportClient;

public interface TransportClientKeeper {
    void addClient(TransportClient client);
    void removeClient(TransportClient client);
}
