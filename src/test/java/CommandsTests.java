import com.netcracker.unc.commands.CommandManager;
import com.netcracker.unc.commands.building.CallElevatorBuildingCommand;
import com.netcracker.unc.commands.elevator.LoadPassengersElevatorCommand;
import com.netcracker.unc.commands.elevator.MoveElevatorCommand;
import com.netcracker.unc.commands.elevator.UnLoadPassengersElevatorCommand;
import com.netcracker.unc.commands.floor.AddNewPassengerFloorCommand;
import com.netcracker.unc.commands.vizualizer.*;
import com.netcracker.unc.logic.Elevator;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.Passenger;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;
import com.netcracker.unc.visualization.VisualizerConfig;
import com.netcracker.unc.visualization.elements.ElevatorPicture;
import com.netcracker.unc.visualization.elements.FloorPicture;
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
    public void unLoadPassengersElevatorCommand() {
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
    public void loadPassengersElevatorCommand() {
        Floor floor = floors.get(3);
        Queue<IPassenger> waitingCalls = new LinkedList<>();
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
    public void moveElevatorCommand() {
        elevator.addFloorInQueue(floors.get(4));
        new MoveElevatorCommand(elevator, floors).execute();
        Assert.assertEquals(floors.get(4), elevator.getCurrentFloor());
        elevator.addFloorInQueue(floors.get(1));
        new UnLoadPassengersElevatorCommand(elevator).execute();
        new LoadPassengersElevatorCommand(elevator, new LinkedList<>()).execute();
        new MoveElevatorCommand(elevator, floors).execute();
        Assert.assertEquals(floors.get(3), elevator.getCurrentFloor());
        Assert.assertEquals(State.DOWN, elevator.getState());
        elevator.setState(State.STOPPED);
        new MoveElevatorCommand(elevator, floors).execute();
        Assert.assertEquals(floors.get(3), elevator.getCurrentFloor());
    }

    @Test
    public void callElevatorBuildingCommand() {
        List<IElevator> elevators = new ArrayList<IElevator>();
        Queue<IPassenger> waitingCalls = new LinkedList<>();
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

        floors.get(3).setPushedButtonUp(true);
        new CallElevatorBuildingCommand(elevators, floor, passenger.getDestinationFloor(), passenger.getDirection(), waitingCalls).execute();
        Assert.assertEquals(1, elevators.get(1).getFloorsToVisit().size());
    }

    @Test
    public void callElevatorToProhibitedFloor() {
        List<IElevator> elevators = new ArrayList<IElevator>();
        elevators.add(elevator);
        List<Floor> availableFloors = new ArrayList<Floor>(floors);
        availableFloors.remove(floors.get(7));
        elevators.add(new Elevator(1, floors.get(6), availableFloors, 300));
        IPassenger passenger = new Passenger(floors.get(7), floors.get(1));
        Queue<IPassenger> waitingCalls = new LinkedList<>();
        new CallElevatorBuildingCommand(elevators, passenger.getStartFloor(), passenger.getDestinationFloor(), passenger.getDirection(), waitingCalls).execute();
        Assert.assertEquals(floors.get(7), elevator.getNextDestinationFloor());

        elevators.remove(elevator);
        elevators.add(new Elevator(2, floors.get(6), availableFloors, 300));
        new CallElevatorBuildingCommand(elevators, passenger.getStartFloor(), passenger.getDestinationFloor(), passenger.getDirection(), waitingCalls).execute();
        Assert.assertEquals(0, waitingCalls.size());
    }

    @Test
    public void loadPassengersElevatorCommandWithProbabilityOfChoice() {
        Floor floor = floors.get(3);
        Queue<IPassenger> waitingCalls = new LinkedList<>();
        floor.addPassenger(new Passenger(floors.get(3), floors.get(6), 30, 0));
        floor.addPassenger(new Passenger(floors.get(3), floors.get(6), 20, 100));
        floors.get(3).setPushedButtonUp(true);
        new LoadPassengersElevatorCommand(elevator, waitingCalls).execute();
        Assert.assertEquals(1, elevator.getPassengers().size());
        Assert.assertEquals(1, floor.getPassengers().size());
        Assert.assertEquals(1, waitingCalls.size());
        elevator.setCurrentFloor(floors.get(0));
        elevator.setState(State.DOWN);
        floors.get(0).setPushedButtonDown(true);
        new LoadPassengersElevatorCommand(elevator, waitingCalls).execute();
        Assert.assertFalse(floors.get(0).isPushedButtonDown());
        elevator.setState(State.STOPPED);
        floors.get(0).setPushedButtonDown(true);
        floors.get(0).setPushedButtonUp(true);
        new LoadPassengersElevatorCommand(elevator, waitingCalls).execute();
        Assert.assertFalse(floors.get(0).isPushedButtonDown());
        Assert.assertFalse(floors.get(0).isPushedButtonUp());
    }

    @Test
    public void addNewPassengerFloorCommand() {
        Floor floor = floors.get(0);
        new AddNewPassengerFloorCommand(new Passenger(floors.get(0), floors.get(5)), floor).execute();
        Assert.assertEquals(1, floor.getPassengers().size());
    }

    @Test
    public void closeAndOpenDoorsCommand() {
        ElevatorPicture elevatorPicture = new ElevatorPicture(elevator.getId(), 1, 0, false);
        new OpenDoorsVisualizerCommand(elevator, elevatorPicture).execute();
        Assert.assertTrue(elevator.isOpened());
        Assert.assertTrue(elevatorPicture.isOpened());

        new CloseDoorsVisualizerCommand(elevator, elevatorPicture).execute();
        Assert.assertFalse(elevator.isOpened());
        Assert.assertFalse(elevatorPicture.isOpened());
    }

    @Test
    public void moveElevatorVisualizerCommand() {
        ElevatorPicture elevatorPicture = new ElevatorPicture(elevator.getId(), 13, 0, false);
        FloorPicture floorPicture = new FloorPicture(elevator.getCurrentFloor().getId(), (floors.size() - elevator.getCurrentFloor().getId()) * VisualizerConfig.FLOOR_HEIGHT + 1, 0, false, false);
        Assert.assertTrue(elevator.isInFloor());
        new MoveElevatorVisualizerCommand(elevator, elevatorPicture, floorPicture, floors).execute();
        Assert.assertEquals(13, elevatorPicture.getYCoordinate());
        // движение вниз
        elevator.addFloorInQueue(floors.get(0));
        new MoveElevatorVisualizerCommand(elevator, elevatorPicture, floorPicture, floors).execute();
        Assert.assertEquals(14, elevatorPicture.getYCoordinate());
        Assert.assertFalse(elevator.isInFloor());
        for (int i = 1; i < VisualizerConfig.ELEVATOR_HEIGHT; i++)
            new MoveElevatorVisualizerCommand(elevator, elevatorPicture, floorPicture, floors).execute();
        Assert.assertEquals(16, elevatorPicture.getYCoordinate());
        Assert.assertEquals(floors.get(2), elevator.getCurrentFloor());
        //двигаемся вверх
        elevatorPicture = new ElevatorPicture(elevator.getId(), 13, 0, false);
        elevator.setCurrentFloor(floors.get(3));
        elevator.deleteFloorFromQueue();
        elevator.addFloorInQueue(floors.get(5));
        new MoveElevatorVisualizerCommand(elevator, elevatorPicture, floorPicture, floors).execute();
        Assert.assertEquals(12, elevatorPicture.getYCoordinate());
    }

    @Test
    public void updateFloorVisualizerCommand() {
        Floor floor = floors.get(3);
        FloorPicture floorPicture = new FloorPicture(floor.getId(), (floors.size() - floor.getId()) * VisualizerConfig.FLOOR_HEIGHT + 1, 0, false, false);
        floor.setPushedButtonDown(true);
        floor.addPassenger(new Passenger(floor, floors.get(0)));
        new UpdateFloorVisualizerCommand(floor, floorPicture).execute();
        Assert.assertTrue(floorPicture.isPushedDown());
        Assert.assertFalse(floorPicture.isPushedUp());
        Assert.assertEquals(1, floorPicture.getCountOfPassengers());
    }

    @Test
    public void loadPassengersVisualizerCommand() {
        ElevatorPicture elevatorPicture = new ElevatorPicture(elevator.getId(), 1, 0, false);
        FloorPicture floorPicture = new FloorPicture(elevator.getCurrentFloor().getId(), (floors.size() - elevator.getCurrentFloor().getId()) * VisualizerConfig.FLOOR_HEIGHT + 1, 1, true, false);
        elevator.addPassenger(new Passenger(floors.get(3), floors.get(0)));
        new LoadPassengersVisualizerCommand(elevator, elevatorPicture, floorPicture).execute();
        Assert.assertEquals(1, elevatorPicture.getCountOfPassengers());
        Assert.assertEquals(0, floorPicture.getCountOfPassengers());
        Assert.assertFalse(floorPicture.isPushedUp());
    }

    @Test
    public void commandManagerTests() {
        CommandManager commandManager = new CommandManager();
        commandManager.addCommand(new AddNewPassengerFloorCommand(new Passenger(floors.get(0), floors.get(1)), floors.get(0)));
        Assert.assertNotNull(commandManager.getNextCommand());
        commandManager.executeNextCommand();
        Assert.assertEquals(1, floors.get(0).getPassengers().size());
        Assert.assertTrue(commandManager.isEmpty());
        Assert.assertNull(commandManager.getNextCommand());
    }
}
