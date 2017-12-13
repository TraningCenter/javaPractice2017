package com.eugene.сontroller.Actions.ButtonActions;

import com.eugene.сontroller.Entities.Button;

/**
 * Button switch off class
 */
public class ButtonTurnsOff implements ButtonAction {

    protected Button button;

    public ButtonTurnsOff(Button button) {
        this.button = button;
    }

    @Override
    public void execute() {
        button.deactivate();
    }
}
