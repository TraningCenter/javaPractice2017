package lift;

import java.util.Deque;
import java.util.Iterator;

import execs.DelegateLiftExecutable;
import execs.PushFloorButtonExecutable;
import models.Request;

public class Dispatcher {
	private static IBuilding house;
	private static Deque<Request> requests;
	
	public static void setHouse(IBuilding newHouse) {
		house = newHouse;
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
	}
	public static void setDelegations() {
		if (!requests.isEmpty())
			ExeResolver.addExecutable(new DelegateLiftExecutable(house, requests));
	}
	public static void actLifts() {
		Lift[] lifts = house.getLifts();
		for (int i = 0; i < lifts.length; i++) {
			if (lifts[i].getDirection() != LiftDirection.STOP)
				lifts[i].actLift();
		}
	}
}
