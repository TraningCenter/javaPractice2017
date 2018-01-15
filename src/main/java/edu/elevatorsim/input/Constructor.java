/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.input;

import edu.elevatorsim.command.CommandExecutor;
import edu.elevatorsim.model.building.Building;
import edu.elevatorsim.model.elevator.Dispatcher;
import edu.elevatorsim.model.elevator.Elevator;
import edu.elevatorsim.output.BuildingVisualization;
import java.util.Random;


public class Constructor {
    
    private Building building;
    private BuildingVisualization buildingVis;
    private CommandExecutor executor;
    
    public void build(int floorCount, int elevatorCount){
        building = new Building(floorCount, elevatorCount);
        buildingVis = new BuildingVisualization(floorCount, elevatorCount);
        executor = new CommandExecutor(buildingVis);
        setElevatorsStartPosition();
        Dispatcher.setBuilding(building);
        Dispatcher.setCommandExecutor(executor);
    }
    
    public void addPassengers(int floorNumber, int[] destinations){
        building.addPassengersToFloor(floorNumber, destinations);
        buildingVis.addPassengersOnFloor(floorNumber, destinations.length);
    }
    
    private void setElevatorsStartPosition(){
        Random rand = new Random();
        for (Elevator e : building.getElevators()){
            int num = rand.nextInt(building.getFloorCount()) + 1;
            e.setCurrentFloor(num);
            buildingVis.setElevatorPosition(e.getId(), num);
        }
    }
    
    public Building getBuilding(){
        return building;
    }
    
    public BuildingVisualization getBuildingVisualization(){
        return buildingVis;
    }

    public CommandExecutor getExecutor(){
        return executor;
    }
}
