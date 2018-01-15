/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.building;

import edu.elevatorsim.model.elevator.Elevator;
import edu.elevatorsim.model.elevator.ElevatorController;
import edu.elevatorsim.model.floor.Floor;
import edu.elevatorsim.model.passenger.Person;
import java.util.ArrayList;


public class Building {
    
    private int floorCount;
    private int elevatorCount;
    private ArrayList<Floor> floors;
    private ArrayList<Elevator> elevators;
    private ArrayList<ElevatorController> controllers;
    
    public Building(int floorCount, int elevatorCount){
        this.floorCount = floorCount;
        this.elevatorCount = elevatorCount;
        floors = new ArrayList<>();
        elevators = new ArrayList<>();
        controllers = new ArrayList<>();
        addFloors(floorCount);
        addElevators(elevatorCount, floorCount);
        addControllers();
    }
    
    private void addFloors(int count){
        for (int i = 0; i < count; i++){
            floors.add(new Floor(i + 1));
        }
    }
    
    private void addElevators(int elCount, int flCount){
        for (int i = 0; i < elCount; i++){
            elevators.add(new Elevator(i, flCount));
        }
    }
    
    private void addControllers(){
        for (Elevator elevator : elevators){
            controllers.add(new ElevatorController(elevator));
        }
    }
    
    public void addPassengersToFloor(int floorNumber, int[] destinations){
        Floor fl = null;
        for (Floor floor : floors){
            if (floor.getLevel() == floorNumber){
                fl = floor;
                break;
            }
        }
        
        if (fl != null){
            for (int i = 0; i < destinations.length; i++){
                fl.addPassenger(new Person(fl.getLevel(), destinations[i]));
            }
        }
    }

    public ArrayList<Elevator> getElevators() {
        return elevators;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }
    
    public Floor getFloor(int number){
        return floors.get(number - 1);
    }

    public ArrayList<ElevatorController> getControllers() {
        return controllers;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public int getElevatorCount() {
        return elevatorCount;
    }
}
