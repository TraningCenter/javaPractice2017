package com.eugene.сontroller.Actions.LiftActions;

import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Listeners.LiftListener;

/**
 * Class for opening the elevator doors
 */
public class LiftOpenDoors implements LiftAction {

    private Lift lift;
    private LiftListener listener;

    public LiftOpenDoors(Lift lift, LiftListener listener) {
        this.lift = lift;
        this.listener = listener;
    }

    @Override
    public void execute() {
        listener.liftOpenedDoors(lift);
    }
}
