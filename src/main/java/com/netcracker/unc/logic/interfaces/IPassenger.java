package com.netcracker.unc.logic.interfaces;

import com.netcracker.unc.logic.State;

public interface IPassenger {
    int getStartFloor();

    int getDestinationFloor();

    int getWeight();

    State getDirection();
}
