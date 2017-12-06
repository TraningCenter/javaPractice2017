package lift;

import java.io.Console;
import java.util.*;

import execs.AddPassengersExecutable;
import execs.Executable;
import execs.SetHouseExecutable;
import input.InputController;
import output.OutputController;

public class Controller {
	
	private static IBuilding building;
	private static InputController inputController; 
	private static OutputController outputController;
	private static Queue<Executable> exeObjects;
	
	public Controller() {
		exeObjects = new LinkedList<Executable>();
		inputController = new InputController();
		outputController = new OutputController();
		building = new House();
		addExecutable(new SetHouseExecutable(building, inputController));
	}
	public void setHouse(IBuilding house) {
		this.building = house;
	}
	public void addExecutable(Executable exe) {
		exeObjects.offer(exe);
	}
	public int nextExecutable() {
		if (exeObjects == null) return -1;
		if (exeObjects.isEmpty()) return 0;
		Executable nextExe = exeObjects.poll();
		nextExe.execute();
		return 1;
	}
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		ExeResolver.setController(controller);
		// First house instantiation. 
		runIt(controller);
		Console console = System.console();
		String command = "";
		if (console != null) {
			command = console.readLine("Enter please \"Add\" to add passengers\n"
					+ "\tor \"Start\" to start your simulation: ");
		}
		if (checkInput(command)) {
			if (command.equals("Add")) {
				ExeResolver.addExecutable(new AddPassengersExecutable(building, inputController));
				runIt(controller);
			}
		}
		//ExeResolver.addExecutable(new ); TODO: переходить дальше к реализации симул€ции
	}
	
	private static boolean checkInput(String input) {
		if (input.equals("Add") || input.equals("Start")) return true;
		return false;
	}
	private static void runIt(Controller controller) {
		while(!controller.exeObjects.isEmpty()) {
			controller.exeObjects.poll().execute();
		}
	}
}
