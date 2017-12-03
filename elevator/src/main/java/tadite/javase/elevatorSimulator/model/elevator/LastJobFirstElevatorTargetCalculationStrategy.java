package tadite.javase.elevatorSimulator.model.elevator;

import java.util.List;

/**
 * Very simple target evaluation strategy
 */
public class LastJobFirstElevatorTargetCalculationStrategy implements ElevatorTargetCalculationStrategy {
    @Override
    public ElevatorRequest calculateTargetLevel(List<ElevatorRequest> requests, int currentLevel) {
        return requests.size()>0?requests.get(requests.size()-1):null;
    }
}
