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
//	public int nextExecutable() {
//		if (exeObjects == null) return -1;
//		if (exeObjects.isEmpty()) return 0;
//		Executable nextExe = exeObjects.poll();
//		nextExe.execute();
//		return 1;
//	}
	public static void clrscreen() {
		try {
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
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
			Controller controller = new Controller();
			clrscreen();
			ExeResolver.setController(controller);
			// First house instantiation. 
			runIt(controller);
			
			outputController = new OutputController();
			outputController.configureOutput(building);
			outputController.showSituation(building);
			String command = "";
			System.out.println("Enter please \"Add\" to add passengers\n"
					+ "\tor \"Start\" to start your simulation: ");
			Scanner in = new Scanner(System.in);
			command = in.nextLine();
			if (checkInput(command)) {
				if (command.toLowerCase().equals("add")) {
					House h = (House) building;
					System.out.println(h.toString());
					ExeResolver.addExecutable(new AddPassengersExecutable(building, inputController));
					runIt(controller);
					outputController.showSituation(building);
				}
				if (command.toLowerCase().equals("start")) {
					Dispatcher.setHouse(building);
					ExeResolver.addExecutable(new PushFloorButtonExecutable(building));
					runIt(controller);
					simulationInProgress = true;
					while(simulationInProgress) {
						Dispatcher.nextStep();
						runIt(controller);
						outputController.showSituation(building);
					}
				}
			}
			else {
				System.out.println("WRONG");
			}
			System.out.println("EOP");
		}
		catch(Exception e) {
			System.out.printf("Exception %s\n", e);
			e.printStackTrace();
		}
	
	}
	
	private static boolean checkInput(String input) {
		if (input.toLowerCase().equals("add") || input.toLowerCase().equals("start")) return true;
		return false;
	}
	private static void runIt(Controller controller) {
		while(!controller.exeObjects.isEmpty()) {
			controller.exeObjects.poll().execute();
		}
	}
}
