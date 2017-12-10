package output;

import lift.Floor;
import lift.Lift;

public class FloorOutput {
	private static final String[][] LIFT_TEMPLATE = {
		{"|"," ","|"},
		{"|"," ","|"},
		{"|","_","|"}};
	
	private int liftsCount;
	private Floor floor;
	private int floorsNum;
	

	public FloorOutput(int liftsCount, Floor floor, int floorsNum) {
		this.liftsCount = liftsCount;
		this.floor = floor;
		this.floorsNum = floorsNum;
	}
	
	public String[][] getFloor(){
		String[][] floorGrid = new String[3][3*(liftsCount + 1) + 3];
		stringFiller(floorGrid);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3*liftsCount; j+=3) {
				floorGrid[i][j] = LIFT_TEMPLATE[i][0];
				floorGrid[i][j+1] = LIFT_TEMPLATE[i][1];
				floorGrid[i][j+2] = LIFT_TEMPLATE[i][2];
			}
			if (i == 2) {
				for (int j = liftsCount*3; j < (liftsCount+1)*3; j++) {
					floorGrid[i][j] = "_";
				}
				int j = (liftsCount+1)*3;
				Integer hundreds = (floorsNum-floor.getNumber()-1)/100;
				Integer tens = (floorsNum-floor.getNumber()-1)%100/10;
				Integer digits = (floorsNum-floor.getNumber()-1)%10;
				floorGrid[i][j] = hundreds.toString();
				floorGrid[i][j+1] = tens.toString();
				floorGrid[i][j+2] = digits.toString();
			}
		}
//		for (int i = 0; i < floorGrid.length; i++) {
//			for (int j = 0; j < floorGrid[0].length; j++)
//				System.out.print(floorGrid[i][j]);
//			System.out.print("\n");
//		}
		return floorGrid;
	}
	public void stringFiller(String[][] s) {
		if (s == null || s.length == 0) return;
		for (int i = 0; i < s.length; i++)
			for (int j = 0; j < s[i].length; j++)
				s[i][j] = " ";
	}
}
