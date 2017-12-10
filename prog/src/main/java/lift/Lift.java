package lift;

import java.awt.geom.CubicCurve2D;
import java.util.*;

import models.Request;

public class Lift implements LiftInnerButton {
	private static int globalLiftEnumerator = 1;
	private int id;
	private int curFloorNumber;
	private boolean isPaused;
	private List<Integer> floorNumbersToStop;
	private List<Transportable> passengers; 
	private int capacity;
	private LiftDirection liftDirection;
	private LiftDirection requestDirection;
	private int maxFloor; 
	private int minFloor;
	
	public Lift() {
		id = globalLiftEnumerator++;
		floorNumbersToStop = new LinkedList<Integer>();
		passengers = new LinkedList<Transportable>();
		capacity = 7; 
		liftDirection = LiftDirection.STOP;
		requestDirection = LiftDirection.STOP;
		isPaused = false;
	}
	public Lift(int capacity, int curFloorNumber, int minFloor, int maxFloor) {
		id = globalLiftEnumerator++;
		this.curFloorNumber = curFloorNumber;
		floorNumbersToStop = new LinkedList<Integer>();
		passengers = new LinkedList<Transportable>();
		this.capacity = capacity; 
		liftDirection = LiftDirection.STOP;
		requestDirection = LiftDirection.STOP;
		this.minFloor = minFloor;
		this.maxFloor = maxFloor;
		isPaused = false;
	}
	
	public void addPassenger(Transportable passenger) {
		passengers.add(passenger);
		if (!floorNumbersToStop.contains(passenger.getDest()))
			floorNumbersToStop.add(passenger.getDest());
	}	
	public int getMin() {
		return minFloor;
	}
	public int getMax() {
		return maxFloor;
	}
	public int getCapacity() {
		return capacity;
	}
	public LiftDirection getLiftDirection() {
		return liftDirection;
	}
	public LiftDirection getRequestDirection() {
		return requestDirection;
	}
	public void setLiftDirection(LiftDirection liftDirection) {
		this.liftDirection = liftDirection;
	}
	public void setRequestDirection(LiftDirection requestDirection) {
		this.requestDirection = requestDirection;
	}
	public List<Transportable> getPassengers() {
		return passengers;
	}
	public List<Integer> getFloorNumbersToStop() {
		return floorNumbersToStop;
	}
	public void letOutPassenger(Transportable passenger) {
		if (!passengers.remove(passenger))
			System.out.println("There wasn't such passenger in this lift");
	}
	public void pushButton(int floorNumber) {
		addFloorToStop(floorNumber);
	}
	public LiftDirection getDirection() {
		return liftDirection;
	}
	public int getCurFloorNumber() {
		return curFloorNumber;
	}
	public void setCurFloorNumber(int number) {
		this.curFloorNumber = number;
	}
	public boolean isFull() {
		return (capacity == passengers.size());
	}
	public boolean isSpecialDirection() {
		return (this.liftDirection != this.requestDirection);
	}
	public void delegateRequest(Request request) {
		if (liftDirection == LiftDirection.STOP) {
			this.requestDirection = request.getFloorDirection() == PassengerDirection.UP ? LiftDirection.UP : LiftDirection.DOWN;
			this.liftDirection = (curFloorNumber - request.getFloorNumber()) < 0 ? LiftDirection.UP : LiftDirection.DOWN;
		}
		this.addFloorToStop(request.getFloorNumber());
	}
	public boolean isDestFloor(int floorNum) {
		for (Transportable t: passengers) {
			if (t.getDest() == floorNum)
				return true;
		}
		return false;
	}	
	public void addFloorToStop(int floorNumber) {
		floorNumbersToStop.add(floorNumber);
	}
	
	public void switchDirection(LiftDirection direction) {
		if (direction == LiftDirection.UP)
			direction = LiftDirection.DOWN;
		direction = LiftDirection.UP;
	}
}
