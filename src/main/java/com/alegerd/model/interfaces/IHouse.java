package com.alegerd.model.interfaces;

import com.alegerd.model.Floor;
import com.alegerd.model.Lift;

import java.util.Iterator;
import java.util.function.Consumer;

public interface IHouse {
    /**
     *
     * @return The number of floors in the house
     */
     Integer getNumberOfFloors();
    /**
     *
     * @return The number of lifts in the house
     */
     Integer getNumberOfLifts();
    /**
     * Adds new Floor to the House
     * @param newFloor adding floor
     */
     void addFloor(IFloor newFloor);
    /**
     * Adds new Lift to the House
     * @param newLift adding floor
     */
     void addLift(ILift newLift);

     Iterator<IFloor> floorIterator();

    Iterator<ILift> liftIterator();
}
