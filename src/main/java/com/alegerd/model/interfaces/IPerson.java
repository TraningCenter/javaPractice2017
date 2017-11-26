package com.alegerd.model.interfaces;

public interface IPerson {
     String toString();

     /**
     * Chooses the right button from
     * buttonsToCallLift and invokes method push() of it
     */
     void callLift();

     /**
     * Chooses floor button from buttonsInLift
     * It depends on the floor he wants to go to.
     */
     void chooseDestinationFloor();

     Integer getId();

     void setId(Integer id);

     Integer getFloorNumber();

     void setFloorNumber(Integer floorNumber);

     Integer getDestinationFloor();

     void setDestinationFloor(Integer destinationFloor);

     Integer getWeight();

     void setWeight(Integer weight);

      Integer getWaitsForLiftNumber();

      void setWaitsForLiftNumber(Integer waitsForLiftNumber);
}
