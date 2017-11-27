package com.alegerd.commands.interfaces;

import com.alegerd.model.interfaces.ILift;

public class LiftWereToGoCommand implements ILiftCommand{

    ILift lift;

    public LiftWereToGoCommand(ILift lift){
        this.lift = lift;
    }

    @Override
    public void setLift(ILift lift) {

    }

    @Override
    public void execute() {
        System.out.println("лифт думает");
        lift.thinkWhereToGo();
    }
}
