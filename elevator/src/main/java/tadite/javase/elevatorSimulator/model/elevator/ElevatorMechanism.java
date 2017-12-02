package tadite.javase.elevatorSimulator.model.elevator;

public interface ElevatorMechanism {
    void moveUp();
    void moveDown();
    void onDoorOpened();
    int getLevel();
}
