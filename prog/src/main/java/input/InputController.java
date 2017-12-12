package input;

import java.util.LinkedList;
import java.util.List;

import lift.Floor;
import lift.House;
import lift.IBuilding;
import lift.Lift;
import lift.Passenger;
import lift.Shaft;
import models.HouseInfoModel;
import models.PassengerInfoModel;

public class InputController {
	private InputConsole inputConsole;
	private static HouseInfoModel houseModel;
	
	public InputController() {
		inputConsole = new DefaultInputConsole();
	}
	public IBuilding instantiateBuilding() {
		HouseInfoModel houseModel = inputConsole.getHouseInfo();
		InputController.houseModel = houseModel;
		IBuilding house = new House();
		for (int i = 0; i < houseModel.getFloorCount(); i++) {
			house.addFloor(new Floor(i));
		}
		for (int i = 0; i < houseModel.getLiftCount(); i++) {
			house.addShaft(new Shaft(new Lift(houseModel.getCapacity()[i], 0, 0, houseModel.getFloorCount() - 1)));
		}
		return house;
	}
	public List<Passenger> getPassengersFromInput(){
		PassengerInfoModel[] passengersInfo = houseModel.getPassengers();
		List<Passenger> passengers = getPassengersFromInput(passengersInfo);
		houseModel = null;
		return passengers;
	}
	public List<Passenger> getPassengersFromInput(PassengerInfoModel[] passengersInfo){
		List<Passenger> passengers = new LinkedList<Passenger>();
		for (int i = 0; i < passengersInfo.length; i++) {
			Passenger pass = new Passenger(passengersInfo[i].getStart(), passengersInfo[i].getDest());
			passengers.add(pass);
		}
		if (passengers.isEmpty()) return null;
		return passengers;
	}
	public IBuilding setPassengersForBuilding(List<Passenger> passengers, IBuilding house) {
		if (passengers == null || passengers.isEmpty()) return house;
		for(Passenger p: passengers) {
			house.getFloorByNumber(p.getStart()).addWaiting(p);
		}
		return house;
	}
	public List<Passenger> addPassengers() {
		PassengerInfoModel[] passInfoModels = inputConsole.getPassengersInfo();
		return getPassengersFromInput(passInfoModels);
	}
}
