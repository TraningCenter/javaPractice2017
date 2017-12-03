package tadite.javase.elevatorSimulator.view;

/**
 * Command Setting slots count in building
 */
public class SetSlotCountCommand implements ParseCommand {
    @Override
    public boolean parseAndExecute(String str, UserInputController inputController) {
        String[] words = str.split(" ");

        if (words.length>3 && words[0].equalsIgnoreCase("set")
                && words[1].equalsIgnoreCase("slot")
                && words[2].equalsIgnoreCase("count")){

            int count = Integer.parseInt(words[3]);

            inputController.getBuildingConfigurator().setSlotCount(count);
            return true;
        }

        return false;
    }
}
