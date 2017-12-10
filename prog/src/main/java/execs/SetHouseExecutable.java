package execs;

import java.util.List;

import input.InputController;
import lift.ExeResolver;
import lift.House;
import lift.IBuilding;
import lift.Passenger;

public class SetHouseExecutable implements HouseExecutable {
	private IBuilding house;
	private InputController inputController;
	
	public SetHouseExecutable(IBuilding house, InputController inputController) {
		this.house = house;
		this.inputController = inputController;
	}
	public void execute() {
		IBuilding temp = inputController.instantiateBuilding();
		house.getParamsFromHouse(temp);
		List<Passenger> passengersInput = inputController.getPassengersFromInput();
		ExeResolver.addExecutable(new SetWaitingPassengerExecutable(house, inputController, passengersInput));
	}
}
