package tadite.javase.elevatorSimulator.view;

import tadite.javase.elevatorSimulator.controller.BuildingConfigurator;
import tadite.javase.elevatorSimulator.controller.BuildingSimulationRunner;
import tadite.javase.elevatorSimulator.controller.SimulationRunner;

import java.util.Scanner;

public class UserInputController {

    private InputMenu inputMenu;
    private BuildingConfigurator buildingConfigurator;
    private Scanner scanner;

    private SimulationRunner simulationRunner;

    public UserInputController(InputMenu inputMenu, BuildingConfigurator buildingConfigurator) {
        this.inputMenu = inputMenu;
        this.buildingConfigurator = buildingConfigurator;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while(true) {
            showMenu();
            String str = scanner.nextLine();
            inputMenu.execute(str, this);
        }
    }

    public void changeMenu(InputMenu inputMenu){
        this.inputMenu=inputMenu;
    }

    public BuildingConfigurator getBuildingConfigurator(){
        return buildingConfigurator;
    }

    private void showMenu() {
        System.out.println("****************MENU*****************");
        System.out.println(inputMenu.getCommandsLine());
        System.out.println("**************************************");
        System.out.println("Selection: ");
    }

    public void startSimulation() {
        createSimulationRunner();
        simulationRunner.startSimulation();
    }

    private void createSimulationRunner() {
        simulationRunner=new BuildingSimulationRunner(buildingConfigurator.createBuilding(),new StreamBuildingPrintStrategy(System.out));
    }
}
