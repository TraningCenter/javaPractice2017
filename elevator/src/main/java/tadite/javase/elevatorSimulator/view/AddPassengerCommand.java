package tadite.javase.elevatorSimulator.view;

import tadite.javase.elevatorSimulator.controller.PersonCreateConfig;

/**
 * Command For Adding Passenger to config
 */
public class AddPassengerCommand implements ParseCommand {
    //"add passenger -pos <startPosition> -level <startLevel> -tPos <targetPosition> -tLevel <targetLevel>\n"+

    @Override
    public boolean parseAndExecute(String str, UserInputController inputController) {
        String[] words = str.split(" ");

        if (words.length>8 && words[0].equalsIgnoreCase("add")
                && words[1].equalsIgnoreCase("passenger")
                && words[2].equalsIgnoreCase("-pos")
                && words[4].equalsIgnoreCase("-level")
                && words[6].equalsIgnoreCase("-tPos")
                && words[8].equalsIgnoreCase("-tLevel")){

            int position = Integer.parseInt(words[3]);
            int startLevel = Integer.parseInt(words[5]);
            int targetPosition = Integer.parseInt(words[7]);
            int targetLevel = Integer.parseInt(words[9]);

            inputController.getBuildingConfigurator().addPersonCreateConfig(new PersonCreateConfig(startLevel,position,targetLevel,targetPosition));
            return true;
        }

        return false;
    }
}
