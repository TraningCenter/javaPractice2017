package logic.interfaces;

import logic.State;

public interface IPassenger {
    int getStartFloor();

    int getDestinationFloor();

    int getWeight();

    State getDirection();
}
