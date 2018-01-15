/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.command;

import edu.elevatorsim.model.elevator.Direction;
import edu.elevatorsim.model.elevator.Elevator;
import edu.elevatorsim.model.floor.Floor;
import edu.elevatorsim.model.interfaces.ICommand;


public class MoveCommand implements ICommand{

    private Elevator elevator;
    private Floor floor;
    
    public MoveCommand(Elevator elevator, Floor floor){
        this.elevator = elevator;
        this.floor = floor;
    }
    
    @Override
    public void execute() {
        if (elevator.getCurrentFloor() == 1 || elevator.getCurrentFloor() == elevator.getFloorCount()){
            if (elevator.getCurrentFloor() > elevator.getNextFloor()){
                elevator.setDirection(Direction.DOWN);
            }
            if (elevator.getCurrentFloor() < elevator.getNextFloor()){
                elevator.setDirection(Direction.UP);
            }
            if (elevator.getCurrentFloor() == elevator.getNextFloor()){
                elevator.setDirection(Direction.STOP);
            }
        }
        if (elevator.getDirection().equals(Direction.UP)){
            elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
        }
        if (elevator.getDirection().equals(Direction.DOWN)){
            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
        }
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
