package lift;

import java.util.*;

import execs.Executable;

public class House implements IBuilding{
	private List<Floor> floors;
	private List<Shaft> shafts;
	
	public House() {
		floors = new ArrayList<Floor>();
		shafts = new ArrayList<Shaft>();
	}
	public void addShaft(Shaft shaft) {
		if (!shafts.add(shaft))
			System.out.println("Something went wrong with adding shaft");
	}
	public void addFloor(Floor floor) {
		if (!floors.add(floor))
			System.out.println("Something went wrong with adding floor");
	}
	public void floorChecker() {
		for (Floor f: floors) {
			if (!f.getWaitingList().isEmpty()) {
				List<Transportable> floorPassengers = f.getWaitingList();
				for (Transportable t: floorPassengers) {
					f.pushButton(t.getDirection());///// TODO: Кто будет это ловить, фиксировать 
				}
			}
		}
	}
}
