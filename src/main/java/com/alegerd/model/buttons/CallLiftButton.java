package com.alegerd.model.buttons;

import com.alegerd.Direction;
import com.alegerd.model.Lift;
import com.alegerd.model.interfaces.ILift;

public class CallLiftButton implements ICallLiftButton{

    ILift lift;
    Integer floorNumber;

    public CallLiftButton(Integer floorNumber, ILift lift) {
        this.floorNumber = floorNumber;
        this.lift = lift;
    }

    @Override
    public void push(Direction direction) {
        lift.callingButtonPushed(floorNumber, direction);
    }
}
