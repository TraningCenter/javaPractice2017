package lift;

import java.util.List;

public interface IBuilding {
	public List<Shaft> getShafts();
	public List<Floor> getFloors();
	public void addShaft(Shaft shaft);
	public void addFloor(Floor floor);
	public Floor getFloorByNumber(int floorNum);
	public void floorChecker();
	public Lift[] getLifts();
	public void getParamsFromHouse(IBuilding house);
	public String toString();
}
