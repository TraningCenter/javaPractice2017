package tadite.javase.elevatorSimulator.model.elevator;

import tadite.javase.elevatorSimulator.model.misc.Button;
import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.misc.Observable;
import tadite.javase.elevatorSimulator.model.misc.Observer;
import tadite.javase.elevatorSimulator.model.passenger.TransportClient;

import java.util.LinkedList;
import java.util.List;

/**
 * Implements elevator logic, takes TransportClients and update their positions
 * can move up and down, be observable and notify observers on doors opening
 */
public class DefaultElevator implements Elevator, ElevatorMechanism, Observable {
    private int level;
    private int position;
    private List<TransportClient> clients=new LinkedList<>();
    private List<Observer> observers=new LinkedList<>();
    private Button button;

    public DefaultElevator(Location location, Button button) {
        this.position=location.getPosition();
        this.level = location.getLevel();
        this.button = button;
    }

    @Override
    public void pushButton(int level) {
        button.addRequest(level);
    }

    @Override
    public void addClient(TransportClient client) {
        clients.add(client);
    }

    @Override
    public void removeClient(TransportClient client) {
        clients.remove(client);
    }

    @Override
    public void moveUp() {
        level++;
        updateClientsLocation();
    }

    @Override
    public void moveDown() {
        level--;
        updateClientsLocation();
    }

    @Override
    public void onDoorOpened() {
        notifyObservers();
    }

    @Override
    public int getLevel() {
        return level;
    }

    private void updateClientsLocation() {
        clients.forEach(client -> client.updateLocation(new Location(getLocation().getPosition(),getLocation().getLevel())));
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update());
    }

    public int getObserversCount(){
        return observers.size();
    }

    public int getClientsCount(){
        return clients.size();
    }

    public Location getLocation() {
        return new Location(position,level);
    }
}
