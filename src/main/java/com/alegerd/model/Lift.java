package com.alegerd.model;

import com.alegerd.Direction;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.ILift;
import com.alegerd.model.interfaces.IPerson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Consumer;
/**
 * Lift
 */
public class Lift implements ILift{
    public Integer number;

    private Integer floorNumber;
    private Integer maxWeight;
    private Integer maxFloor;
    private List<Integer> floorsToVisit = new ArrayList<>();
    private List<IPerson> peopleIn = new ArrayList<>();
    private List<IFloor> floors = new ArrayList<>();
    private PriorityQueue<IFloor> floorsQueue = new PriorityQueue<>();

    public Lift(Integer number, Integer floorNumber){
        this.number = number;
        this.floorNumber = floorNumber;
    }

    public Lift(Integer number){
        this.number = number;
        this.floorNumber = 0;
    }

    @Override
    public String toString() {
        String result;
        result = "Lift number: " + number + ", number of people: " + peopleIn.size() + "\n";
        for (IPerson p :
                peopleIn) {
            result += "   " + p.toString() + "\n";
        }
        return result;
    }

    /**
     * Takes people from chosen floor
     * @param floor chosen floor
     */
    public void takePeopleFromFloor(IFloor floor){

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

    @Override
    public Integer getNumberOfPeople() {
        return peopleIn.size();
    }

    @Override
    public Integer getFloorLiftOn() {
        return floorNumber;
    }

    /**
     * Invokes when person calls the lift from floor
     */
    public void callingButtonPushed(Integer floorNumber, Direction direction){
        //System.out.print("Лифт " + number + " Вызов с этажа " + floorNumber);
        //String s = direction.equals(Direction.UP) ? " вверх" : " вниз";
        //System.out.println(s);
    }

    @Override
    public Iterator<IPerson> getPeopleIterator() {
        return new Iterator<IPerson>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < peopleIn.size();
            }

            @Override
            public IPerson next() {
                IPerson next = peopleIn.get(i);
                i++;
                return next;
            }
        };
    }

    @Override
    public void forEachPerson(Consumer<? super IPerson> action) {
        for (IPerson p :
                peopleIn) {
            action.accept(p);
        }
    }
}
