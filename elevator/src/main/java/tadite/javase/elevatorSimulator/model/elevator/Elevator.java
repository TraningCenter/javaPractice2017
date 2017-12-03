package tadite.javase.elevatorSimulator.model.elevator;

import tadite.javase.elevatorSimulator.model.misc.Observable;

/**
 * Implements elevator logic, can take clients, be observable, push button
 */
public interface Elevator extends TransportClientKeeper, Observable {
    void pushButton(int level);
}
