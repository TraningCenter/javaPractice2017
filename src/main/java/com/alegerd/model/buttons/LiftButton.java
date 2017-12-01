package com.alegerd.model.buttons;

import com.alegerd.model.Lift;

public class LiftButton implements IButton {

    Integer floor;
    Lift lift;

    public LiftButton(Lift lift, Integer floor) {
        this.lift = lift;
        this.floor = floor;
    }

    @Override
    public Integer getFloorNumber() {
        return floor;
    }

    @Override
    public void push() {
        lift.liftButtonPushed(floor);
    }
}
