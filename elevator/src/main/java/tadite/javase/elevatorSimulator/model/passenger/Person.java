package tadite.javase.elevatorSimulator.model.passenger;

import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.floor.Slot;
import tadite.javase.elevatorSimulator.model.elevator.TransportClientKeeper;

public class Person implements Passenger, TransportClient{
    private Location currentLocation;
    private PersonState state;
    private Floor currentFloor;
    private TransportClientKeeper keeper;
    private Location targetLocation;
    private FloorGetter floorGetter;

    private boolean active=true;

    public Person(Location startLocation, Floor startFloor, Location targetLocation, FloorGetter floorGetter){
        this.currentLocation =startLocation;
        this.currentFloor=startFloor;
        this.targetLocation = targetLocation;
        this.floorGetter = floorGetter;
    }

    @Override
    public void updateState() {
        state.action();
    }

    @Override
    public Location getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public Slot getCurrentSlot() {
        return currentFloor.getSlot(this.currentLocation.getPosition());
    }

    @Override
    public Floor getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setInactive(){
        active=false;
    }

    @Override
    public void goIn(TransportClientKeeper keeper) {
        this.keeper=keeper;
        keeper.addClient(this);
    }

    @Override
    public void goOut() {
        if (keeper != null) {
            keeper.removeClient(this);
            keeper = null;
            currentFloor = floorGetter.getCurrentFloor(this);
        }
    }

    @Override
    public void updateLocation(Location location) {
        this.currentLocation=location;
    }

    public Location getTargetLocation() {
        return targetLocation;
    }

    public void changeState(PersonState personState){
        this.state = personState;
    }

    public void moveLeft(){
        this.currentLocation.setPosition(this.currentLocation.getPosition()-1);
    }

    public void moveRight(){
        this.currentLocation.setPosition(this.currentLocation.getPosition()+1);
    }

    public TransportClientKeeper getKeeper() {
        return keeper;
    }

}
