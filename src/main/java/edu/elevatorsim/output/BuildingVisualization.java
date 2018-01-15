/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.output;

import java.util.ArrayList;

public class BuildingVisualization {
    
    private final int FLOOR_WIDTH = 12;
    private final int FLOOR_HEIGHT = 3;
    
    private int floorCount;
    private int elevatorCount;
    private ArrayList<FloorTemplate> floors;
    private ArrayList<RightFloorTemplate> rightFloors;
    private ArrayList<ElevatorTemplate> elevators;

    public BuildingVisualization(int floorCount, int elevatorCount){
        this.floorCount = floorCount;
        this.elevatorCount = elevatorCount;
        buildingInit();
    }
    
    private void buildingInit(){
        floors = new ArrayList<>();
        rightFloors = new ArrayList<>();
        elevators = new ArrayList<>();
        for (int i = 0; i < elevatorCount; i++){
            elevators.add(new ElevatorTemplate(i, FLOOR_WIDTH, 0));
        }
        int rightFloorX = FLOOR_WIDTH + elevators.get(0).getCoordinates().getWidth() * elevators.size() - 1;
        for (int i = 0; i < floorCount; i++){
            int floorY = (floorCount - i) * FLOOR_HEIGHT - 1;
            floors.add(new FloorTemplate(i + 1, 0, 0, floorY, FLOOR_HEIGHT, FLOOR_WIDTH));
            rightFloors.add(new RightFloorTemplate(i + 1, 0, rightFloorX, floorY, FLOOR_HEIGHT, FLOOR_WIDTH));
        }
    }
    
    public void addPassengersOnFloor(int level, int count){
        floors.get(level - 1).setPassengersCount(count);
    }
    
    public void addPassengersInElevator(int id, int count){
        elevators.get(id).setPassengersCount(count);
    }

    public void addArrivedPassengers(int level, int count){
        rightFloors.get(level - 1).setPassengersCount(count);
    }
    
    public void setElevatorPosition(int id, int level){
        elevators.get(id).setYCoordinate(floors.get(level - 1).getCoordinates().getY());
    }
    
    public char[][] build() {
        int row = floors.size() * FLOOR_HEIGHT;
        int column = FLOOR_WIDTH + elevators.size() * elevators.get(0).getCoordinates().getWidth() + FLOOR_WIDTH;
        char[][] house = new char[row][column];

        for (int f = floors.size() - 1; f >= 0; f--) {
            FloorTemplate fl = floors.get(f);
            RightFloorTemplate rFl = rightFloors.get(f);
            char[][] floor = fl.draw();
            char[][] rightFloor = rFl.draw();
            for (int i = 0; i < floor.length; i++) {
                for (int j = 0; j < floor[0].length; j++) {
                    house[fl.getCoordinates().getY() - i][j] = floor[floor.length - i - 1][j];
                }
            }
            for (int i = 0; i < rightFloor.length; i++) {
                for (int j = column - FLOOR_WIDTH; j < column; j++) {
                    house[rFl.getCoordinates().getY() - i][j] = rightFloor[rightFloor.length - i - 1][j - rFl.getCoordinates().getX() - 1];
                }
            }
        }

        for (ElevatorTemplate el : elevators) {
            char[][] elevator = el.draw();
            for (int i = elevator.length - 1; i >= 0; i--) {
                for (int j = 0; j < elevator.length; j++) {
                    house[el.getCoordinates().getY() - i][el.getCoordinates().getX() + j] = elevator[i][j];
                }
            }
        }

        return house;
    }
}
