package lift;

import java.util.*;

import execs.Executable;
import models.Request;

public class House implements IBuilding{
	private List<Floor> floors;
	private List<Shaft> shafts;
	
	public House() {
		floors = new ArrayList<Floor>();
		shafts = new ArrayList<Shaft>();
	}
	public List<Shaft> getShafts() {
		return shafts;
	}
	public List<Floor> getFloors(){
		return floors;
	}
	public void addShaft(Shaft shaft) {
		if (!shafts.add(shaft))
			System.out.println("Something went wrong with adding shaft");
	}
	public void addFloor(Floor floor) {
		if (!floors.add(floor))
			System.out.println("Something went wrong with adding floor");
	}
	public Floor getFloorByNumber(int floorNum) {
		Floor selectedFloor  = null;
		for (Floor f: floors) {
			if (f.getNumber() == floorNum) {
				selectedFloor = f;
				break;
			}
		}
		return selectedFloor;
	}
	public Lift[] getLifts() {
		Lift[] lifts = new Lift[shafts.size()];
		int i = 0;
		for (Shaft shaft: shafts)
			lifts[i++] = shaft.getLift();
		return lifts;
	}
	public void floorChecker() {
		for (Floor f: floors) {
			if (!f.getWaitingList().isEmpty()) {
				List<Transportable> floorPassengers = f.getWaitingList();
				for (Transportable t: floorPassengers) {
					f.pushButton(t.getDirection());
				}
			}
		}
	}
}
