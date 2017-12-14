package execs;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import lift.Floor;
import lift.House;
import lift.IBuilding;
import lift.Lift;
import lift.LiftDirection;
import lift.PassengerDirection;
import lift.Shaft;
import models.Request;



public class DelegateRequestTest {
	IBuilding house;
	Deque<Request> requests;
	DelegateLiftExecutable delegateLiftExecutable;
	
	@Before
	public void init() {
		house = new House();
		house.addFloor(new Floor(0));
		house.addFloor(new Floor(1));
		house.addFloor(new Floor(2));
		house.addShaft(new Shaft(new Lift(3, 0, 0, 2)));
		
		Request req1 = new Request(0, PassengerDirection.UP);
		Request req2 = new Request(1, PassengerDirection.DOWN);
		Request req3 = new Request(1, PassengerDirection.UP);
		Request req4 = new Request(2, PassengerDirection.UP);
		Request req5 = new Request(2, PassengerDirection.DOWN);
		requests = new LinkedList<Request>();
		requests.offerLast(req1);
		requests.offerLast(req2);
		requests.offerLast(req3);
		requests.offerLast(req4);
		requests.offerLast(req5);
	}
	
	@Test
	public void testDelegations() {
		delegateLiftExecutable = new DelegateLiftExecutable(house,  requests);
		delegateLiftExecutable.execute();
		Assert.assertEquals(3, house.getLifts()[0].getFloorNumbersToStop().size());
		Assert.assertEquals(2, requests.size());
		Assert.assertEquals(LiftDirection.UP, house.getLifts()[0].getLiftDirection());
		Assert.assertEquals(LiftDirection.UP, house.getLifts()[0].getRequestDirection());
	}
}
