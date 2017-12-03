package tadite.javase.elevatorSimulator.view;

/**
 * Interface for commands which are parsed and executed
 */
public interface ParseCommand {
    boolean parseAndExecute(String str, UserInputController inputController);
}
