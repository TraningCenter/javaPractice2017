package com.alegerd.model.interfaces;

import com.alegerd.model.Floor;
import com.alegerd.model.Lift;

public interface IHouse {
    /**
     *
     * @return The number of floors in the house
     */
    public Integer getNumberOfFloors();
    /**
     *
     * @return The number of lifts in the house
     */
    public Integer getNumberOfLifts();
    /**
     * Adds new Floor to the House
     * @param newFloor adding floor
     */
    public void addFloor(Floor newFloor);
    /**
     * Adds new Lift to the House
     * @param newLift adding floor
     */
    public void addLift(Lift newLift);
}
