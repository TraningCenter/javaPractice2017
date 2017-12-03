package tadite.javase.elevatorSimulator.controller;

/**
 * POJO for elevator create config
 */
public class ElevatorCreateConfig {
    private int minLevel;
    private int maxLevel;
    private int position;
    private int elevatorStartLevel;

    public ElevatorCreateConfig(int minLevel, int maxLevel, int position, int elevatorStartLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.position = position;
        this.elevatorStartLevel = elevatorStartLevel;
    }

    public int getElevatorStartLevel() {
        return elevatorStartLevel;
    }

    public int getPosition() {
        return position;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getMinLevel() {
        return minLevel;
    }
}
