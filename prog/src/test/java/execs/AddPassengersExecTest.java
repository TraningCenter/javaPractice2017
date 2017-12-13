package execs;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import input.DefaultInputConsole;
import input.InputController;
import lift.Floor;
import lift.House;
import lift.IBuilding;
import lift.Lift;
import lift.Passenger;
import lift.Shaft;
import lift.Transportable;
import execs.AddPassengersExecutable;

public class AddPassengersExecTest {
	private IBuilding house;
	private InputController inputController;
	private AddPassengersExecutable addPassengersExecutable;
	
	@Before
	public void init() {
		house = new House();
		house.addFloor(new Floor(0));
		house.addFloor(new Floor(1));
		house.addFloor(new Floor(2));
		house.addShaft(new Shaft(new Lift(1, 0, 0, 2)));
	}
	
	@Test
	public void testAddPassengers() {
		String testInput = "\npasss:1:1:2\npass:1:1:3:4\n"
				+ "pass:3:0:-1:2:3:1:2";
		inputController = new InputController(new DefaultInputConsole(new Scanner(testInput)));
		addPassengersExecutable = new AddPassengersExecutable(house, inputController);
		addPassengersExecutable.execute();
		LinkedList<Transportable> passengersOnFloors = new LinkedList<Transportable>();
		for(Floor floor : house.getFloors()) {
			if (!floor.getWaitingList().isEmpty()) 
				for (Transportable t: floor.getWaitingList()) {
					passengersOnFloors.add(t);
				}
		}			
		Assert.assertEquals(1, passengersOnFloors.size());
		Assert.assertEquals(1, passengersOnFloors.get(0).getStart());
		Assert.assertEquals(2, passengersOnFloors.get(0).getDest());
	}
}
