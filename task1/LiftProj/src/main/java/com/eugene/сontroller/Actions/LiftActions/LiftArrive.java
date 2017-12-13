package com.eugene.сontroller.Actions.LiftActions;

import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Listeners.LiftListener;

/**
 * Class for the action of the arrival of the elevator
 */
public class LiftArrive implements LiftAction {

    private Lift lift;
    private LiftListener listener;

    public LiftArrive(Lift lift, LiftListener listener) {
        this.lift = lift;
        this.listener = listener;
    }

    @Override
    public void execute() {
        if (lift.getFloors() != null && lift.getFloors().contains(lift.getFloor()))
            lift.getFloors().remove((Integer) lift.getFloor());
        listener.liftArrived(lift);
    }

}
