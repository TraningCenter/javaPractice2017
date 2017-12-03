package tadite.javase.elevatorSimulator.model.elevator;

import java.util.List;

/**
 * Shortest job target evaluation strategy
 */
public class ShortestJobFirstElevatorTargetCalculationStrategy implements ElevatorTargetCalculationStrategy {

    @Override
    public ElevatorRequest calculateTargetLevel(List<ElevatorRequest> requests, int currentLevel) {
        if (requests.size()==0)
            return null;

        ElevatorRequest elevatorRequest = requests.stream().min((o1, o2) -> Math.abs(o1.getLevel() - currentLevel) - Math.abs(o2.getLevel() - currentLevel)).get();

        return elevatorRequest;
    }
}
