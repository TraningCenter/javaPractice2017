package com.alegerd.model;

import com.alegerd.model.interfaces.IHouse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * House
 */
public class House implements IHouse{
    private List<Lift> lifts = new ArrayList<>();
    private List<Floor> floors = new ArrayList<>();

    public House(List<Floor> floors, List<Lift> lifts){
        this.floors = floors;
        this.lifts = lifts;
    }

    /**
     *
     * @return The number of floors in the house
     */
    public Integer getNumberOfFloors(){
        return floors.size();
    }
    /**
     *
     * @return The number of lifts in the house
     */
    public Integer getNumberOfLifts(){
        return lifts.size();
    }
    /**
     * Adds new Floor to the House
     * @param newFloor adding floor
     */
    public void addFloor(Floor newFloor){
        if(newFloor == null)
            throw new IllegalArgumentException("argument newFloor can't be Null");
        else {
            floors.add(newFloor);
        }
    }
    /**
     * Adds new Lift to the House
     * @param newLift adding floor
     */
    public void addLift(Lift newLift){
        if(newLift == null)
            throw new IllegalArgumentException("argument newLift can't be Null");
        else {
            lifts.add(newLift);
        }
    }

    @Override
    public String toString(){
        String result = "House \n Number of floors " + getNumberOfFloors() + "\n";
        for (Floor floor : floors ) {
            result += "   " + floor.toString() + "\n";
        }
        result += "Number of Lifts " + getNumberOfLifts() + "\n";
        for (Lift lift : lifts ) {
            result += "   " + lift.toString() + "\n";
        }
        return result;
    }

    public Iterator<Floor> floorIterator() {
        return new Iterator<Floor>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < floors.size();
            }

            @Override
            public Floor next() {
                Floor next = floors.get(i);
                i++;
                return next;
            }
        };
    }

    public void forEach(Consumer<? super Floor> action) {
        for (Floor floor : floors){
            action.accept(floor);
        }
    }

}
