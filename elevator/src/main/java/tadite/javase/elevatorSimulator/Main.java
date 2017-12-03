package tadite.javase.elevatorSimulator;

import tadite.javase.elevatorSimulator.controller.DefaultBuildingConfigurator;
import tadite.javase.elevatorSimulator.view.StartInputMenu;
import tadite.javase.elevatorSimulator.view.UserInputController;

public class Main {

    public static void main(String [] args){
        UserInputController userInputController = new UserInputController(new StartInputMenu(), new DefaultBuildingConfigurator());
        userInputController.start();
    }
}
