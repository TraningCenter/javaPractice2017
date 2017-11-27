package com.alegerd.commands.interfaces;

import com.alegerd.model.interfaces.ILift;

public class LiftArrivedCommand implements ILiftCommand{

    ILift lift;

    public LiftArrivedCommand(ILift lift){
        this.lift = lift;
    }

    @Override
    public void setLift(ILift lift) {

    }

    @Override
    public void execute() {
        System.out.println("лифт приехал");
        lift.arrived();
    }
}
