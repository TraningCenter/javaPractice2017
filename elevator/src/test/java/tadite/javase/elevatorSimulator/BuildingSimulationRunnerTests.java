package tadite.javase.elevatorSimulator;

import junit.framework.Assert;
import org.junit.Test;
import tadite.javase.elevatorSimulator.controller.*;
import tadite.javase.elevatorSimulator.model.building.Building;
import tadite.javase.elevatorSimulator.model.elevator.IndoorTransport;
import tadite.javase.elevatorSimulator.model.passenger.Passenger;
import tadite.javase.elevatorSimulator.model.passenger.Person;
import tadite.javase.elevatorSimulator.view.StreamBuildingPrintStrategy;

import java.util.Iterator;

public class BuildingSimulationRunnerTests {

    @Test
    public void canRunSimulationWithOnePerson(){
        //Array
        BuildingConfigurator configurator = new DefaultBuildingConfigurator();
        configurator.setFloorCount(3);
        configurator.setSlotCount(3);
        configurator.addElevatorCreateConfig(new ElevatorCreateConfig(1,3,1,1));
        configurator.addPersonCreateConfig(new PersonCreateConfig(1,0,3,2));
        Building building = configurator.createBuilding();

        BuildingPrintStrategy printStrategy = blding -> {};

        BuildingSimulationRunner buildingSimulationRunner = new BuildingSimulationRunner(building, printStrategy,1);
        //Act
        buildingSimulationRunner.startSimulation();

        //Assert
        Iterator<Passenger> passengerIterator = building.getPassengerIterator();
        Iterator<IndoorTransport> transportIterator = building.getTransportIterator();
        Passenger passenger = passengerIterator.next();
        IndoorTransport transport = transportIterator.next();

        Assert.assertEquals(2,passenger.getCurrentLocation().getPosition());
        Assert.assertEquals(3,passenger.getCurrentLocation().getLevel());
        Assert.assertEquals(1,transport.getLocation().getPosition());
        Assert.assertEquals(3,transport.getLocation().getLevel());
    }

    @Test
    public void canRunSimulationWithMultiplePersonsAndElevators(){
        //Array
        BuildingConfigurator configurator = new DefaultBuildingConfigurator();
        /*
        _T3| |___|E|_S1 4
        ___|E|___| |___ 3
        ___| |___| |_T2 2
        _S3| |_T1| |_S2 1
         0  1  2  3  4
         */
        configurator.setFloorCount(4);
        configurator.setSlotCount(5);
        configurator.addElevatorCreateConfig(new ElevatorCreateConfig(1,4,1,3));
        configurator.addElevatorCreateConfig(new ElevatorCreateConfig(1,4,3,4));
        configurator.addPersonCreateConfig(new PersonCreateConfig(4,4,1,2));
        configurator.addPersonCreateConfig(new PersonCreateConfig(1,4,2,4));
        configurator.addPersonCreateConfig(new PersonCreateConfig(1,0,4,0));
        Building building = configurator.createBuilding();
        Iterator<Passenger> passengerIterator = building.getPassengerIterator();
        Iterator<IndoorTransport> transportIterator = building.getTransportIterator();

        BuildingPrintStrategy printStrategy = blding -> {};

        BuildingSimulationRunner buildingSimulationRunner = new BuildingSimulationRunner(building, printStrategy,1);

        //Assert building elements start locations
        Passenger passenger1start = passengerIterator.next();
        Passenger passenger2start = passengerIterator.next();
        Passenger passenger3start = passengerIterator.next();
        IndoorTransport transport1was = transportIterator.next();
        IndoorTransport transport2was = transportIterator.next();

        Assert.assertEquals(4,passenger1start.getCurrentLocation().getPosition());
        Assert.assertEquals(4,passenger1start.getCurrentLocation().getLevel());
        Assert.assertEquals(4,passenger2start.getCurrentLocation().getPosition());
        Assert.assertEquals(1,passenger2start.getCurrentLocation().getLevel());
        Assert.assertEquals(0,passenger3start.getCurrentLocation().getPosition());
        Assert.assertEquals(1,passenger3start.getCurrentLocation().getLevel());
        Assert.assertEquals(1,transport1was.getLocation().getPosition());
        Assert.assertEquals(3,transport1was.getLocation().getLevel());
        Assert.assertEquals(3,transport2was.getLocation().getPosition());
        Assert.assertEquals(4,transport2was.getLocation().getLevel());


        //Start Simulation
        buildingSimulationRunner.startSimulation();

        //Assert building elements locations after simulation
        /*
        _T3| |___|E|_S1 4
        ___|E|___| |___ 3
        ___| |___| |_T2 2
        _S3| |_T1| |_S2 1
         0  1  2  3  4
         */
        passengerIterator = building.getPassengerIterator();
        Passenger passenger1 = passengerIterator.next();
        Passenger passenger2 = passengerIterator.next();
        Passenger passenger3 = passengerIterator.next();

        Assert.assertEquals(2,passenger1.getCurrentLocation().getPosition());
        Assert.assertEquals(1,passenger1.getCurrentLocation().getLevel());
        Assert.assertEquals(4,passenger2.getCurrentLocation().getPosition());
        Assert.assertEquals(2,passenger2.getCurrentLocation().getLevel());
        Assert.assertEquals(0,passenger3.getCurrentLocation().getPosition());
        Assert.assertEquals(4,passenger3.getCurrentLocation().getLevel());

    }

}
