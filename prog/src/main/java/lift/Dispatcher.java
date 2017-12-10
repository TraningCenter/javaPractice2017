package lift;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import execs.ActLiftExecutable;
import execs.DelegateLiftExecutable;
import execs.PushFloorButtonExecutable;
import models.Request;

public class Dispatcher {
	private static IBuilding house;
	private static Deque<Request> requests;
	
	public static void setHouse(IBuilding newHouse) {
		house = newHouse;
		requests = new LinkedList<Request>();
	}
	public static void addRequest(Request request) {
		Iterator<Request> it = requests.iterator();
		while(it.hasNext()) {
			if (it.next().equals(request))
				return;
		}
		requests.offerLast(request);
	}
	public static boolean areRequestsEmpty() {
		return requests.isEmpty();
	}
	public static void nextStep() {
		setDelegations();
		actLifts();
		checkEndOfSimulation();
	}
	public static void setDelegations() {
		if (!requests.isEmpty())
			ExeResolver.addExecutable(new DelegateLiftExecutable(house, requests));
		else
			System.out.println("requests are empty");
	}
	public static void actLifts() {
		Lift[] lifts = house.getLifts();
		for (int i = 0; i < lifts.length; i++) {
			if (lifts[i].getDirection() != LiftDirection.STOP)
				ExeResolver.addExecutable(new ActLiftExecutable(lifts[i], house.getFloorByNumber(lifts[i].getCurFloorNumber())));
		}
	}
	public static void getBackRequest(Request request) {
		requests.offerFirst(request);
	}
	public static void checkEndOfSimulation() {
		if (!requests.isEmpty()) return;
		for (Lift lift: house.getLifts()) {
			if (lift.getLiftDirection() != LiftDirection.STOP) return;
			if (!lift.getPassengers().isEmpty()) return;
		}
		for (Floor floor : house.getFloors()) {
			if (!floor.getWaitingList().isEmpty()) return;
		}
		ExeResolver.stopSimulation();
	}
}
