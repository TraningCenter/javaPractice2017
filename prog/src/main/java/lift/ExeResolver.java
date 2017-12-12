package lift;

import java.util.*;

import execs.Executable;

public class ExeResolver {
	private static Controller controller;
	
	public static void setController(Controller c) {
		ExeResolver.controller = c;
	}
	public static void addExecutable(Executable action) {
		controller.addExecutable(action);
	}
	public static void stopSimulation(){
		controller.stopSimulation();
	}
	public static Controller getController() {
		return controller;
	}
}
