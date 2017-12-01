package logic.interfaces.buttons;

import logic.Floor;

public interface IElevatorButton extends IButton {

    void goToFloor(Floor floor);
}
