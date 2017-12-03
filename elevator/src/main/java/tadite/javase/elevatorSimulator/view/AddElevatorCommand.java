package tadite.javase.elevatorSimulator.view;

import tadite.javase.elevatorSimulator.controller.ElevatorCreateConfig;

public class AddElevatorCommand implements ParseCommand {
    //add elevator -pos <position> -start <startLevel> -range <minLevel>,<maxLevel>

    @Override
    public boolean parseAndExecute(String str, UserInputController inputController) {
        String[] words = str.split(" ");

        if (words.length>7 && words[0].equalsIgnoreCase("add")
                && words[1].equalsIgnoreCase("elevator")
                && words[2].equalsIgnoreCase("-pos")
                && words[4].equalsIgnoreCase("-level")
                && words[6].equalsIgnoreCase("-range")
                && words[7].contains(",")){

            int position = Integer.parseInt(words[3]);
            int startLevel = Integer.parseInt(words[5]);
            String[] levelRangeWords = words[7].split(",");
            int minLevel = Integer.parseInt(levelRangeWords[0]);
            int maxLevel = Integer.parseInt(levelRangeWords[1]);

            inputController.getBuildingConfigurator().addElevatorCreateConfig(new ElevatorCreateConfig(minLevel,maxLevel,position,startLevel));
            return true;
        }

        return false;
    }
}
