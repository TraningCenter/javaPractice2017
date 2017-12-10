package models;

import lift.PassengerDirection;

public class Request {
	private int floorNumber; 
	private PassengerDirection passengerDirection;
	
	public Request(int floorNumber, PassengerDirection passengerDirection) {
		this.floorNumber = floorNumber; 
		this.passengerDirection = passengerDirection;
	}
	
	public int getFloorNumber() {
		return floorNumber;
	}
	public PassengerDirection getFloorDirection() {
		return passengerDirection;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Request req = (Request) obj;
		if (floorNumber != req.floorNumber) return false;
		if (!passengerDirection.equals(req.passengerDirection)) return false;
		return true;
	}
}
