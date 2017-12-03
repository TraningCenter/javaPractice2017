package tadite.javase.elevatorSimulator.model.passenger;

import tadite.javase.elevatorSimulator.model.elevator.ElevatorDoor;
import tadite.javase.elevatorSimulator.model.floor.Slot;

import java.util.ArrayList;
import java.util.List;

public class GoToElevatorDoorPersonState extends AbstractPersonState {
    private List<Boolean> visitedSlots = new ArrayList<>();
    private int targetPosition=-1;

    public GoToElevatorDoorPersonState(Person person) {
        super(person);
        visitedSlots = new ArrayList<Boolean>()
        {{
            for(int i=0;i<getPerson().getCurrentFloor().getSlotsCount();i++)
                add(false);
        }};
    }

    public void action() {
        Slot slotNow = getPerson().getCurrentSlot();
        visitedSlots.set(getPerson().getCurrentLocation().getPosition(), true);

        if (slotNow instanceof ElevatorDoor) {
            pressButtonAndWait((ElevatorDoor) slotNow);
        } else {
            if (targetPosition < 0 || targetPosition == getPerson().getCurrentLocation().getPosition())
                targetPosition = getNearestNotVisitedPosition();

            moveToTarget();
        }
    }

    private void pressButtonAndWait(ElevatorDoor elevatorDoor) {
        if (elevatorDoor.getConfig().getMinLevel() <= getPerson().getTargetLocation().getLevel() &&
                elevatorDoor.getConfig().getMaxLevel() >= getPerson().getTargetLocation().getLevel() &&
                elevatorDoor.getConfig().getMinLevel() <= getPerson().getCurrentLocation().getLevel() &&
                elevatorDoor.getConfig().getMaxLevel() >= getPerson().getCurrentLocation().getLevel()) {
            WaitForElevatorPersonState nextState = new WaitForElevatorPersonState(getPerson());
            elevatorDoor.pushButton();
            elevatorDoor.addObserver(nextState);
            getPerson().changeState(nextState);
        }
        else {
            targetPosition = getNearestNotVisitedPosition();
            moveToTarget();
        }
    }

    private void moveToTarget() {
        if (targetPosition>getPerson().getCurrentLocation().getPosition())
            getPerson().moveRight();
        else
            getPerson().moveLeft();
    }

    private int getNearestNotVisitedPosition() {

        int positionToLeft = getNearestNotVisitedPositionToLeft();
        int positionToRight = getNearestNotVisitedPositionToRight();

        return (positionToRight - getPerson().getTargetLocation().getPosition() >
                getPerson().getTargetLocation().getPosition() - positionToLeft)?
                positionToLeft:
                positionToRight;
    }

    private int getNearestNotVisitedPositionToRight() {
        int positionToRight = -1;
        for(int i=getPerson().getTargetLocation().getPosition();i<visitedSlots.size();i++){
            if(!visitedSlots.get(i)){
                positionToRight=i;
                break;
            }
        }
        return positionToRight;
    }

    private int getNearestNotVisitedPositionToLeft() {
        int positionToLeft = -1;

        for(int i=getPerson().getTargetLocation().getPosition();i>=0;i--){
            if(!visitedSlots.get(i)){
                positionToLeft=i;
                break;
            }
        }
        return positionToLeft;
    }
}
