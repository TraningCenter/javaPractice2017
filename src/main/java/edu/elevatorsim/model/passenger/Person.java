/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.passenger;

import edu.elevatorsim.model.interfaces.Passenger;


public class Person implements Passenger{

    private int currentFloor;
    private int destinationFloor;
    
    public Person(int currentFloor, int destinationFloor){
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
    }
    
    @Override
    public int getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public int getDestinationFloor() {
        return destinationFloor;
    }

    @Override
    public void setCurrentFloor(int level) {
        this.currentFloor = level;
    }
}
