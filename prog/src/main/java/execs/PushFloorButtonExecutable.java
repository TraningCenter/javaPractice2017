package execs;

import lift.IBuilding;

public class PushFloorButtonExecutable implements FloorExecutable {
	private IBuilding house;
	
	public PushFloorButtonExecutable(IBuilding house) {
		this.house = house;
	}
	public void execute() {
		house.floorChecker();
	}
}
