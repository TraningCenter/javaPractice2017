package com.eugene.сontroller.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for lift entity
 * comes to people and transports them
 */
public class Lift extends Entity {

    private boolean working;
    private int floor;
    private boolean moving;
    private int direction;
    private int maxWeight;
    private boolean overload;
    private int doorsActions;
    private List<Integer> floors;
    private List<Passenger> passengers;

    public boolean getWorking() {
        return working;
    }

    public int getFloor() {
        return floor;
    }

    public boolean getMoving() {
        return moving;
    }

    public void stop() {
        moving = false;
        direction = 0;//today
        /*if (floors != null && floors.isEmpty())
            direction = 0;
        newPassengers = new ArrayList<>();*/
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public boolean getOverload() {
        return overload;
    }

    public int getDoorsActions() {
        return doorsActions;
    }

    public void setDoorsActions(int doorsActions) {
        this.doorsActions = doorsActions;
    }

    public List<Integer> getFloors() {
        return floors;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void letinPassenger(Passenger passenger) {
        //if lift hasnt yet planned to go to the final floor of the passenger, we add this floor
        if (!floors.contains(passenger.getEndFloor()))
            floors.add(passenger.getEndFloor());
        passengers.add(passenger);
    }

    public void move() {
        if (floors.isEmpty())
            return;
        moving = true;
        if (direction == 0)
            direction = (floors.get(0) - floor) > 0 ? 1 : -1;
        if (direction == -1)
            floor--;
        if (direction == 1)
            floor++;
        for (Passenger p : passengers)
            p.setFloor(floor);
    }

    public Lift(boolean working, int startFloor, int maxWeight) {
        this.working = working;
        this.floor = startFloor;
        this.maxWeight = maxWeight;
        this.floors = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public Lift(Lift lift) {
        this.working = lift.working;
        this.floor = lift.floor;
        this.moving = lift.moving;
        this.direction = lift.direction;
        this.maxWeight = lift.maxWeight;
        this.overload = lift.overload;
        this.doorsActions = lift.doorsActions;
        this.floors = new ArrayList<>();
        this.passengers = new ArrayList<>();
        for (Integer i : lift.floors)
            floors.add(new Integer(i));
        for (Passenger p : lift.passengers)
            passengers.add(new Passenger(p));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lift lift = (Lift) o;

        if (working != lift.working) return false;
        if (floor != lift.floor) return false;
        if (moving != lift.moving) return false;
        if (direction != lift.direction) return false;
        if (maxWeight != lift.maxWeight) return false;
        if (overload != lift.overload) return false;
        if (doorsActions != lift.doorsActions) return false;
        if (!floors.equals(lift.floors)) return false;
        return passengers.equals(lift.passengers);
    }

    @Override
    public int hashCode() {
        int result = (working ? 1 : 0);
        result = 31 * result + floor;
        result = 31 * result + (moving ? 1 : 0);
        result = 31 * result + direction;
        result = 31 * result + maxWeight;
        result = 31 * result + (overload ? 1 : 0);
        result = 31 * result + doorsActions;
        result = 31 * result + floors.hashCode();
        result = 31 * result + passengers.hashCode();
        return result;
    }
}
