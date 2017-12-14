package execs;


import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import lift.Floor;
import lift.House;
import lift.IBuilding;
import lift.Lift;
import lift.Passenger;
import lift.Shaft;


public class PushFloorButtonExecTest {
	IBuilding house;
	PushFloorButtonExecutable pushFloorButtonExecutable;
	
	@Before
	public void init() {
		house = new House();
		house.addFloor(new Floor(0));
		house.addFloor(new Floor(1));
		house.addFloor(new Floor(2));
		house.addShaft(new Shaft(new Lift(5, 0, 0, 2)));
		
		Passenger pass1 = new Passenger(0, 1);
		Passenger pass2 = new Passenger(0, 2);
		Passenger pass3 = new Passenger(1, 2);
		Passenger pass4 = new Passenger(2, 1);
		Passenger pass5 = new Passenger(2, 0);
		house.getFloors().get(0).addWaiting(pass1);
		house.getFloors().get(0).addWaiting(pass2);
		house.getFloors().get(1).addWaiting(pass3);
		house.getFloors().get(2).addWaiting(pass4);
		house.getFloors().get(2).addWaiting(pass5);
	}
	
	@Test
	public void testPushButton() {
		Assert.assertEquals(false, house.getFloors().get(0).isUpPressed());
		Assert.assertEquals(false, house.getFloors().get(1).isUpPressed());
		Assert.assertEquals(false, house.getFloors().get(2).isDownPressed());
		
		pushFloorButtonExecutable = new PushFloorButtonExecutable(house);
		pushFloorButtonExecutable.execute();
		
		Assert.assertEquals(true, house.getFloors().get(0).isUpPressed());
		Assert.assertEquals(true, house.getFloors().get(1).isUpPressed());
		Assert.assertEquals(true, house.getFloors().get(2).isDownPressed());
	}
}
