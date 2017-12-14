package entities;

import org.junit.Test;
import org.junit.Assert;

import lift.Controller;
import lift.Dispatcher;
import lift.Floor;
import lift.House;
import lift.IBuilding;
import lift.Lift;
import lift.PassengerDirection;
import lift.Shaft;
import models.Request;

public class DispatcherTest {
	IBuilding house;
	
	@Test
	public void dispatcherTest() {
		Controller c = new Controller();
		house = new House();
		house.addFloor(new Floor(0));
		house.addFloor(new Floor(1));
		house.addFloor(new Floor(2));
		house.addShaft(new Shaft(new Lift(5, 0, 0, 2)));
		Dispatcher.setHouse(house);
		Assert.assertEquals(true, Dispatcher.areRequestsEmpty());
		Dispatcher.checkEndOfSimulation();
		Dispatcher.nextStep();
		Dispatcher.addRequest(new Request(1, PassengerDirection.UP));
		Dispatcher.getBackRequest(new Request(0, PassengerDirection.UP));
		Dispatcher.addRequest(new Request(1, PassengerDirection.UP));
		Dispatcher.getBackRequest(new Request(0, PassengerDirection.UP));
		Assert.assertEquals(false, Dispatcher.areRequestsEmpty());
	}
}
