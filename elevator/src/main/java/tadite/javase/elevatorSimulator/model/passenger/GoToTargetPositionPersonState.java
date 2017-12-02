package tadite.javase.elevatorSimulator.model.passenger;

public class GoToTargetPositionPersonState extends AbstractPersonState {
    public GoToTargetPositionPersonState(Person person) {
        super(person);
    }

    @Override
    public void action() {
        if (getPerson().getTargetLocation().getPosition() == getPerson().getCurrentLocation().getPosition()){
            getPerson().changeState(new StopAtPositionPersonState(getPerson()));
            getPerson().setInactive();
            return;
        }

        if (getPerson().getTargetLocation().getPosition() < getPerson().getCurrentLocation().getPosition())
            getPerson().moveLeft();
        else
            getPerson().moveRight();
    }
}
