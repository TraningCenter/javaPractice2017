package com.eugene.сontroller.actions.PassengerActions;

import com.eugene.сontroller.Entities.Passenger;

/**
 * Class for passenger exit from lift
 */
public class PassengerExitFromLift implements PassengerAction {

    private Passenger passenger;

    public PassengerExitFromLift(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public void execute() {
        passenger.setInLift(false);
        passenger.setDirection(0);
    }
}