package com.alegerd.model;

import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.IHouse;
import com.alegerd.model.interfaces.ILift;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * House
 */
public class House implements IHouse{
    private List<ILift> lifts = new ArrayList<>();
    private List<IFloor> floors = new ArrayList<>();

    public House(List<IFloor> floors, List<ILift> lifts){
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
    public void addFloor(IFloor newFloor){
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
    public void addLift(ILift newLift){
        if(newLift == null)
            throw new IllegalArgumentException("argument newLift can't be Null");
        else {
            lifts.add(newLift);
        }
    }

    /**
     *
     * @return String information about class
     */
    @Override
    public String toString(){
        String result = "House \n Number of floors " + getNumberOfFloors() + "\n";
        for (IFloor floor : floors ) {
            result += "   " + floor.toString() + "\n";
        }
        result += "Number of Lifts " + getNumberOfLifts() + "\n";
        for (ILift lift : lifts ) {
            result += "   " + lift.toString() + "\n";
        }
        return result;
    }

    /**
     *
     * @return Iterator for floors in the house
     */
    public Iterator<IFloor> floorIterator() {
        return new Iterator<IFloor>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < floors.size();
            }

            @Override
            public IFloor next() {
                IFloor next = floors.get(i);
                i++;
                return next;
            }
        };
    }

    /**
     *
     * @return Iterator for all lifts in the house
     */
    public Iterator<ILift> liftIterator() {
        return new Iterator<ILift>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < lifts.size();
            }

            @Override
            public ILift next() {
                ILift next = lifts.get(i);
                i++;
                return next;
            }
        };
    }

}
