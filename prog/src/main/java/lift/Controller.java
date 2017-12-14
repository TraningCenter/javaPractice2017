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
	public static void setHouse(IBuilding house) {
		building = house;
	}
	public static IBuilding getHouse() {
		return building;
	}
	public static void addExecutable(Executable exe) {
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
	public static void stopSimulation() {
		simulationInProgress = false;
	}
	public static boolean isQueueEmpty() {
		return exeObjects.isEmpty();
	}
	public static void runIt() {
		while(!isQueueEmpty()) {
			exeObjects.poll().execute();
		}
	}
	
	public static void main(String[] args) {
		try {
			Controller controller = new Controller();
			clrscreen();
			// First house instantiation. 
			runIt();
			
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
						addExecutable(new AddPassengersExecutable(building, inputController));
						runIt();
						outputController.showSituation(building);
						continue;
					}
					if (command.toLowerCase().equals("start")) {
						Dispatcher.setHouse(building);
						addExecutable(new PushFloorButtonExecutable(building));
						runIt();
						simulationInProgress = true;
						while(simulationInProgress) {
							Dispatcher.nextStep();
							runIt();
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
}
