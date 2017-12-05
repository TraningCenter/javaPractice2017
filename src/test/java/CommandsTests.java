import com.netcracker.unc.commands.building.CallElevatorBuildingCommand;
import com.netcracker.unc.commands.elevator.LoadPassengersElevatorCommand;
import com.netcracker.unc.commands.elevator.MoveElevatorCommand;
import com.netcracker.unc.commands.elevator.UnLoadPassengersElevatorCommand;
import com.netcracker.unc.commands.floor.AddNewPassengerFloorCommand;
import com.netcracker.unc.logic.*;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Tests that verify the correct operation of commands
 */
public class CommandsTests {

    private List<Floor> floors;
    private IElevator elevator;

    @Before
    public void init() {
        floors = new ArrayList<>();
        for (int i = 1; i <= 8; i++)
            floors.add(new Floor(i));
        elevator = new Elevator(0, floors.get(3), floors, 300);
    }

    @Test
    public void UnLoadPassengersElevatorCommand() {
        elevator.addFloorInQueue(floors.get(7));
        elevator.addPassenger(new Passenger(floors.get(0), floors.get(7), 30, 10));
        elevator.addPassenger(new Passenger(floors.get(0), floors.get(3), 30, 0));
        elevator.addPassenger(new Passenger(floors.get(2), floors.get(3), 40, 0));
        UnLoadPassengersElevatorCommand command = new UnLoadPassengersElevatorCommand(elevator);
        command.execute();
        Assert.assertEquals(1, elevator.getPassengers().size());
        Assert.assertEquals(floors.get(7), elevator.getPassengers().get(0).getDestinationFloor());
    }

    @Test
    public void LoadPassengersElevatorCommand() {
        Floor floor = floors.get(3);
        Queue<WaitingCall> waitingCalls = new LinkedList<WaitingCall>();
        floor.addPassenger(new Passenger(floors.get(3), floors.get(5), 100, 0));
        floor.addPassenger(new Passenger(floors.get(3), floors.get(5), 100, 0));
        floor.addPassenger(new Passenger(floors.get(3), floors.get(7), 80, 0));
        floor.addPassenger(new Passenger(floors.get(3), floors.get(6), 30, 0));
        floor.addPassenger(new Passenger(floors.get(3), floors.get(6), 20, 100));
        LoadPassengersElevatorCommand command = new LoadPassengersElevatorCommand(elevator, waitingCalls);
        command.execute();
        Assert.assertEquals(3, elevator.getPassengers().size());
        Assert.assertEquals(2, floor.getPassengers().size());
        Assert.assertEquals(floors.get(5), elevator.getNextDestinationFloor());
        Assert.assertEquals(2, elevator.getFloorsToVisit().size());
        Assert.assertEquals(2, waitingCalls.size());
    }

    @Test
    public void MoveElevatorCommand() {
        elevator.addFloorInQueue(floors.get(4));
        new MoveElevatorCommand(elevator, floors).execute();
        Assert.assertEquals(floors.get(4), elevator.getCurrentFloor());
        elevator.addFloorInQueue(floors.get(1));
        new UnLoadPassengersElevatorCommand(elevator).execute();
        new LoadPassengersElevatorCommand(elevator, new LinkedList<WaitingCall>()).execute();
        new MoveElevatorCommand(elevator, floors).execute();
        Assert.assertEquals(floors.get(3), elevator.getCurrentFloor());
        Assert.assertEquals(State.DOWN, elevator.getState());
    }

    @Test
    public void CallElevatorBuildingCommand() {
        List<IElevator> elevators = new ArrayList<IElevator>();
        Queue<WaitingCall> waitingCalls = new LinkedList<WaitingCall>();
        elevators.add(elevator);
        elevators.add(new Elevator(1, floors.get(7), floors, 300));
        elevators.add(new Elevator(2, floors.get(0), floors, 300));
        elevator.addFloorInQueue(floors.get(7));
        Floor floor = floors.get(4);
        IPassenger passenger = new Passenger(floors.get(4), floors.get(6));
        floor.addPassenger(passenger);
        new CallElevatorBuildingCommand(elevators, floor, passenger.getDestinationFloor(), passenger.getDirection(), waitingCalls).execute();
        Assert.assertEquals(floors.get(4), elevator.getNextDestinationFloor());

        floor = floors.get(1);
        passenger = new Passenger(floors.get(1), floors.get(6));
        floor.addPassenger(passenger);
        new CallElevatorBuildingCommand(elevators, floor, passenger.getDestinationFloor(), passenger.getDirection(), waitingCalls).execute();
        Assert.assertEquals(floors.get(1), elevators.get(2).getNextDestinationFloor());

        floor = floors.get(3);
        passenger = new Passenger(floors.get(3), floors.get(0));
        floor.addPassenger(passenger);
        new CallElevatorBuildingCommand(elevators, floor, passenger.getDestinationFloor(), passenger.getDirection(), waitingCalls).execute();
        Assert.assertEquals(floors.get(3), elevators.get(1).getNextDestinationFloor());
    }

    @Test
    public void CallElevatorToProhibitedFloor() {
        List<IElevator> elevators = new ArrayList<IElevator>();
        elevators.add(elevator);
        List<Floor> availableFloors = new ArrayList<Floor>(floors);
        availableFloors.remove(floors.get(7));
        elevators.add(new Elevator(1, floors.get(6), availableFloors, 300));
        IPassenger passenger = new Passenger(floors.get(7), floors.get(1));
        Queue<WaitingCall> waitingCalls = new LinkedList<WaitingCall>();
        new CallElevatorBuildingCommand(elevators, passenger.getStartFloor(), passenger.getDestinationFloor(), passenger.getDirection(), waitingCalls).execute();
        Assert.assertEquals(floors.get(7), elevator.getNextDestinationFloor());

        elevators.remove(elevator);
        elevators.add(new Elevator(2, floors.get(6), availableFloors, 300));
        new CallElevatorBuildingCommand(elevators, passenger.getStartFloor(), passenger.getDestinationFloor(), passenger.getDirection(), waitingCalls).execute();
        Assert.assertEquals(0, waitingCalls.size());
    }

    @Test
    public void LoadPassengersElevatorCommandWithProbabilityOfChoice() {
        Floor floor = floors.get(3);
        Queue<WaitingCall> waitingCalls = new LinkedList<WaitingCall>();
        floor.addPassenger(new Passenger(floors.get(3), floors.get(6), 30, 0));
        floor.addPassenger(new Passenger(floors.get(3), floors.get(6), 20, 100));
        new LoadPassengersElevatorCommand(elevator, waitingCalls).execute();
        Assert.assertEquals(1, elevator.getPassengers().size());
        Assert.assertEquals(1, floor.getPassengers().size());
        Assert.assertEquals(1, waitingCalls.size());
    }

    @Test
    public void AddNewPassengerFloorCommand() {
        Floor floor = floors.get(0);
        new AddNewPassengerFloorCommand(new Passenger(floors.get(0), floors.get(5)), floor).execute();
        Assert.assertEquals(1, floor.getPassengers().size());
    }
}
