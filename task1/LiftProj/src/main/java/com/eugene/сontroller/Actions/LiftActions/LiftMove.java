package com.eugene.сontroller.actions.LiftActions;

import com.eugene.сontroller.Entities.Lift;

/**
 * Class for moving the elevator
 */
public class LiftMove implements LiftAction {

    private Lift lift;

    public LiftMove(Lift lift) {
        this.lift = lift;
    }

    @Override
    public void execute() {
        lift.move();
    }
}
