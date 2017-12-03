package tadite.javase.elevatorSimulator.model.passenger;

import tadite.javase.elevatorSimulator.model.misc.Observer;
import tadite.javase.elevatorSimulator.model.elevator.Elevator;

public class WaitInsideElevatorPersonState extends AbstractPersonState implements Observer {

    private Elevator elevator;

    public WaitInsideElevatorPersonState(Person person, Elevator elevator) {
        super(person);
        this.elevator = elevator;
    }

    @Override
    public void action() {

    }

    @Override
    public void update() {
       if (getPerson().getCurrentLocation().getLevel() == getPerson().getTargetLocation().getLevel()){
           getPerson().changeState(new GoToTargetPositionPersonState(getPerson()));
           elevator.removeObserver(this);
           getPerson().goOut();
       }
    }
}
