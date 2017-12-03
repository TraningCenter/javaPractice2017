package tadite.javase.elevatorSimulator.view;

import tadite.javase.elevatorSimulator.controller.BuildingConfigurator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConfigInputMenu implements InputMenu {

    List<ParseCommand> commandList;

    public ConfigInputMenu(BuildingConfigurator buildingConfigurator){
        commandList = new LinkedList<>();
        commandList.add(new AddElevatorCommand());
        commandList.add(new AddPassengerCommand());
        commandList.add(new SetSlotCountCommand());
        commandList.add(new SetFloorCountCommand());
        commandList.add(new AddPassengerCommand());
        commandList.add((str, inputController) -> {
            if (str.equalsIgnoreCase("back"))
            {
                inputController.changeMenu(new StartInputMenu());
                return true;
            }
            return false;});
    }

    @Override
    public String getCommandsLine() {
        return  "add passenger -pos <startPosition> -level <startLevel> -tPos <targetPosition> -tLevel <targetLevel>\n"+
                "add elevator -pos <position> -level <startLevel> -range <minLevel>,<maxLevel>\n" +
                "set floor count <count>\n" +
                "set slot count <count>\n"+
                "back\n";
    }

    @Override
    public void execute(String str, UserInputController inputController) {
        commandList.stream().anyMatch(parseCommand -> parseCommand.parseAndExecute(str, inputController));
    }



}
