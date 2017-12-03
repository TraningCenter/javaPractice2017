import junit.framework.Assert;
import org.junit.Test;
import tadite.javase.elevatorSimulator.controller.BuildingConfigurator;
import tadite.javase.elevatorSimulator.controller.DefaultBuildingConfigurator;
import tadite.javase.elevatorSimulator.controller.ElevatorCreateConfig;
import tadite.javase.elevatorSimulator.controller.PersonCreateConfig;
import tadite.javase.elevatorSimulator.model.building.Building;
import tadite.javase.elevatorSimulator.model.elevator.*;
import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.floor.SlotType;
import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.passenger.Passenger;
import tadite.javase.elevatorSimulator.model.passenger.Person;

import java.util.*;


public class BuildingConfiguratorTests {

    @Test
    public void canOperateWithCreateConfigs(){
        //Array
        BuildingConfigurator configurator = new DefaultBuildingConfigurator();
        ElevatorCreateConfig elevatorCreateConfig1 = new ElevatorCreateConfig(1,4,1,1);
        ElevatorCreateConfig elevatorCreateConfig2 = new ElevatorCreateConfig(1,4,3,2);
        PersonCreateConfig personCreateConfig1 = new PersonCreateConfig(1,0,4,0);
        PersonCreateConfig personCreateConfig2 = new PersonCreateConfig(2,4,3,0);

        //Act
        configurator.setSlotCount(3);
        configurator.setFloorCount(3);
        configurator.addElevatorCreateConfig(elevatorCreateConfig1);
        configurator.addElevatorCreateConfig(elevatorCreateConfig2);
        configurator.addPersonCreateConfig(personCreateConfig1);
        configurator.addPersonCreateConfig(personCreateConfig2);
        configurator.removePersonCreateConfig(1);
        configurator.removeElevatorCreateConfig(0);

        //Assert
        Iterator<PersonCreateConfig> personCreateConfigIterator = configurator.getPersonCreateConfigs();
        Iterator<ElevatorCreateConfig> elevatorCreateConfigs = configurator.getElevatorCreateConfigs();

        Assert.assertEquals(elevatorCreateConfig2, elevatorCreateConfigs.next());
        Assert.assertEquals(personCreateConfig1, personCreateConfigIterator.next());
        Assert.assertEquals(false, elevatorCreateConfigs.hasNext());
        Assert.assertEquals(false, personCreateConfigIterator.hasNext());
    }

    @Test
    public void canCreateBuildingFromConfigs(){
        //Array
        /*
         ___| |_T_ 3
         ___|E|___ 2
         _S_| |___ 1
          0  1  2
         */
        BuildingConfigurator configurator = new DefaultBuildingConfigurator();
        configurator.setFloorCount(3);
        configurator.setSlotCount(3);
        configurator.addPersonCreateConfig(new PersonCreateConfig(1,0,3,2));
        configurator.addElevatorCreateConfig(new ElevatorCreateConfig(1,3,1,2));

        //Act
        Building building = configurator.createBuilding();
        Iterator<Floor> floorIterator = building.getFloorIterator();
        Iterator<Passenger> passengerIterator = building.getPassengerIterator();
        Iterator<IndoorTransport> transportIterator = building.getTransportIterator();

        Floor floor1 = floorIterator.next();
        Floor floor2 = floorIterator.next();
        Floor floor3 = floorIterator.next();

        Person person = (Person) passengerIterator.next();

        ElevatorController elevator = (ElevatorController) transportIterator.next();
        List<Location> usedLocations = new LinkedList<>();
        for (int level = 1;level<=3;level++)
            usedLocations.add(new Location(1,level));

        //Assert
        //Assert Floors
        Assert.assertEquals(1,floor1.getLevel());
        Assert.assertEquals(3,floor1.getSlotsCount());
        Assert.assertEquals(SlotType.EMPTY_SLOT,floor1.getSlot(0).getType());
        Assert.assertEquals(SlotType.ELEVATOR_DOOR,floor1.getSlot(1).getType());
        Assert.assertEquals(SlotType.EMPTY_SLOT,floor1.getSlot(2).getType());

        Assert.assertEquals(2,floor2.getLevel());
        Assert.assertEquals(3,floor2.getSlotsCount());
        Assert.assertEquals(SlotType.EMPTY_SLOT,floor2.getSlot(0).getType());
        Assert.assertEquals(SlotType.ELEVATOR_DOOR,floor2.getSlot(1).getType());
        Assert.assertEquals(SlotType.EMPTY_SLOT,floor2.getSlot(2).getType());

        Assert.assertEquals(3,floor3.getLevel());
        Assert.assertEquals(3,floor3.getSlotsCount());
        Assert.assertEquals(SlotType.EMPTY_SLOT,floor3.getSlot(0).getType());
        Assert.assertEquals(SlotType.ELEVATOR_DOOR,floor3.getSlot(1).getType());
        Assert.assertEquals(SlotType.EMPTY_SLOT,floor3.getSlot(2).getType());

        Assert.assertEquals(((DefaultElevatorDoor)floor1.getSlot(1)).getElevator(),((DefaultElevatorDoor)floor2.getSlot(1)).getElevator());
        Assert.assertEquals(((DefaultElevatorDoor)floor2.getSlot(1)).getElevator(),((DefaultElevatorDoor)floor3.getSlot(1)).getElevator());

        Assert.assertEquals(false, floorIterator.hasNext());

        //Assert Person
        Assert.assertEquals(1, person.getCurrentLocation().getLevel());
        Assert.assertEquals(0, person.getCurrentLocation().getPosition());
        Assert.assertEquals(3, person.getTargetLocation().getLevel());
        Assert.assertEquals(2, person.getTargetLocation().getPosition());
        Assert.assertEquals(null, person.getKeeper());
        Assert.assertEquals(SlotType.EMPTY_SLOT, person.getCurrentSlot().getType());
        Assert.assertEquals(floor1, person.getCurrentFloor());

        Assert.assertEquals(false, passengerIterator.hasNext());

        //Assert Elevator
        Assert.assertEquals(IndoorTransportType.ELEVATOR, elevator.getType());
        Assert.assertEquals(1, elevator.getLocation().getPosition());
        Assert.assertEquals(2, elevator.getLocation().getLevel());

        Iterator<Location> elevatorUsedLocations = elevator.getUsedLocations();
        for (int level = 1;level<=3;level++){
            Assert.assertEquals(true, elevatorUsedLocations.next().equals(usedLocations.get(level-1)));
        }

        Assert.assertEquals(false, elevatorUsedLocations.hasNext());
    }

}
