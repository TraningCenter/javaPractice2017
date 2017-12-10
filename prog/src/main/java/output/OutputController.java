package output;

import lift.Floor;
import lift.IBuilding;
import lift.Lift;

public class OutputController {
	private final int LIFT_WIDTH = 3;
	private final int FLOOR_HEIGHT = 3;	
	
	private String[][] grid;
	private static int floorsNum; 
	private static int liftsCount;
	
	public void configureOutput(IBuilding house) {
		floorsNum = house.getFloors().size();
		liftsCount = house.getShafts().size();
		int houseWidth = LIFT_WIDTH*(liftsCount + 1) + 3;
		int houseHeight = FLOOR_HEIGHT*floorsNum + 1;
		grid = new String[houseHeight][houseWidth];
		for (int i = 0; i < houseHeight; i++)
			for (int j = 0; j < houseWidth; j++)
				grid[i][j] = " ";
	}
	public void showSituation(IBuilding house) {
		int floorCounter = 0;
		for (Floor floor: house.getFloors()){
			FloorOutput fout = new FloorOutput(house.getLifts().length, floor);
			String[][] floorGrid = fout.getFloor();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < floorGrid[0].length; j++) {
					grid[i+3*floorCounter][j] = floorGrid[i][j];
				}
				if (i == 1) {
					Integer tens = floor.getWaitingList().size()/10%10;
					Integer digits = floor.getWaitingList().size()%10;
					grid[i][house.getLifts().length+1] = tens.toString();
					grid[i][house.getLifts().length+2] = digits.toString();
				}
			}
			floorCounter++;
		}
		for (Lift lift: house.getLifts()) {
			Integer tens = lift.getPassengers().size()%10;
			Integer digits = lift.getId()%10;
			grid[1 + (house.getFloors().size() - lift.getCurFloorNumber() - 1)*3][1+(lift.getId()-1)*3] = tens.toString();
			grid[grid.length-1][1+(lift.getId()-1)*3] = digits.toString();
		}
		showGrid(grid);
	}
	public void showGrid(String[][] grid) {
		if (grid == null) return;
		System.out.println("\033[H\033[2J");
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++)
				System.out.print(grid[i][j]);
			System.out.print("\n");
		}
	}
	
	
	public String multyStr(String str, int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(str);
		}
		return sb.toString();
	}
}
