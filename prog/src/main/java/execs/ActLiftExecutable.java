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
//		if (lift.getLiftDirection() == LiftDirection.STOP) return;
//		System.out.println("Floors to stop ");
//		for (Integer i: lift.getFloorNumbersToStop()) {
//			System.out.print(i + " ");
//		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (lift.getFloorNumbersToStop().contains(lift.getCurFloorNumber())) {
			if (lift.getFloorNumbersToStop().size() == 1 && lift.getLiftDirection() != lift.getRequestDirection())
				lift.setLiftDirection(lift.getRequestDirection());
			if (!lift.getPassengers().isEmpty()) {
				LinkedList<Transportable> listToLetOut = new LinkedList<Transportable>();
				for (Transportable t: lift.getPassengers()) {
					if (t.getDest() == lift.getCurFloorNumber())
						listToLetOut.add(t);
						//lift.letOutPassenger(t);
				}
				if (!listToLetOut.isEmpty())
					for (Transportable t: listToLetOut)
						lift.letOutPassenger(t);
				listToLetOut = null;
			}
			while (lift.getPassengers().size() < lift.getCapacity())  {
				boolean isBrokeOut = false;
				System.out.println("Lift " + lift.getId());
				System.out.println("LiftDirection " + lift.getLiftDirection());
				System.out.println("RequestDirection " + lift.getRequestDirection());
				System.out.println("People on floor " + floor.getWaitingList().size());
				System.out.println("People in lift " + lift.getPassengers().size());
				for (Transportable t: floor.getWaitingList()) {
					System.out.println("direction " + t.getDirection());
					if ((lift.getPassengers().size() < lift.getCapacity()) &&
						((t.getDirection() == PassengerDirection.UP && lift.getLiftDirection() == LiftDirection.UP) ||
						(t.getDirection() == PassengerDirection.DOWN && lift.getLiftDirection() == LiftDirection.DOWN))) {
						//floor.removeWaitingPass(t);
						lift.addPassenger(t);
						//break;
					}
					else {
						if (lift.getPassengers().size() == lift.getCapacity())
							break;
						//isBrokeOut = true;
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
			lift.getFloorNumbersToStop().remove(lift.getFloorNumbersToStop().indexOf(lift.getCurFloorNumber()));
			if (lift.getPassengers().size() == lift.getCapacity()) {
				for (Integer stopNum: lift.getFloorNumbersToStop()) {
					if (!lift.isDestFloor(stopNum)) {
						if (lift.getLiftDirection() == LiftDirection.UP)
							Dispatcher.getBackRequest(new Request(stopNum, PassengerDirection.UP));
						if (lift.getLiftDirection() == LiftDirection.DOWN)
							Dispatcher.getBackRequest(new Request(stopNum, PassengerDirection.DOWN));
						lift.getFloorNumbersToStop().remove(stopNum);						
					}						
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
		if (lift.getCurFloorNumber() == lift.getMax()|| lift.getCurFloorNumber() == lift.getMin())
			lift.switchDirection(lift.getLiftDirection());
		if (lift.getLiftDirection() == LiftDirection.UP) {
			lift.setCurFloorNumber(lift.getCurFloorNumber()+1);
		}
		if (lift.getLiftDirection() == LiftDirection.DOWN)
			lift.setCurFloorNumber(lift.getCurFloorNumber()-1);
	}

}
