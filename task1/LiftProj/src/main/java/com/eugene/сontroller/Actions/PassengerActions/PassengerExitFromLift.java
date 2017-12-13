package com.eugene.сontroller.Actions.PassengerActions;

import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Entities.Passenger;
import com.eugene.сontroller.Listeners.PassengerListener;

/**
 * Class for passenger exit from lift
 */
public class PassengerExitFromLift implements PassengerAction {

    private Lift lift;
    private Passenger passenger;
    private PassengerListener listener;

    public PassengerExitFromLift(Lift lift, Passenger passenger, PassengerListener listener) {
        this.lift = lift;
        this.passenger = passenger;
        this.listener = listener;
    }

    @Override
    public void execute() {
        passenger.exitFromLift();
        passenger.setDirection(0);
        listener.passengerExitedFromLift(lift, passenger);
    }
}