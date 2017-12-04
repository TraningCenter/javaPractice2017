package models;

public class HouseInfoModel {
	private int floorCount;
	private int liftCount;
	private int[] capacity;
	private PassengerInfoModel[] passengers;
	
	public HouseInfoModel(int floorCount, int liftCount, PassengerInfoModel[] passengers, int[] capacity) {
		this.floorCount = floorCount;
		this.liftCount = liftCount;
		this.passengers = passengers;
		this.capacity = capacity;
	}
	public int getFloorCount() {
		return floorCount;
	}
	public int getLiftCount() {
		return liftCount;
	}
	public PassengerInfoModel[] getPassengers() {
		return passengers;
	}
	public int[] getCapacity() {
		return capacity;
	}
}
