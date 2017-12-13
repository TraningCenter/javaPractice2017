package execs;

import java.util.LinkedList;
import java.util.List;

import input.InputController;
import lift.IBuilding;
import lift.Passenger;

public class AddPassengersExecutable implements HouseExecutable {
	private IBuilding building;
	private InputController inputController;
	
	public AddPassengersExecutable(IBuilding building, InputController inputController) {
		this.building = building;
		this.inputController = inputController;
	}

	public void execute() {
		List<Passenger> passengers = inputController.addPassengers();
		filterPassengers(passengers);
		inputController.setPassengersForBuilding(passengers, building);
	}
	
	private List<Passenger> filterPassengers(List<Passenger> passengers) {
		int[] indecies = new int[passengers.size() + 1];
		int counter = 0;
		for(Passenger pass: passengers) {
			if (pass.getDest() < 0 || pass.getDest() >= building.getFloors().size() || 
					pass.getStart() < 0 || pass.getStart() >= building.getFloors().size())
				indecies[counter++] = passengers.indexOf(pass);
		}
		for (int i = 0; i < counter; i++) {
			passengers.remove(indecies[i]);
			for (int j = i + 1; j < counter; j++)
				if (indecies[j] > indecies[i])
					indecies[j]--;
		}
		return passengers;
	}

}
