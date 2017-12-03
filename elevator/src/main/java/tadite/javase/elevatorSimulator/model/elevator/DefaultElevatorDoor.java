package tadite.javase.elevatorSimulator.model.elevator;

import tadite.javase.elevatorSimulator.model.misc.Button;
import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.misc.Observer;
import tadite.javase.elevatorSimulator.model.floor.Slot;
import tadite.javase.elevatorSimulator.model.floor.SlotType;

import java.util.LinkedList;
import java.util.List;

/**
 * Implements elevator door logic, can close/open
 * can be observable and notify observers on doors opening
 */
public class DefaultElevatorDoor implements Slot,ElevatorDoorMechanism,ElevatorDoor {
    private Location location;
    private ElevatorConfig config;
    private List<Observer> observers=new LinkedList<>();
    private Button button;
    private Elevator elevator;
    private ElevatorDoorStatus doorStatus = ElevatorDoorStatus.DOOR_CLOSED;

    public DefaultElevatorDoor(Location location, ElevatorConfig config, Button button, Elevator elevator){
        this.location = location;
        this.config = config;
        this.button = button;
        this.elevator = elevator;
    }

    @Override
    public void pushButton() {
        button.addRequest(location.getLevel());
    }

    @Override
    public ElevatorConfig getConfig() {
        return config;
    }

    @Override
    public Elevator getElevator() {
        return elevator;
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

    @Override
    public void openDoor() {
        doorStatus=ElevatorDoorStatus.DOOR_OPENED;
        notifyObservers();
    }

    @Override
    public void closeDoor() {
        doorStatus=ElevatorDoorStatus.DOOR_CLOSED;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public SlotType getType() {
        return SlotType.ELEVATOR_DOOR;
    }

    public ElevatorDoorStatus getDoorStatus() {
        return doorStatus;
    }


}
