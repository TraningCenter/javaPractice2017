package com.alegerd.model.interfaces;


import com.alegerd.Direction;
import com.alegerd.model.Floor;

import java.util.Iterator;
import java.util.function.Consumer;

public interface ILift {
    public String toString();

    /**
     * Takes people from chosen floor
     * @param floor chosen floor
     */
     void takePeopleFromFloor(IFloor floor);

    /**
     * Adds new floor number to priority queue
     * @param floorNumber new floor number
     */
     void addFloorToVisit(Integer floorNumber);
    /**
     * Moves lift to next floor from priority queue
     */
     void moveToNextFloor();

    /**
     * Invokes when person pushes a floor-number-button
     * in lift to choose the floor he wants to go to
     */
     void liftButtonPushed();

     Integer getNumberOfPeople();

     Integer getFloorLiftOn();
    /**
     * Invokes when person calls the lift from floor
     */
     void callingButtonPushed(Direction direction);

     Iterator<IPerson> getPeopleIterator();

     void forEachPerson(Consumer<? super IPerson> action);
}
