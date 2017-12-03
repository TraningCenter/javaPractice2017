package tadite.javase.elevatorSimulator.model.elevator;

import tadite.javase.elevatorSimulator.model.misc.Observable;

/**
 * Elevator door logic, can push button, has status
 */
public interface ElevatorDoor extends Observable {
    void pushButton();
    ElevatorConfig getConfig();
    Elevator getElevator();
    ElevatorDoorStatus getDoorStatus();
}
