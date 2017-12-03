package tadite.javase.elevatorSimulator.view;
/**
 * Takes user input and execute commands, also can return string with available commands
 */
public interface InputMenu {
    String getCommandsLine();

    void execute(String str, UserInputController inputController);
}
