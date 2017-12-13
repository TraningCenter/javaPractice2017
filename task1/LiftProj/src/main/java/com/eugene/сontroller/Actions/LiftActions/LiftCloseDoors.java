package com.eugene.сontroller.Actions.LiftActions;

import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Listeners.LiftListener;

/**
 * Class for closing the elevator doors
 */
public class LiftCloseDoors implements LiftAction {

    private Lift lift;
    private LiftListener listener;

    public LiftCloseDoors(Lift lift, LiftListener listener) {
        this.lift = lift;
        this.listener = listener;
    }

    @Override
    public void execute() {
        lift.setDoorsActions(0);
        listener.liftClosedDoors(lift);
    }
}

