package tadite.javase.elevatorSimulator;

import tadite.javase.elevatorSimulator.controller.BuildingConfigurator;
import tadite.javase.elevatorSimulator.controller.DefaultBuildingConfigurator;
import tadite.javase.elevatorSimulator.controller.ElevatorCreateConfig;
import tadite.javase.elevatorSimulator.controller.PersonCreateConfig;
import tadite.javase.elevatorSimulator.view.InputMenu;
import tadite.javase.elevatorSimulator.view.StartInputMenu;
import tadite.javase.elevatorSimulator.view.UserInputController;

public class Main {

    public static void main(String [] args){
        InputMenu menu = new StartInputMenu();
        BuildingConfigurator buildingConfigurator = new DefaultBuildingConfigurator();

        UserInputController userInputController = new UserInputController(menu, buildingConfigurator);

        userInputController.start();
    }
}
