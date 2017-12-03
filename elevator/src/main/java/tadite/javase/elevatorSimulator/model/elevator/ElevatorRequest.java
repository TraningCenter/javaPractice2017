package tadite.javase.elevatorSimulator.model.elevator;

/**
 * POJO for elevator request
 */
public class ElevatorRequest {
    private Integer level;

    public ElevatorRequest(int level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }
}
