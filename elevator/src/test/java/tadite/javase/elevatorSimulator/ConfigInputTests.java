package tadite.javase.elevatorSimulator;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import tadite.javase.elevatorSimulator.controller.BuildingConfigurator;
import tadite.javase.elevatorSimulator.controller.DefaultBuildingConfigurator;
import tadite.javase.elevatorSimulator.controller.ElevatorCreateConfig;
import tadite.javase.elevatorSimulator.controller.PersonCreateConfig;
import tadite.javase.elevatorSimulator.view.ConfigInputMenu;
import tadite.javase.elevatorSimulator.view.StartInputMenu;
import tadite.javase.elevatorSimulator.view.UserInputController;

public class ConfigInputTests {

    private DefaultBuildingConfigurator configurator;
    private ConfigInputMenu configInputMenu;
    private UserInputController userInputController;

    @Before
    public void init(){
        configurator = new DefaultBuildingConfigurator();
        configInputMenu = new ConfigInputMenu();
        userInputController = new UserInputController(configInputMenu,configurator);
    }

    @Test
    public void canAddPassenger(){
        //Array
        //Act

        configInputMenu.execute("add passenger -pos 1 -level 5 -tPos 3 -tLevel 2",userInputController);

        //Assert
        PersonCreateConfig personCreateConfig = configurator.getPersonCreateConfigs().next();
        Assert.assertEquals(1,personCreateConfig.getStartPosition());
        Assert.assertEquals(5,personCreateConfig.getStartLevel());
        Assert.assertEquals(3,personCreateConfig.getTargetPosition());
        Assert.assertEquals(2,personCreateConfig.getTargetLevel());
    }

    @Test
    public void canAddElevator(){
        //Array
        //Act

        configInputMenu.execute("add elevator -pos 1 -level 1 -range 1,5",userInputController);

        //Assert
        ElevatorCreateConfig elevatorCreateConfig = configurator.getElevatorCreateConfigs().next();
        Assert.assertEquals(1,elevatorCreateConfig.getPosition());
        Assert.assertEquals(1,elevatorCreateConfig.getElevatorStartLevel());
        Assert.assertEquals(1,elevatorCreateConfig.getMinLevel());
        Assert.assertEquals(5,elevatorCreateConfig.getMaxLevel());
    }

    @Test
    public void canSetFloorCount(){
        //Array
        //Act

        configInputMenu.execute("set floor count 5",userInputController);

        //Assert
        Assert.assertEquals(5,configurator.getFloorCount());

    }

    @Test
    public void canSetSlotCount(){
        //Array
        //Act

        configInputMenu.execute("set slot count 5",userInputController);

        //Assert
        Assert.assertEquals(5,configurator.getSlotCount());

    }

}
