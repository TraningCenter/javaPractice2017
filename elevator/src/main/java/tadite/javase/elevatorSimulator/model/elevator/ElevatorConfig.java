package tadite.javase.elevatorSimulator.model.elevator;

/**
 * POJO for elevator config
 */
public class ElevatorConfig {
    private int minLevel;
    private int maxLevel;
    private int position;

    public ElevatorConfig(int minLevel, int maxLevel, int position) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.position = position;
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
