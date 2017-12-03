package tadite.javase.elevatorSimulator.view;

import tadite.javase.elevatorSimulator.controller.BuildingConfigurator;
import tadite.javase.elevatorSimulator.controller.ElevatorCreateConfig;
import tadite.javase.elevatorSimulator.controller.PersonCreateConfig;
import tadite.javase.elevatorSimulator.model.elevator.ElevatorConfig;

import java.util.*;


/**
 * Get user input and execure start menu
 */
public class StartInputMenu implements InputMenu {

    Map<String, Action> actionMap;

    public StartInputMenu(){
        actionMap=new HashMap<>();
        actionMap.put("1",(inputController -> inputController.changeMenu(new ConfigInputMenu())));
        actionMap.put("2",(inputController -> showConfig(inputController)));
        actionMap.put("3",(inputController -> createDefaultConfig(inputController)));
        actionMap.put("4",(inputController -> inputController.startSimulation()));
        actionMap.put("5",(inputController -> System.exit(1)));
    }

    private void createDefaultConfig(UserInputController inputController) {
        BuildingConfigurator configurator = inputController.getBuildingConfigurator();
        configurator.clear();

        int floorCount = 5;
        int slotCount = 12;

        configurator.setFloorCount(floorCount);
        configurator.setSlotCount(slotCount);

        configurator.addElevatorCreateConfig(new ElevatorCreateConfig(1,5,1,1));
        configurator.addElevatorCreateConfig(new ElevatorCreateConfig(2,4,3,2));
        configurator.addElevatorCreateConfig(new ElevatorCreateConfig(3,5,6,4));
        configurator.addElevatorCreateConfig(new ElevatorCreateConfig(1,3,8,3));
        configurator.addElevatorCreateConfig(new ElevatorCreateConfig(1,5,10,5));

        configurator.addPersonCreateConfig(new PersonCreateConfig(1,5,5,11));
        configurator.addPersonCreateConfig(new PersonCreateConfig(2,4,3,0));
        configurator.addPersonCreateConfig(new PersonCreateConfig(3,7,5,0));
        configurator.addPersonCreateConfig(new PersonCreateConfig(4,2,3,0));
        configurator.addPersonCreateConfig(new PersonCreateConfig(5,11,1,11));
    }

    private void showConfig(UserInputController inputController) {
        BuildingConfigurator configurator = inputController.getBuildingConfigurator();
        System.out.println("****************CONFIG*****************");
        System.out.println("FLOOR COUNT : " + configurator.getFloorCount());
        System.out.println("SLOT COUNT : " + configurator.getSlotCount());
        System.out.println("PASSENGERS : ");
        Iterator<PersonCreateConfig> personCreateConfigs = configurator.getPersonCreateConfigs();
        while (personCreateConfigs.hasNext()){
            PersonCreateConfig next = personCreateConfigs.next();
            System.out.print("START POS : " + next.getStartPosition()+"   ");
            System.out.print("START LEVEL : " + next.getStartLevel()+"   ");
            System.out.print("TARGET POS : " + next.getTargetPosition()+"   ");
            System.out.println("TARGET LEVEL : " + next.getTargetLevel()+"   ");
        }
        System.out.println("ELEVATORS : ");
        Iterator<ElevatorCreateConfig> elevatorCreateConfigs = configurator.getElevatorCreateConfigs();
        while (elevatorCreateConfigs.hasNext()){
            ElevatorCreateConfig next = elevatorCreateConfigs.next();
            System.out.print("POS : " + next.getPosition()+"   ");
            System.out.print("START LEVEL : " + next.getElevatorStartLevel()+"   ");
            System.out.print("MIN LEVEL : " + next.getMinLevel()+"   ");
            System.out.println("MAX LEVEL : " + next.getMaxLevel()+"   ");
        }
    }

    @Override
    public String getCommandsLine() {
        return "[1] Open config\n[2] Show config\n[3] Set default config\n[4] Run simulation \n[5] Exit \n";
    }

    @Override
    public void execute(String str, UserInputController inputController) {
        if (actionMap.containsKey(str))
            actionMap.get(str).execute(inputController);
    }

}
