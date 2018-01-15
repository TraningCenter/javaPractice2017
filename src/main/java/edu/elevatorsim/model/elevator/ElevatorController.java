/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.elevator;

import edu.elevatorsim.model.interfaces.Observer;
import edu.elevatorsim.model.interfaces.Passenger;
import java.util.Deque;
import java.util.LinkedList;

public class ElevatorController implements Observer{
    
    private Elevator elevator;
    private Deque<Request> requestQueue;
    private RequestQueueManager manager;
    
    public ElevatorController(Elevator elevator){
        this.elevator = elevator;
        requestQueue = new LinkedList<Request>();
        manager = new RequestQueueManager();
        elevator.getButtonPanel().addObserver(this);
    }

    @Override
    public void update(Request request) {
        if (requestQueue.isEmpty()){
            if (elevator.getCurrentFloor() > request.getLevel()){
                elevator.setDirection(Direction.DOWN);
            }
            if (elevator.getCurrentFloor() < request.getLevel()){
                elevator.setDirection(Direction.UP);
            }
            if (elevator.getCurrentFloor() == request.getLevel()){
                elevator.setDirection(Direction.STOP);
            }
        }
        if (!checkRequestInQueue(request)){
            requestQueue.add(request);
            manager.sorting(requestQueue, elevator.getCurrentFloor(), elevator.getDirection());
        }
    }
    
    public Elevator getElevator(){
        return elevator;
    }
    
    public Deque getRequestQueue(){
        return requestQueue;
    }
    
    public boolean checkRequestInQueue(Request request){
        for (Request req : requestQueue){
            if (req.equals(request)){
                return true;
            }
        }
        return false;
    }
    
    public boolean checkCurrentFloor(){
        if (!requestQueue.isEmpty()){
            if (requestQueue.getFirst().getLevel() == elevator.getCurrentFloor()){
                requestQueue.remove(requestQueue.getFirst());
                return true;
            }
        }
        return false;
    }
    
    public boolean checkPassengersInElevator(){
        for (Passenger passenger : elevator.getPassengers()){
            if (passenger.getDestinationFloor() == elevator.getCurrentFloor()){
                return true;
            }
        }
        return false;
    }
    
    public boolean calculateNextFloor(){
        if (!requestQueue.isEmpty()){
            if (elevator.getCurrentFloor() == elevator.getNextFloor() || elevator.getNextFloor() == 0){
                elevator.setNextFloor(requestQueue.getFirst().getLevel());
                calculateDirection();
            }
        } else{
            return false;
        }
        return true;
    }
    
    private void calculateDirection(){
        if (elevator.getCurrentFloor() > elevator.getNextFloor()){
            elevator.setDirection(Direction.DOWN);
        }
        if (elevator.getCurrentFloor() < elevator.getNextFloor()){
            elevator.setDirection(Direction.UP);
        }
    }
    
    public boolean checkElevatorEnd(){
        if (requestQueue.isEmpty() && elevator.getPassengerCount() == 0){
            return true;
        }
        return false;
    }
}
