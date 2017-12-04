package com.netcracker.unc.logic;


import com.netcracker.unc.logic.interfaces.IPassenger;

public class Passenger implements IPassenger {
    private Floor startFloor;
    private Floor destinationFloor;
    private int weight;
    private int probabilityOfChoice;

    public Passenger() {
    }

    public Passenger(Floor startFloor, Floor destinationFloor) {
        this.startFloor = startFloor;
        this.destinationFloor = destinationFloor;
    }

    public Passenger(Floor startFloor, Floor destinationFloor, int weight, int probabilityOfChoice) {
        this.startFloor = startFloor;
        this.destinationFloor = destinationFloor;
        this.weight = weight;
        this.probabilityOfChoice = probabilityOfChoice;
    }

    public State getDirection() {
        return (startFloor.getId() - destinationFloor.getId()) > 0 ? State.DOWN : State.UP;
    }

    public Floor getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(Floor startFloor) {
        this.startFloor = startFloor;
    }

    public Floor getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(Floor destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getProbabilityOfChoice() {
        return probabilityOfChoice;
    }

    public void setProbabilityOfChoice(int probabilityOfChoice) {
        this.probabilityOfChoice = probabilityOfChoice;
    }

}
