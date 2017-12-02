import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import tadite.javase.elevatorSimulator.model.misc.DefaultButton;
import tadite.javase.elevatorSimulator.model.building.DefaultFloorGetter;
import tadite.javase.elevatorSimulator.model.elevator.*;
import tadite.javase.elevatorSimulator.model.floor.DefaultFloor;
import tadite.javase.elevatorSimulator.model.floor.EmptySlot;
import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.floor.Slot;
import tadite.javase.elevatorSimulator.model.passenger.FloorGetter;
import tadite.javase.elevatorSimulator.model.passenger.GoToElevatorDoorPersonState;
import tadite.javase.elevatorSimulator.model.passenger.Person;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PersonTests {

    private Location startLocation;
    private Location targetLocation;
    private ElevatorConfig config;
    private Elevator elevator;
    private List<Floor> floorsList;
    private FloorGetter floorGetter;

    /*
    ___|E|_T_ 2
    _S_| |___ 1
     0  1  2
     */
    @Before
    public void init() {
        startLocation = new Location(0, 1);
        targetLocation = new Location(2, 2);
        config = new ElevatorConfig(1, 2, 1);

        Location elevatorLocation = new Location(1,1);
        RequestManager requestManager = new DefaultRequestManager();
        elevator = new DefaultElevator(elevatorLocation, new DefaultButton(requestManager));

        floorsList=new LinkedList<Floor>();

        floorsList.add(new DefaultFloor(1, new ArrayList<Slot>(){{
            add(new EmptySlot(new Location(0,1)));
            add(new DefaultElevatorDoor(
                    new Location(1,2),
                    config,
                    new DefaultButton(requestManager),
                    elevator
            ));
            add(new EmptySlot(new Location(2,1)));
        }}));

        floorsList.add(new DefaultFloor(2, new ArrayList<Slot>(){{
            add(new EmptySlot(new Location(0,2)));
            add(new DefaultElevatorDoor(
                    new Location(1,2),
                    config,
                    new DefaultButton(requestManager),
                    elevator
            ));
            add(new EmptySlot(new Location(2,2)));
        }}));

        floorGetter = new DefaultFloorGetter(floorsList);
    }

    @Test
    public void canFindElevatorDoorAndWait(){
        //Array
        Person person = new Person(startLocation, floorsList.get(0), targetLocation, floorGetter);
        person.changeState(new GoToElevatorDoorPersonState(person));

        //Act
        person.updateState();
        person.updateState();
        person.updateState();

        //Assert
        Assert.assertEquals(1,person.getCurrentLocation().getPosition());
        Assert.assertEquals(1,person.getCurrentLocation().getLevel());
    }

}
