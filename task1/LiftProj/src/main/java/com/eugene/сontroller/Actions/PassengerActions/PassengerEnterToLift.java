package com.eugene.сontroller.Actions.PassengerActions;

import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Entities.Passenger;
import com.eugene.сontroller.Listeners.PassengerListener;

/**
 * Class for passenger enter to lift
 */
public class PassengerEnterToLift implements PassengerAction {

    private Lift lift;
    private Passenger passenger;
    private PassengerListener listener;

    public PassengerEnterToLift(Lift lift, Passenger passenger, PassengerListener listener) {
        this.lift = lift;
        this.passenger = passenger;
        this.listener = listener;
    }

    @Override
    public void execute() {
        passenger.enterToLift();
        lift.letinPassenger(passenger);
        listener.passengerEnteredToLift(lift, passenger);
    }
}
