package execs;

import java.util.List;

import input.InputController;
import lift.IBuilding;
import lift.Passenger;

public class SetWaitingPassengerExecutable implements HouseExecutable {

	private IBuilding house;
	private InputController inputController;
	private List<Passenger> passengers;
	
	public SetWaitingPassengerExecutable(IBuilding house, InputController inputController, List<Passenger> passengers) {
		this.house = house;
		this.inputController = inputController;
		this.passengers = passengers;
	}
	public void execute() {
		inputController.setPassengersForBuilding(passengers, house);
	}

}
