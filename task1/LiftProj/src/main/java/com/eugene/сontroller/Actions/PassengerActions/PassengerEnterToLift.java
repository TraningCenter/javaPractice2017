package com.eugene.сontroller.actions.PassengerActions;

import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Entities.Passenger;

/**
 * Class for passenger enter to lift
 */
public class PassengerEnterToLift implements PassengerAction {

    private Lift lift;
    private Passenger passenger;

    public PassengerEnterToLift(Lift lift, Passenger passenger) {
        this.lift = lift;
        this.passenger = passenger;
    }

    @Override
    public void execute() {
        passenger.enterToLift();
        lift.letinPassenger(passenger);
    }
}
