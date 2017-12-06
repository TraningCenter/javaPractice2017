package lift;

public interface IBuilding {
	public void addShaft(Shaft shaft);
	public void addFloor(Floor floor);
	public Floor getFloorByNumber(int floorNum);
}
