package tadite.javase.elevatorSimulator.view;

import java.util.HashMap;
import java.util.Map;

public class StartInputMenu implements InputMenu {

    Map<Character, Action> actionMap;

    public StartInputMenu(){
        actionMap=new HashMap<>();
        actionMap.put('1',(inputController -> inputController.changeMenu(new ConfigInputMenu())));
    }

    @Override
    public String getCommandsLine() {
        return "[1] Open config\n[2] Run simulation \n";
    }

    @Override
    public void execute(char ch, UserInputController inputController) {
        if (actionMap.containsKey(ch))
            actionMap.get(ch).execute(inputController);
    }
}
