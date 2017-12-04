package ogkostin.elevator.model;
/**
 * Provides data and opportunities of the elevatorperson
 *
 *  @author Oleg Kostin
 */
public class Person implements Drawable {

    private int currentFloor;
    private int targetFloor;
    private int weight;
    private Direction direction;
    private boolean isOntarget = false;
    private Logger logger;


    public boolean enter(Elevator elevator, Floor floor) {
        if (elevator.checkCapacity(weight)) {
            elevator.openDoors();
            elevator.getPersons().add(this);
            elevator.setPersonCount(elevator.getPersonCount() + 1);
            elevator.setCurrentWeight(elevator.getCurrentWeight() + weight);
            floor.setPersonsCount(floor.getPersonsCount() - 1);
            elevator.addPriorityTargetFloor(targetFloor);
            logger.append("Person with weight " + weight + " enter the lift on " + currentFloor + " floor and going to " + targetFloor + " floor");
            return true;
        } else return false;
    }


    public void goOut(Elevator elevator, Floor floor) {

        elevator.openDoors();
        elevator.getPersons().remove(this);
        elevator.setPersonCount(elevator.getPersonCount() - 1);
        elevator.setCurrentWeight(elevator.getCurrentWeight() - weight);
        elevator.getPriorityTargetFloors().remove((Integer) floor.getNumber());
        floor.setCompletedPersonsCount(floor.getCompletedPersonsCount() + 1);
        logger.append("Person with weight " + weight + " left the lift on " + targetFloor + " floor");
        isOntarget = true;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


}
