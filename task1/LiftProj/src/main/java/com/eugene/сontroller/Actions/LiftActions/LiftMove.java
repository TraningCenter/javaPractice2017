package com.eugene.сontroller.Actions.LiftActions;

import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Listeners.LiftListener;

/**
 * Class for moving the elevator
 */
public class LiftMove implements LiftAction {

    private LiftListener listener;
    private Lift lift;

    public LiftMove(Lift lift, LiftListener listener) {
        this.lift = lift;
        this.listener = listener;
    }

    @Override
    public void execute() {
        lift.move();
        listener.liftMoved(lift);
    }
}
