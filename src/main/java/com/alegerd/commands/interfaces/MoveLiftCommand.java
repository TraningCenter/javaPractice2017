package com.alegerd.commands.interfaces;

import com.alegerd.model.interfaces.ILift;

public class MoveLiftCommand implements ILiftCommand{

    ILift lift;

    public MoveLiftCommand(ILift lift){
        this.lift = lift;
    }

    @Override
    public void execute() {
        lift.moveOneFloor();
    }
}
