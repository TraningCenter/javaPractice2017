package tadite.javase.elevatorSimulator.view;

/**
 * Command Setting floors count in building
 */
public class SetFloorCountCommand implements ParseCommand {

    //set floor count <count>

    @Override
    public boolean parseAndExecute(String str, UserInputController inputController) {
        String[] words = str.split(" ");

        if (words.length>3 && words[0].equalsIgnoreCase("set")
                && words[1].equalsIgnoreCase("floor")
                && words[2].equalsIgnoreCase("count")){

            int count = Integer.parseInt(words[3]);

            inputController.getBuildingConfigurator().setFloorCount(count);
            return true;
        }

        return false;
    }
}
