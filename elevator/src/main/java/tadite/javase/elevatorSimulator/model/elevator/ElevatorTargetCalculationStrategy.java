package tadite.javase.elevatorSimulator.model.elevator;

import java.util.List;

public interface ElevatorTargetCalculationStrategy {
    ElevatorRequest calculateTargetLevel(List<ElevatorRequest> requests);
}
