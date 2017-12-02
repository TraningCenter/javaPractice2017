import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import tadite.javase.elevatorSimulator.model.misc.Button;
import tadite.javase.elevatorSimulator.model.misc.DefaultButton;
import tadite.javase.elevatorSimulator.model.building.DefaultFloorGetter;
import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.elevator.*;
import tadite.javase.elevatorSimulator.model.floor.DefaultFloor;
import tadite.javase.elevatorSimulator.model.floor.EmptySlot;
import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.floor.Slot;
import tadite.javase.elevatorSimulator.model.passenger.FloorGetter;
import tadite.javase.elevatorSimulator.model.passenger.GoToElevatorDoorPersonState;
import tadite.javase.elevatorSimulator.model.passenger.Person;

import java.util.*;

public class OnePersonWithElevatorTests {
    private ElevatorController elevatorController;
    private RequestManager requestManager;
    private DefaultElevator elevator;
    private Map<Integer, ElevatorDoorMechanism> doorMechanisms;
    private List<Floor> floorsList;
    private FloorGetter floorGetter;
    private Person person;

    /*
    ___|E|_T_ 4
    ___| |___ 3
    ___| |___ 2
    _S_| |___ 1
     0  1  2
     */
    @Before
    public void init() {
        Location startPersonLocation = new Location(0,1);
        Location targetPersonLocation = new Location(2,4);

        Location startElevatorLocation = new Location(1,4);
        ElevatorConfig elevatorConfig = new ElevatorConfig(1,4,1);

        //Elevator init
        requestManager = new DefaultRequestManager();
        Button insideElevatorButton = new DefaultButton(requestManager);
        elevator = new DefaultElevator(startElevatorLocation,insideElevatorButton);

        ElevatorTargetCalculationStrategy targetCalculationStrategy = new LastJobFirstElevatorTargetCalculationStrategy();

        List<DefaultElevatorDoor> elevatorDoors = new LinkedList<>();
        for (int i = 1; i<5; i++){
            final int level = i;
            elevatorDoors.add(new DefaultElevatorDoor(
                new Location(1,level),
                elevatorConfig,
                new DefaultButton(requestManager),
                elevator
            ));
        }

        doorMechanisms = new HashMap<>();
        for (int level = 1; level<5; level++) {
            doorMechanisms.put(level,elevatorDoors.get(level-1));
        }

        elevatorController = new ElevatorController(
                elevatorConfig,
                elevator,
                targetCalculationStrategy,
                doorMechanisms,
                requestManager);

        //Person init
        floorsList=new LinkedList<Floor>();

        for (int i = 1; i<5; i++){
            final int level=i;
            floorsList.add(new DefaultFloor(level, new ArrayList<Slot>(){{
                add(new EmptySlot(new Location(0,level)));
                add(elevatorDoors.get(level-1));
                add(new EmptySlot(new Location(2,level)));
            }}));
        }

        floorGetter = new DefaultFloorGetter(floorsList);

        person = new Person(startPersonLocation, floorsList.get(0), targetPersonLocation, floorGetter);
    }

    @Test
    public void canPersonGoToTargetThroughElevator(){
        //Array
        person.changeState(new GoToElevatorDoorPersonState(person));

        //Act
        for (int i=0;i<10;i++){
            elevatorController.updateState();
            person.updateState();
        }

        //Assert
        Assert.assertEquals(4, person.getCurrentLocation().getLevel());
        Assert.assertEquals(2, person.getCurrentLocation().getPosition());
        Assert.assertEquals(null,person.getKeeper());
        Assert.assertEquals(floorsList.get(3),person.getCurrentFloor());

        Assert.assertEquals(4, elevatorController.getLocation().getLevel());
        Assert.assertEquals(1, elevatorController.getLocation().getPosition());

        Assert.assertEquals(0, elevator.getClientsCount());
        Assert.assertEquals(0, elevator.getObserversCount());
    }

}
