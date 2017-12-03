package tadite.javase.elevatorSimulator.view;

public interface ParseCommand {
    boolean parseAndExecute(String str, UserInputController inputController);
}
