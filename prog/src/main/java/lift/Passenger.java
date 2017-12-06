package lift;

public class Passenger implements Transportable {
	private int startFloor;
	private int destFloor;
	
	public Passenger() {
		startFloor = 0;
		destFloor = 1;
	}
	public Passenger(int start, int dest) {
		startFloor = start;
		destFloor = dest;
	}
	public int getStart() {
		return this.startFloor;
	}
	public int getDest() {
		return this.destFloor;
	}
	public PassengerDirection getDirection() {
		int delta = destFloor - startFloor;
		if (delta < 0) return PassengerDirection.DOWN;
		else return PassengerDirection.UP;
	}
}
