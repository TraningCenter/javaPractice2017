package execs;

import input.InputController;
import lift.ExeResolver;
import lift.IBuilding;

public class SetHouseExecutable implements HouseExecutable {
	private IBuilding house;
	private InputController inputController;
	
	public SetHouseExecutable(IBuilding house, InputController inputController) {
		this.house = house;
		this.inputController = inputController;
	}
	public void execute() {
		house = inputController.instantiateBuilding();
		//ExeResolver.addExecutable(new SetHouseFloorsExecutable(house, inputController));
	}
}
