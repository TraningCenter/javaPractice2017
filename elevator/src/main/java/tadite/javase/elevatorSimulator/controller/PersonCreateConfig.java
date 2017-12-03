package tadite.javase.elevatorSimulator.controller;

/**
 * POJO for person create config
 */
public class PersonCreateConfig {
    private int startLevel;
    private int startPosition;
    private int targetLevel;
    private int targetPosition;

    public PersonCreateConfig(int startLevel, int startPosition, int targetLevel, int targetPosition) {
        this.startLevel = startLevel;
        this.startPosition = startPosition;
        this.targetLevel = targetLevel;
        this.targetPosition = targetPosition;
    }

    public int getStartLevel() {
        return startLevel;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getTargetLevel() {
        return targetLevel;
    }

    public int getTargetPosition() {
        return targetPosition;
    }
}
