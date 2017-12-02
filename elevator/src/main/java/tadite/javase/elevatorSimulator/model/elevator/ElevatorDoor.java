package tadite.javase.elevatorSimulator.model.elevator;

import tadite.javase.elevatorSimulator.model.misc.Observable;

public interface ElevatorDoor extends Observable {
    void pushButton();
    ElevatorConfig getConfig();
    Elevator getElevator();
    ElevatorDoorStatus getDoorStatus();
}
