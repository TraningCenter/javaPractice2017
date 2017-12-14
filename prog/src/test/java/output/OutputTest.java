package output;

import org.junit.Assert;
import org.junit.Test;

import lift.Controller;
import lift.Floor;
import lift.House;
import lift.IBuilding;
import lift.Lift;
import lift.Passenger;
import lift.Shaft;

import org.junit.Before;

public class OutputTest {
	private IBuilding house;
	private OutputController outputController; 
	
	private static final String[][] emptyGridTest = {
		{"|"," ","|"," "," "," "," "," "," "},
		{"|"," ","|"," ","0","0"," "," "," "}, 
		{"|","_","|","_","_","_","0","0","2"},
		{"|"," ","|"," "," "," "," "," "," "},
		{"|"," ","|"," ","0","0"," "," "," "}, 
		{"|","_","|","_","_","_","0","0","1"},
		{"|"," ","|"," "," "," "," "," "," "},
		{"|","0","|"," ","0","0"," "," "," "}, 
		{"|","_","|","_","_","_","0","0","0"},
		{" ","1"," "," "," "," "," "," "," "}
	};
	private static final String[][] passGridTest = {
		{"|"," ","|"," "," "," "," "," "," "},
		{"|"," ","|"," ","0","0"," "," "," "}, 
		{"|","_","|","_","_","_","0","0","2"},
		{"|"," ","|"," "," "," "," "," "," "},
		{"|"," ","|"," ","0","0"," "," "," "}, 
		{"|","_","|","_","_","_","0","0","1"},
		{"|"," ","|"," "," "," "," "," "," "},
		{"|","0","|"," ","0","1"," "," "," "}, 
		{"|","_","|","_","_","_","0","0","0"},
		{" ","1"," "," "," "," "," "," "," "}
	};
	
	@Before
	public void init() {
		new Lift();
		house = new House();
		house.addFloor(new Floor(0));
		house.addFloor(new Floor(1));
		house.addFloor(new Floor(2));
		house.addShaft(new Shaft(new Lift(1, 0, 0, 2)));
		outputController = new OutputController();
	}
	
	@SuppressWarnings("deprecation")
	@Test 
	public void testOutput() {
		new Controller();
		outputController.configureOutput(house);
		outputController.showSituation(house);
		Assert.assertEquals(emptyGridTest, outputController.getGrid());
		house.getFloorByNumber(0).addWaiting(new Passenger(0, 1));
		outputController.showSituation(house);
		Assert.assertEquals(passGridTest, outputController.getGrid());		
	}
}
