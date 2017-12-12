package lift;

import java.io.Console;
import java.io.PrintStream;
import java.util.*;

import execs.AddPassengersExecutable;
import execs.Executable;
import execs.PushFloorButtonExecutable;
import execs.SetHouseExecutable;
import input.InputController;
import output.OutputController;

public class Controller {
	
	private static IBuilding building;
	private static InputController inputController; 
	private static OutputController outputController;
	private static Queue<Executable> exeObjects;
	private static boolean simulationInProgress;
	
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
	public static void clrscreen() {
		try {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	public void stopSimulation() {
		simulationInProgress = false;
	}
	
	public static void main(String[] args) {
		try {
			Controller controller = new Controller();
			clrscreen();
			ExeResolver.setController(controller);
			// First house instantiation. 
			runIt(controller);
			
			outputController = new OutputController();
			outputController.configureOutput(building);
		}
		catch(Exception e) {
			System.out.printf("Exception %s\n", e);
			e.printStackTrace();
		}
		while (true) {
			try {				
				outputController.showSituation(building);
				String command = "";
				System.out.println("Enter please \"Add\" to add passengers\n"
						+ "\tor \"Start\" to start your simulation: \n"
						+ "\tor \"Exit\" to finish the program work!\n");
				Scanner in = new Scanner(System.in);
				command = in.nextLine();
				if (checkInput(command)) {
					if (command.toLowerCase().equals("add")) {
						ExeResolver.addExecutable(new AddPassengersExecutable(building, inputController));
						runIt(ExeResolver.getController());
						outputController.showSituation(building);
						continue;
					}
					if (command.toLowerCase().equals("start")) {
						Dispatcher.setHouse(building);
						ExeResolver.addExecutable(new PushFloorButtonExecutable(building));
						runIt(ExeResolver.getController());
						simulationInProgress = true;
						while(simulationInProgress) {
							Dispatcher.nextStep();
							runIt(ExeResolver.getController());
							outputController.showSituation(building);
						}
					}
					if (command.toLowerCase().equals("exit")) {
						break;
					}
				}
			}
			catch(Exception e) {
				System.out.printf("Exception %s\n", e);
				e.printStackTrace();
			}
		}
	}
	
	private static boolean checkInput(String input) {
		if (input.toLowerCase().equals("add") || input.toLowerCase().equals("start") ||
				input.toLowerCase().equals("exit")) return true;
		return false;
	}
	private static void runIt(Controller controller) {
		while(!controller.exeObjects.isEmpty()) {
			controller.exeObjects.poll().execute();
		}
	}
}
