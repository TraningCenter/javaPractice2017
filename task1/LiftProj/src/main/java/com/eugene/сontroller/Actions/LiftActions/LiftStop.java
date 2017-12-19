package com.eugene.сontroller.actions.LiftActions;

import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Listeners.LiftListener;

/**
 * Class for stopping the elevator
 */
public class LiftStop implements LiftAction {

    private Lift lift;
    private LiftListener listener;

    public LiftStop(Lift lift, LiftListener listener) {
        this.lift = lift;
        this.listener = listener;
    }

    @Override
    public void execute() {
        lift.stop();
        listener.liftStopped(lift);
    }

}
