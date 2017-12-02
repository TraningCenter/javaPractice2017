package tadite.javase.elevatorSimulator.model.elevator;

import tadite.javase.elevatorSimulator.model.misc.Observable;

public interface Elevator extends TransportClientKeeper, Observable {
    void pushButton(int level);
}
