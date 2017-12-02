package com.netcracker.unc.logic.interfaces;

import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;

public interface IPassenger {

    Floor getStartFloor();

    void setStartFloor(Floor floor);

    Floor getDestinationFloor();

    void setDestinationFloor(Floor floor);

    int getWeight();

    void setWeight(int weight);

    State getDirection();

    int getProbabilityOfChoice();

    void setProbabilityOfChoice(int probabilityOfChoice);
}
