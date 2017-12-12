package execs;

import lift.Dispatcher;
import lift.Floor;
import lift.Lift;
import lift.LiftDirection;
import lift.PassengerDirection;
import lift.Transportable;
import models.Request;
import java.util.LinkedList;

public class ActLiftExecutable implements LiftExecutable {
	private Lift lift;
	private Floor floor;
	
	public ActLiftExecutable(Lift lift, Floor floor) {
		this.lift = lift;
		this.floor = floor;
	}

	public void execute() {
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (lift.getFloorNumbersToStop().contains(lift.getCurFloorNumber())) {
			if (lift.getFloorNumbersToStop().size() == 1 && 
					(lift.getLiftDirection() == LiftDirection.UP && lift.getRequestDirection() == LiftDirection.DOWN) ||
					(lift.getLiftDirection() == LiftDirection.DOWN && lift.getRequestDirection() == LiftDirection.UP)) {
				lift.setLiftDirection(lift.getRequestDirection());
				lift.setRequestDirection(LiftDirection.STOP);
			}				
			if (!lift.getPassengers().isEmpty()) {
				LinkedList<Transportable> listToLetOut = new LinkedList<Transportable>();
				for (Transportable t: lift.getPassengers()) {
					if (t.getDest() == lift.getCurFloorNumber())
						listToLetOut.add(t);
				}
				if (!listToLetOut.isEmpty())
					for (Transportable t: listToLetOut)
						lift.letOutPassenger(t);
				listToLetOut = null;
			}
			lift.getFloorNumbersToStop().remove(lift.getFloorNumbersToStop().indexOf(lift.getCurFloorNumber()));
			while (lift.getPassengers().size() < lift.getCapacity())  {
				for (Transportable t: floor.getWaitingList()) {
					if ((lift.getPassengers().size() < lift.getCapacity()) &&
						((t.getDirection() == PassengerDirection.UP && lift.getLiftDirection() == LiftDirection.UP) ||
						(t.getDirection() == PassengerDirection.DOWN && lift.getLiftDirection() == LiftDirection.DOWN))) {
						lift.addPassenger(t);
					}
					else {
						if (lift.getPassengers().size() == lift.getCapacity())
							break;
					}
				}
				if (lift.getLiftDirection() == LiftDirection.UP && lift.getCurFloorNumber() == lift.getMax()) {
					lift.setLiftDirection(LiftDirection.DOWN);
					continue;
				}
				if (lift.getLiftDirection() == LiftDirection.DOWN && lift.getCurFloorNumber() == lift.getMin()) {
					lift.setLiftDirection(LiftDirection.UP);
					continue;
				}
				break;
			}
			for (Transportable t: lift.getPassengers()) {
				if (floor.getWaitingList().contains(t))
					floor.removeWaitingPass(t);
			}
			for (Transportable t: floor.getWaitingList()) {
				if (t.getDirection() == PassengerDirection.UP && lift.getLiftDirection() == LiftDirection.UP) {
					Dispatcher.getBackRequest(new Request(lift.getCurFloorNumber(), PassengerDirection.UP));
					break;
				}
				if (t.getDirection() == PassengerDirection.DOWN && lift.getLiftDirection() == LiftDirection.DOWN) {
					Dispatcher.getBackRequest(new Request(lift.getCurFloorNumber(), PassengerDirection.DOWN));
					break;
				}
			}
			if (lift.getPassengers().size() == lift.getCapacity()) {
				LinkedList<Integer> removeFloorsToStop = new LinkedList<Integer>();
				for (Integer stopNum: lift.getFloorNumbersToStop()) {
					if (!lift.isDestFloor(stopNum)) {
						if (lift.getLiftDirection() == LiftDirection.UP)
							Dispatcher.getBackRequest(new Request(stopNum, PassengerDirection.UP));
						if (lift.getLiftDirection() == LiftDirection.DOWN)
							Dispatcher.getBackRequest(new Request(stopNum, PassengerDirection.DOWN));
						removeFloorsToStop.add(stopNum);					
					}
				}
				for (Integer i : removeFloorsToStop) {
					lift.getFloorNumbersToStop().remove(i);
				}
			}
			if (lift.getFloorNumbersToStop().isEmpty() && lift.getPassengers().isEmpty()) {
				lift.setLiftDirection(LiftDirection.STOP);
			}
			if (lift.getFloorNumbersToStop().isEmpty()) {
				for (Transportable t: lift.getPassengers()) {
					lift.getFloorNumbersToStop().add(t.getDest());
				}
			}
			return;
		}
		if (lift.getCurFloorNumber() == lift.getMax())
			lift.setLiftDirection(LiftDirection.DOWN);
		if (lift.getCurFloorNumber() == lift.getMin())
			lift.setLiftDirection(LiftDirection.UP);;
		if (lift.getLiftDirection() == LiftDirection.UP) {
			lift.setCurFloorNumber(lift.getCurFloorNumber()+1);
		}
		if (lift.getLiftDirection() == LiftDirection.DOWN)
			lift.setCurFloorNumber(lift.getCurFloorNumber()-1);
	}

}
