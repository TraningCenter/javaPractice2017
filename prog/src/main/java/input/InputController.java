package input;

import lift.Floor;
import lift.House;
import lift.IBuilding;
import lift.Lift;
import lift.Shaft;
import models.HouseInfoModel;

public class InputController {
	private InputConsole inputConsole;
	
	public InputController() {
		inputConsole = new DefaultInputConsole();
	}
	public void getInputModel() {
		
	}
	public IBuilding instantiateBuilding() {
		HouseInfoModel houseModel = inputConsole.getHouseInfo();
		IBuilding house = new House();
		for (int i = 0; i < houseModel.getFloorCount(); i++) {
			house.addFloor(new Floor(i));
		}
		for (int i = 0; i < houseModel.getLiftCount(); i++) {
			house.addShaft(new Shaft(new Lift(houseModel.getCapacity()[i], 0, 0, houseModel.getFloorCount() - 1)));
		}
		return house;
	}
	public void setHouseFloors(IBuilding house) {
		
	}
}
