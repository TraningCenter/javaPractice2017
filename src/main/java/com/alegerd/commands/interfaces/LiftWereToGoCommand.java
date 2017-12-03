package com.alegerd.commands.interfaces;

import com.alegerd.model.interfaces.ILift;

public class LiftWereToGoCommand implements ILiftCommand{

    ILift lift;

    public LiftWereToGoCommand(ILift lift){
        this.lift = lift;
    }

    @Override
    public void execute() {
        lift.thinkWhereToGo();
    }
}
