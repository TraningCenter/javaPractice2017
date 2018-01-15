/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.command;

import edu.elevatorsim.model.elevator.Elevator;
import edu.elevatorsim.model.floor.Floor;
import edu.elevatorsim.model.interfaces.ICommand;
import edu.elevatorsim.model.interfaces.Passenger;
import java.util.Iterator;


public class GetInElevator implements ICommand{
    
    private Elevator elevator;
    private Floor floor;

    public GetInElevator(Elevator elevator, Floor floor){
        this.elevator = elevator;
        this.floor = floor;
    }

    @Override
    public void execute() {
        floor.setElevatorOnFloor(true);
        Iterator<Passenger> iterator = floor.getPassengers().iterator();
        while (iterator.hasNext()){
            Passenger passenger = iterator.next();
            boolean added = elevator.addPassenger(passenger);
            if (added){
                iterator.remove();
                floor.removePassenger(passenger);
            }
        }
        floor.setElevatorOnFloor(false);
    }

    @Override
    public Elevator getElevator() {
        return elevator;
    }

    @Override
    public Floor getFloor() {
        return floor;
    }
    
}
