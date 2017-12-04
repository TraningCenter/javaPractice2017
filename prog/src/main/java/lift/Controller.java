package lift;

import java.util.*;

import execs.Executable;
import execs.SetHouseExecutable;
import input.InputController;
import output.OutputController;

public class Controller {
	
	private IBuilding building;
	private InputController inputController; 
	private OutputController outputController;
	private Queue<Executable> exeObjects;
	
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
//	public Queue<Executable> getExecs() {
//		return exeObjects;
//	}
//	public void setExecs(Queue<Executable> execs) {
//		this.exeObjects = execs;
//	}
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
		while(!controller.exeObjects.isEmpty()) {
			controller.exeObjects.poll().execute();
		}
	}
}
