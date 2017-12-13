package com.eugene.сontroller.Listeners;

import com.eugene.сontroller.Entities.Button;
import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Entities.Passenger;

/**
 * Interface for subscribers of passengers
 */
public interface PassengerListener {

    void wasPressedButton(Button button);

    void passengerEnteredToLift(Lift lift, Passenger passenger);

    void passengerExitedFromLift(Lift lift, Passenger passenger);

}
