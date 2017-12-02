import org.junit.Test;
import tadite.javase.elevatorSimulator.controller.*;
import tadite.javase.elevatorSimulator.model.building.Building;
import tadite.javase.elevatorSimulator.model.elevator.IndoorTransport;
import tadite.javase.elevatorSimulator.model.passenger.Passenger;
import tadite.javase.elevatorSimulator.view.StreamBuildingPrintStrategy;

import java.io.PrintStream;
import java.util.Iterator;

public class BuildingConsolePrinterTests {

    @Test
    public void canPrintBuildingStateInConsole(){
        //Array
        BuildingPrintStrategy printStrategy = new StreamBuildingPrintStrategy(System.out);

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

        //Act
        printStrategy.print(building);

        //
    }
}
