package com.alegerd.model.buttons;

import com.alegerd.model.Lift;

public class LiftButton implements IButton {

    Lift lift;

    public LiftButton(Lift lift) {
        this.lift = lift;
    }

    @Override
    public void push() {

    }
}
