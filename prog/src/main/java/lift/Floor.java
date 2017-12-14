package lift;

import java.util.*;

import models.Request;

public class Floor implements FloorOuterButton {
	private int number;
	private List<Transportable> waitingForLift;
	private boolean isUpPressed;
	private boolean isDownPressed;
	
	public Floor() {
		waitingForLift = new LinkedList<Transportable>();
	}
	public Floor(int number) {
		this.number = number;
		this.waitingForLift = new LinkedList<Transportable>();
	}
	public int getNumber() {
		return number;
	}
	public List<Transportable> getWaitingList(){
		return this.waitingForLift;
	}
	public boolean isUpPressed() {
		return  isUpPressed;
	}
	public boolean isDownPressed() {
		return  isDownPressed;
	}
	public void addWaiting(Transportable passenger) {
		if (waitingForLift == null) 
			waitingForLift = new LinkedList<Transportable>();
		waitingForLift.add(passenger);
	}
	public void removeWaitingPass(Transportable passenger) {
		if (!waitingForLift.remove(passenger))
			System.out.println("Something wrong with passenger deletion from waiting people list");
	}
	public void pushButton(PassengerDirection passDirection) {
		if (passDirection.equals(PassengerDirection.DOWN)) {
			if (!isDownPressed) isDownPressed = true;
		}
		else
			if (!isUpPressed) isUpPressed = true;
		Dispatcher.addRequest(new Request(number, passDirection));
	}
}
