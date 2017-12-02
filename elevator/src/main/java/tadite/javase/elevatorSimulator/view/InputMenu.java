package tadite.javase.elevatorSimulator.view;

public interface InputMenu {
    String getCommandsLine();
    void execute(char ch, UserInputController inputController);
}
