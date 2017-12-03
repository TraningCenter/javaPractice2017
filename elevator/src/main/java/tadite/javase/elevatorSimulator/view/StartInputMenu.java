package tadite.javase.elevatorSimulator.view;

import tadite.javase.elevatorSimulator.controller.BuildingConfigurator;
import tadite.javase.elevatorSimulator.controller.ElevatorCreateConfig;
import tadite.javase.elevatorSimulator.controller.PersonCreateConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StartInputMenu implements InputMenu {

    Map<String, Action> actionMap;

    public StartInputMenu(){
        actionMap=new HashMap<>();
        actionMap.put("1",(inputController -> inputController.changeMenu(new ConfigInputMenu(inputController.getBuildingConfigurator()))));
        actionMap.put("2",(inputController -> showConfig(inputController)));
        actionMap.put("3",(inputController -> inputController.startSimulation()));
        actionMap.put("4",(inputController -> System.exit(1)));
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
        System.out.println("***************************************");
    }

    @Override
    public String getCommandsLine() {
        return "[1] Open config\n[2] Show config\n[3] Run simulation \n[4] Exit \n";
    }

    @Override
    public void execute(String str, UserInputController inputController) {
        if (actionMap.containsKey(str))
            actionMap.get(str).execute(inputController);
    }

}
