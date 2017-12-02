package tadite.javase.elevatorSimulator.model.elevator;

import java.util.List;

public class LastJobFirstElevatorTargetCalculationStrategy implements ElevatorTargetCalculationStrategy {
    @Override
    public ElevatorRequest calculateTargetLevel(List<ElevatorRequest> requests) {
        return requests.size()>0?requests.get(requests.size()-1):null;
    }
}
