package com.eugene.сontroller.Actions.PassengerActions;

import com.eugene.сontroller.Entities.Button;
import com.eugene.сontroller.Listeners.PassengerListener;

/**
 * Class for passenger press button
 */
public class PassengerPressButton implements PassengerAction {

    protected Button button;
    protected PassengerListener listener;

    public PassengerPressButton(Button button, PassengerListener listener) {
        this.button = button;
        this.listener = listener;
    }

    @Override
    public void execute() {
        listener.wasPressedButton(button);
    }
}
