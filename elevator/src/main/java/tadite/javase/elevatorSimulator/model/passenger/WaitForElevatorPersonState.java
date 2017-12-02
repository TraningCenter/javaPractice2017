package tadite.javase.elevatorSimulator.model.passenger;

import tadite.javase.elevatorSimulator.model.elevator.Elevator;
import tadite.javase.elevatorSimulator.model.elevator.ElevatorDoor;
import tadite.javase.elevatorSimulator.model.misc.Observer;
import tadite.javase.elevatorSimulator.model.floor.Slot;

public class WaitForElevatorPersonState extends AbstractPersonState implements Observer {
    public WaitForElevatorPersonState(Person person) {
        super(person);
    }

    @Override
    public void action() {

    }

    @Override
    public void update() {
        Slot slotNow = getPerson().getCurrentSlot();
        if (slotNow instanceof ElevatorDoor) {
            Elevator elevator = ((ElevatorDoor) slotNow).getElevator();
            goInElevator(elevator);
            elevator.removeObserver(this);
            goNextState(elevator);
        }
        else {
        }
    }

    private void goInElevator(Elevator elevator) {
        getPerson().goIn(elevator);
        elevator.pushButton(getPerson().getTargetLocation().getLevel());
    }

    private void goNextState(Elevator elevator) {
        WaitInsideElevatorPersonState nextState = new WaitInsideElevatorPersonState(getPerson(),elevator);
        elevator.addObserver(nextState);
        getPerson().changeState(nextState);
    }
}

