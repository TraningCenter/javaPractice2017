package com.eugene.сontroller.Actions.ButtonActions;

import com.eugene.сontroller.Entities.Button;
import com.eugene.сontroller.Listeners.ButtonListener;

/**
 * Lift call class
 */
public class ButtonCallLift implements ButtonAction {

    protected Button button;
    private ButtonListener listener;

    public ButtonCallLift(Button button, ButtonListener listener) {
        this.button = button;
        this.listener = listener;
    }

    @Override
    public void execute() {
        button.activate();
        listener.wasCalledLift(button.getFloor(), button.getDirection());
    }
}
