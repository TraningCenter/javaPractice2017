package com.alegerd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Lift
 */
public class Lift {
    public Integer number;

    private Integer floorNumber;
    private Integer maxWeight;
    private Integer maxFloor;
    private List<Integer> floorsToVisit = new ArrayList<>();
    private List<Person> peopleIn = new ArrayList<>();
    private List<Floor> floors = new ArrayList<>();
    private PriorityQueue<Floor> floorsQueue = new PriorityQueue<>();

    @Override
    public String toString() {
        String result;
        result = "Lift number: " + number + ", number of people: " + peopleIn.size() + "\n";
        for (Person p :
                peopleIn) {
            result += "   " + p.toString() + "\n";
        }
        return result;
    }

    /**
     * Takes people from chosen floor
     * @param floor chosen floor
     */
    public void takePeopleFromFloor(Floor floor){

    }

    /**
     * Adds new floor number to priority queue
     * @param floorNumber new floor number
     */
    public void addFloorToVisit(Integer floorNumber){

    }

    /**
     * Moves lift to next floor from priority queue
     */
    public void moveToNextFloor(){

    }

    /**
     * Invokes when person pushes a floor-number-button
     * in lift to choose the floor he wants to go to
     */
    public void liftButtonPushed(){

    }

    /**
     * Invokes when person calls the lift from floor
     */
    public void callingButtonPushed(){

    }
}
