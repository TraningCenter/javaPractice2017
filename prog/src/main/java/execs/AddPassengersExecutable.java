package execs;

import java.util.List;

import input.InputController;
import lift.IBuilding;
import lift.Passenger;

public class AddPassengersExecutable implements HouseExecutable {
	IBuilding building;
	InputController inputController;
	
	public AddPassengersExecutable(IBuilding building, InputController inputController) {
		this.building = building;
		this.inputController = inputController;
	}

	public void execute() {
		List<Passenger> passengers = inputController.addPassengers();
		inputController.setPassengersForBuilding(passengers, building);
	}

}
