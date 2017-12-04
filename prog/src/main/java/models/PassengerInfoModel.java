package models;

public class PassengerInfoModel {
	private int startFloor; 
	private int destFloor;
	
	public PassengerInfoModel(int start, int dest) {
		this.startFloor = start;
		this.destFloor = dest;
	}
	public int getStart() {
		return startFloor;
	}
	public int getDest() {
		return destFloor;
	}
}
