package tadite.javase.elevatorSimulator.view;

public interface InputMenu {
    String getCommandsLine();
    void execute(String str, UserInputController inputController);
}
