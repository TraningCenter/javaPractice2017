/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.floor;

import edu.elevatorsim.model.interfaces.IButton;
import edu.elevatorsim.model.interfaces.Passenger;
import java.util.ArrayList;


public class Floor {
    
    private int level;
    private ArrayList<Passenger> passengers;
    private ArrayList<Passenger> arrivedPassenger;
    private IButton button;
    private boolean elevatorOnFloor;
    
    public Floor(int level){
        this.level = level;
        passengers = new ArrayList<>();
        arrivedPassenger = new ArrayList<>();
        button = new CallButton(level);
    }

    public int getLevel() {
        return level;
    }
    
    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
        if (!button.isActive()) button.pushButton();
    }

    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
        if (passengers.isEmpty()){
            button.offButton();
        } else if (!isElevatorOnFloor()){
            button.pushButton();
        }
    }
    
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public ArrayList<Passenger> getArrivedPassenger() {
        return arrivedPassenger;
    }

    public void addToArrivedList(Passenger passenger){
        arrivedPassenger.add(passenger);
    }

    public IButton getButton(){
        return button;
    }
    
    public boolean isElevatorOnFloor(){
        return elevatorOnFloor;
    }
    
    public void setElevatorOnFloor(boolean bool){
        elevatorOnFloor = bool;
    }
}
