package execs;

import java.util.Deque;

import lift.IBuilding;
import lift.Lift;
import lift.LiftDirection;
import lift.PassengerDirection;
import models.Request;

public class DelegateLiftExecutable implements LiftExecutable {
	private IBuilding house;
	private Deque<Request> requests;

	public DelegateLiftExecutable(IBuilding house, Deque<Request> requests) {
		this.house = house;
		this.requests = requests;
	}
	public void execute() {
		Lift[] lifts = house.getLifts();
		int cycleCounter = 0;
		int dequeLength = 0;
		while (!requests.isEmpty()) {
			Lift chosen = null;
			Request curRequest = requests.peekFirst();
			int chosenDistance = house.getFloors().size();
			int curLiftDistance = chosenDistance;
			for (Lift lift: lifts) {
				if (lift.getDirection() == LiftDirection.STOP) {
					curLiftDistance = Math.abs(lift.getCurFloorNumber() - curRequest.getFloorNumber());
					if (curLiftDistance <= chosenDistance) {
						chosenDistance = curLiftDistance;
						chosen = lift;
					}					
				}					
			}
			if (chosen != null) {
				chosen.delegateRequest(requests.pollFirst());
				continue;
			}
			chosenDistance = house.getFloors().size();
			curLiftDistance = chosenDistance;
			curRequest = requests.peekFirst();
			for (Lift lift: lifts) {
				curLiftDistance = Math.abs(lift.getCurFloorNumber() - curRequest.getFloorNumber());
				if (!lift.isFull()
						&& (lift.getDirection() == LiftDirection.UP && curRequest.getFloorDirection() == PassengerDirection.UP 
									&& lift.getCurFloorNumber() <= curRequest.getFloorNumber() ||
								lift.getDirection() == LiftDirection.DOWN && curRequest.getFloorDirection() == PassengerDirection.DOWN
									&& lift.getCurFloorNumber() >= curRequest.getFloorNumber())
						&& curLiftDistance <= chosenDistance
						&& !lift.isSpecialDirection()) {
					chosenDistance = curLiftDistance;
					chosen = lift;
				}
			}
			if (chosen == null) {
				dequeLength = requests.size();
				if (cycleCounter < dequeLength) {
					requests.offerLast(requests.pollFirst());
					cycleCounter++;
					continue;
				}
				break;
			}
			chosen.delegateRequest(requests.pollFirst());
		}
	}

}
