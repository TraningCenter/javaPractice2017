package tadite.javase.elevatorSimulator.view;

public class ConfigInputMenu implements InputMenu {

    @Override
    public String getCommandsLine() {
        return "[1] Add elevator\n[2] Add person \n[3] Set floors \n";
    }

    @Override
    public void execute(char ch, UserInputController inputController) {

    }
}
