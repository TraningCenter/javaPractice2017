/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.elevator;

import edu.elevatorsim.model.interfaces.IButtonPanel;
import edu.elevatorsim.model.interfaces.Passenger;
import java.util.ArrayList;


public class Elevator {
    
    private final int CAPACITY = 9;
    
    private int id;
    private int currentFloor;
    private int nextFloor;
    private IButtonPanel buttonPanel;
    private ArrayList<Passenger> passengers;
    private Direction direction;
    private int passengerCount;
    private int floorCount;
    private boolean full;
    
    public Elevator(int id, int floorCount){
        this.id = id;
        this.currentFloor = currentFloor;
        buttonPanel = new ElevatorPanel(floorCount);
        this.floorCount = floorCount;
        passengers = new ArrayList<>();
        direction = Direction.STOP;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        for (Passenger passenger : passengers){
            passenger.setCurrentFloor(this.currentFloor);
        }
    }

    public int getNextFloor() {
        return nextFloor;
    }
    
    public int getFloorCount(){
        return floorCount;
    }

    public void setNextFloor(int nextFloor) {
        this.nextFloor = nextFloor;
        if (nextFloor > currentFloor) this.setDirection(Direction.UP);
        if (nextFloor < currentFloor) this.setDirection(Direction.DOWN);
    }

    public IButtonPanel getButtonPanel() {
        return buttonPanel;
    }
    
    public boolean addPassenger(Passenger passenger){
        if (!isFull()){
            passengers.add(passenger);
        } else{
            return false;
        }
        if (!buttonPanel.isActive(passenger.getDestinationFloor())){
            buttonPanel.pushButton(passenger.getDestinationFloor());
        }
        this.passengerCount = passengers.size();
        if (passengerCount == CAPACITY){
            setFull(true);
        }
        return true;
    }
    
    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
        this.passengerCount = passengers.size();
        if (buttonPanel.isActive(passenger.getDestinationFloor())){
            buttonPanel.offButton(passenger.getDestinationFloor());
        }
        setFull(false);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getPassengerCount() {
        return passengerCount;
    }
    
    public ArrayList<Passenger> getPassengers(){
        return passengers;
    }

    public boolean isFull() {
        return full;
    }

    private void setFull(boolean full) {
        this.full = full;
    }

    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        
        if (obj == this){
            return true;
        }
        
        if (!(getClass() == obj.getClass())){
            return false;
        } else{
            Elevator tmp = (Elevator)obj;
            if (this.id == tmp.id){
                return true;
            } else{
                return false;
            }
        }
    }
}
