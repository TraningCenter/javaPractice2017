package com.netcracker.unc.logic;


import com.netcracker.unc.logic.interfaces.IPassenger;

public class Passenger implements IPassenger {
    private int startFloor;
    private int destinationFloor;
    private int weight;
    private double probabilityOfChoice;

    public Passenger(int startFloor, int destinationFloor, int weight, double probabilityOfChoice) {
        this.startFloor = startFloor;
        this.destinationFloor = destinationFloor;
        this.weight = weight;
        this.probabilityOfChoice = probabilityOfChoice;
    }

    public State getDirection() {
        return (startFloor - destinationFloor) > 0 ? State.DOWN : State.UP;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(int startFloor) {
        this.startFloor = startFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getProbabilityOfChoice() {
        return probabilityOfChoice;
    }

    public void setProbabilityOfChoice(double probabilityOfChoice) {
        this.probabilityOfChoice = probabilityOfChoice;
    }

}
