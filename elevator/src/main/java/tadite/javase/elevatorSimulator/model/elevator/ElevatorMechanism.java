package tadite.javase.elevatorSimulator.model.elevator;

/**
 * Elevator mechanism login, can move up/down, give current level
 */
public interface ElevatorMechanism {
    void moveUp();
    void moveDown();
    void onDoorOpened();
    int getLevel();
}
