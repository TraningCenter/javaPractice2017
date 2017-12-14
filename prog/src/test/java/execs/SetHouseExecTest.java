package execs;

import org.junit.Before;
import org.junit.Test;

import input.DefaultInputConsole;
import input.InputController;

import java.util.LinkedList;
import java.util.Scanner;

import org.junit.Assert;

import lift.Controller;
import lift.Floor;
import lift.House;
import lift.IBuilding;
import lift.Lift;
import lift.Passenger;
import lift.Shaft;
import execs.SetHouseExecutable;

public class SetHouseExecTest {
	private Controller controller;
	private IBuilding house;
	private InputController inputController;
	private SetHouseExecutable setHouseExecutable;
	
	private SetWaitingPassengerExecutable setWaitingPassengerExecutable;
	
	@Before
	public void init() {
		controller = new Controller();
		String testInput = "Floor:3:Lift:3:8:8:8:Pass:2:0:1:0:2\n";
		inputController = new InputController(new DefaultInputConsole(new Scanner(testInput)));
		house = new House();
	}
	
	@Test
	public void testSetHouse() {
		setHouseExecutable = new SetHouseExecutable(house, inputController);
		setHouseExecutable.execute();
		Assert.assertEquals(3, house.getFloors().size());
		Assert.assertEquals(3, house.getShafts().size());
		Assert.assertEquals(3, house.getLifts().length);
		Assert.assertEquals(false, Controller.isQueueEmpty());		
	}
	
	@Test
	public void testSetDefaultHouse() {
		//house = new House();
		String testInput = "1";
		inputController = new InputController(new DefaultInputConsole(new Scanner(testInput)));
		
		setHouseExecutable = new SetHouseExecutable(house, inputController);
		setHouseExecutable.execute();
		Assert.assertEquals(3, house.getFloors().size());
		Assert.assertEquals(3, house.getShafts().size());
		Assert.assertEquals(3, house.getLifts().length);
		Assert.assertEquals(false, Controller.isQueueEmpty());
		
		//house = new House();
		testInput = "2";
		inputController = new InputController(new DefaultInputConsole(new Scanner(testInput)));
		
		setHouseExecutable = new SetHouseExecutable(house, inputController);
		setHouseExecutable.execute();
		Assert.assertEquals(5, house.getFloors().size());
		Assert.assertEquals(3, house.getShafts().size());
		Assert.assertEquals(3, house.getLifts().length);
		Assert.assertEquals(false, Controller.isQueueEmpty());	
	}

	@Test
	public void setPassengersTest() {
		house = new House();
		house.addFloor(new Floor(0));
		house.addFloor(new Floor(1));
		house.addFloor(new Floor(2));
		house.addShaft(new Shaft(new Lift(1, 0, 0, 2)));
		LinkedList<Passenger> passengers_0 = new LinkedList<Passenger>();
		LinkedList<Passenger> passengers = new LinkedList<Passenger>();
		passengers.add(new Passenger(0, 1));
		passengers.add(new Passenger(1, 2));
		passengers.add(new Passenger(2, 0));
		setWaitingPassengerExecutable = new SetWaitingPassengerExecutable(house, inputController, passengers_0);
		setWaitingPassengerExecutable.execute();
		Assert.assertEquals(0, getPassNum(house));
		setWaitingPassengerExecutable = new SetWaitingPassengerExecutable(house, inputController, passengers);
		setWaitingPassengerExecutable.execute();
		Assert.assertEquals(3, getPassNum(house));
	}
	
	private int getPassNum(IBuilding house) {
		int cPass = 0;
		for (Floor floor: house.getFloors()) {
			if (!floor.getWaitingList().isEmpty())
				cPass += floor.getWaitingList().size();
		}
		return cPass;
	}
}
