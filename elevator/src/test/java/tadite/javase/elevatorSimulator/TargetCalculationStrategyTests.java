package tadite.javase.elevatorSimulator;

import org.junit.Assert;
import org.junit.Test;
import tadite.javase.elevatorSimulator.model.elevator.ElevatorRequest;
import tadite.javase.elevatorSimulator.model.elevator.ShortestJobFirstElevatorTargetCalculationStrategy;

import java.util.LinkedList;
import java.util.List;

public class TargetCalculationStrategyTests {

    @Test
    public void canCalcShortestJobStrategy() {
        //Array
        ShortestJobFirstElevatorTargetCalculationStrategy shortestJobFirstElevatorTargetCalculationStrategy = new ShortestJobFirstElevatorTargetCalculationStrategy();
        List<ElevatorRequest> elevatorRequestList = new LinkedList<>();
        elevatorRequestList.add(new ElevatorRequest(5));
        elevatorRequestList.add(new ElevatorRequest(2));
        elevatorRequestList.add(new ElevatorRequest(1));
        elevatorRequestList.add(new ElevatorRequest(7));
        elevatorRequestList.add(new ElevatorRequest(4));
        elevatorRequestList.add(new ElevatorRequest(1));
        elevatorRequestList.add(new ElevatorRequest(7));

        //Act
        ElevatorRequest elevatorRequest = shortestJobFirstElevatorTargetCalculationStrategy.calculateTargetLevel(elevatorRequestList, 4);

        //Assert
        Assert.assertEquals(4L, elevatorRequest.getLevel().longValue());
    }
}
