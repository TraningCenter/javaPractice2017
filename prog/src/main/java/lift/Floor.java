package lift;

import java.util.*;

public class Floor implements FloorOuterButton {
	private int number;
	private List<Transportable> waitingForLift;
	
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
		
	}
}
