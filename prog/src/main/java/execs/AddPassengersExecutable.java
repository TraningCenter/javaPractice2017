package execs;

import java.util.LinkedList;
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
		filterPassengers(passengers);
		inputController.setPassengersForBuilding(passengers, building);
	}
	
	private List<Passenger> filterPassengers(List<Passenger> passengers) {
		LinkedList<Integer> indecies = new LinkedList<Integer>();
		for(Passenger pass: passengers) {
			if (pass.getDest() < 0 || pass.getDest() >= building.getFloors().size() || 
					pass.getStart() < 0 || pass.getStart() >= building.getFloors().size())
				passengers.indexOf(pass);
		}
		for (Integer i: indecies) {
			passengers.remove(i);
		}
		return passengers;
	}

}
