package com.eugene.сontroller.actions.ButtonActions;

import com.eugene.сontroller.Entities.Button;

/**
 * Lift call class
 */
public class ButtonTurnsOn implements ButtonAction {

    protected Button button;

    public ButtonTurnsOn(Button button) {
        this.button = button;
    }

    @Override
    public void execute() {
        button.activate();
    }
}
