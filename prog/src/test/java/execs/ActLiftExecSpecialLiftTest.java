package execs;

import lift.Dispatcher;
import lift.Floor;
import lift.House;
import lift.Lift;
import lift.LiftDirection;
import lift.Passenger;

import org.junit.Assert;
import org.junit.Test;

import execs.ActLiftExecutable;

public class ActLiftExecSpecialLiftTest {
	Lift lift;
	Floor floor;
	ActLiftExecutable exer;
	
	@Test
	public void testSpecialExecute() {
		clean();
		floor = new Floor(1);
		lift = new Lift(1, 1, 0, 2);
		exer = new ActLiftExecutable(lift, floor);
		lift.setLiftDirection(LiftDirection.UP);
		lift.setRequestDirection(LiftDirection.DOWN);
		lift.addFloorToStop(1);
		Passenger pass = new Passenger(1, 0);
		floor.addWaiting(pass);
		exer.execute();
		Assert.assertEquals(LiftDirection.DOWN, lift.getDirection());
		Assert.assertEquals(LiftDirection.STOP, lift.getRequestDirection());
		Assert.assertEquals(1, lift.getPassengers().size());
		Assert.assertEquals(pass, lift.getPassengers().get(0));
		Assert.assertEquals(1, lift.getFloorNumbersToStop().size());
		Assert.assertEquals((Integer)0, lift.getFloorNumbersToStop().get(0));
		Assert.assertEquals(0, floor.getWaitingList().size());
	}
	
	@Test
	public void testOrdinaryWorkExecute() {
		clean();
		floor = new Floor(1);
		lift = new Lift(1, 1, 0, 2);
		exer = new ActLiftExecutable(lift, floor);
		Passenger pass1 = new Passenger(0, 1);
		lift.addPassenger(pass1);
		lift.setLiftDirection(LiftDirection.UP);
		lift.setRequestDirection(LiftDirection.STOP);
		exer.execute();
		Assert.assertEquals(LiftDirection.STOP, lift.getDirection());
		Assert.assertEquals(0, lift.getPassengers().size());
		Assert.assertEquals(0, lift.getFloorNumbersToStop().size());
	}
	
	@Test
	public void testOrdinaryPassPassenger() {
		clean();
		Dispatcher.setHouse(new House());
		floor = new Floor(1);
		lift = new Lift(1, 1, 0, 2);
		exer = new ActLiftExecutable(lift, floor);
		lift.addFloorToStop(1);
		Passenger pass1 = new Passenger(1, 2);
		Passenger pass2 = new Passenger(1, 2);
		floor.addWaiting(pass1);
		floor.addWaiting(pass2);
		lift.setLiftDirection(LiftDirection.UP);
		lift.setRequestDirection(LiftDirection.STOP);
		exer.execute();
		Assert.assertEquals(LiftDirection.UP, lift.getDirection());
		Assert.assertEquals(1, lift.getPassengers().size());
		Assert.assertEquals(1, lift.getFloorNumbersToStop().size());
		Assert.assertEquals(1, floor.getWaitingList().size());
		Assert.assertEquals(pass1, lift.getPassengers().get(0));
		Assert.assertEquals(pass2, floor.getWaitingList().get(0));
	}
	
	@Test
	public void testOrdinarySwitchDirectionExecute() {
		clean();
		floor = new Floor(2);
		lift = new Lift(1, 2, 0, 2);
		exer = new ActLiftExecutable(lift, floor);
		Passenger pass1 = new Passenger(0, 2);
		Passenger pass2 = new Passenger(2, 1);
		lift.addFloorToStop(0);
		lift.addPassenger(pass1);
		lift.setLiftDirection(LiftDirection.UP);
		lift.setRequestDirection(LiftDirection.STOP);
		floor.addWaiting(pass2);
		exer.execute();
		Assert.assertEquals(LiftDirection.DOWN, lift.getDirection());
		Assert.assertEquals(1, lift.getPassengers().size());
		Assert.assertEquals(pass2, lift.getPassengers().get(0));
	}
	
	@Test
	public void testOrdinaryMotion() {
		clean();
		floor = new Floor(2);
		lift = new Lift(1, 2, 0, 2);
		exer = new ActLiftExecutable(lift, floor);
		lift.setLiftDirection(LiftDirection.UP);
		lift.setRequestDirection(LiftDirection.STOP);
		exer.execute();
		Assert.assertEquals(LiftDirection.DOWN, lift.getLiftDirection());
		Assert.assertEquals(1, lift.getCurFloorNumber());
	}
	
	private void clean() {
		floor = null;
		lift = null;
		exer = null;
	}
}
