package com.alegerd.model.interfaces;


import com.alegerd.model.Direction;

import java.util.Iterator;
import java.util.function.Consumer;

public interface ILift {
    public String toString();

    /**
     * Moves lift on floor
     */
     void moveOneFloor();

     void thinkWhereToGo();

     void arrived();

    /**
     * Invokes when person pushes a floor-number-button
     * in lift to choose the floor he wants to go to
     */
     void liftButtonPushed(Integer floor);

     Integer getNumber();

     Integer getNumberOfPeople();

     Integer getFloorLiftOn();
    /**
     * Invokes when person calls the lift from floor
     */
     void callingButtonPushed(Integer floorNumber, Direction direction);

     Direction getCurrentDirection();

}
