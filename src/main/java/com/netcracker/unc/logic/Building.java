package com.netcracker.unc.logic;


import com.netcracker.unc.logic.interfaces.IElevator;

import java.util.ArrayList;
import java.util.List;

public class Building {

    private List<IElevator> elevators;
    private List<Floor> floors;

    public Building() {
        elevators = new ArrayList<IElevator>();
        floors = new ArrayList<Floor>();
    }

    public Building(List<IElevator> elevators, List<Floor> floors) {
        this.elevators = elevators;
        this.floors = floors;
    }

    public void addElevator(IElevator elevator) {
        elevators.add(elevator);
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public List<IElevator> getElevators() {
        return elevators;
    }

    public void setElevators(List<IElevator> elevators) {
        this.elevators = elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}
