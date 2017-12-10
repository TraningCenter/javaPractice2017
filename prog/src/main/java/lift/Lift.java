package lift;

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
	}
	///// Method for external Executable class-manipulator. I guess
	public void actLift(Floor curFloor) {
		if (floorNumbersToStop.contains(curFloorNumber)) {
			if (!passengers.isEmpty()) {
				for (Transportable t: passengers) {
					if (t.getDest() == curFloorNumber)
						letOutPassenger(t);
				}
			}
			while ((passengers.size() < capacity) || 
					())
			return;
		}
		curFloorNumber++;
		
		if (isPaused) {
			
			while ((passengers.size() != capacity) || (!floor.getWaitingList().isEmpty())) {
				addPassenger(floor.getWaitingList().remove(0));
			}
			isPaused = false;
		}
		else {
			curFloorNumber++;
			if (this.floorNumbersToStop.contains(curFloorNumber))
				isPaused = true;
		}
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
	
	private void addFloorToStop(int floorNumber) {
		floorNumbersToStop.add(floorNumber);
	}
}
