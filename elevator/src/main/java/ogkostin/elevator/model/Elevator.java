package ogkostin.elevator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides data and opportunities of the elevator
 *
 *  @author Oleg Kostin
 */
public class Elevator implements Drawable,Observable {

    private int currentFloor;
    private List<Integer> targetFloors = new ArrayList<>();
    private List<Integer> priorityTargetFloors = new ArrayList<>();
    private int height;
    private int leftPadding;
    private int permissibleWeight;
    private int personCount = 0;
    private List<Observer> observers = new ArrayList<>();
    private List<Person> peronsCallers = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private Direction direction = Direction.none;
    private int waitingTime;
    private boolean isArrived = true;
    private int currentWeight = 0;
    private Integer ownPriorityFloor = 0;
    private Logger logger;

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLeftPadding() {
        return leftPadding;
    }

    public void setLeftPadding(int leftPadding) {
        this.leftPadding = leftPadding;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getPermissibleWeight() {
        return permissibleWeight;
    }

    public void setPermissibleWeight(int permissibleWeight) {
        this.permissibleWeight = permissibleWeight;
    }


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update((Integer) currentFloor, direction, this);
    }


    public void openDoors() {
        waitingTime = 5;
        logger.append("Lift with capacity " + permissibleWeight + " stay on " + currentFloor + " floor");
        // isStaying=true;
    }

    public boolean checkCapacity(int weight) {
        if (currentWeight + weight <= permissibleWeight)
            return true;
        else
            return false;
    }

    public void move() {
        checkPriorityTargetFloors();
        if (priorityTargetFloors.size() != 0) {
            if (waitingTime > 0) {
                isArrived = false;
                waitingTime--;
            } else {
                isArrived = false;
                switch (direction) {
                    case down:
                        height++;
                        break;
                    case up:
                        height--;
                    case none:
                        //!!!!!!можно добавит функцию ехать до ближайшего
                        break;
                }
            }
        } else {
            isArrived = true;
            direction = Direction.none;
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public List<Integer> getTargetFloors() {
        return targetFloors;
    }

    private void checkPriorityTargetFloors() {
        if (priorityTargetFloors.size() == 0) {
            int min = 100;
            int newPriority = 1;
            for (Integer number : targetFloors) {
                if (Math.abs(number - currentFloor) < min) {
                    min = Math.abs(number - currentFloor);
                    newPriority = number;
                }
            }
            addPriorityTargetFloor((Integer) newPriority);
            ownPriorityFloor = newPriority;
        }
    }


    public Integer getOwnPriorityFloor() {
        return ownPriorityFloor;
    }

    public void deleteOwnPriorityFloor() {
        priorityTargetFloors.remove(ownPriorityFloor);
        ownPriorityFloor = 0;

    }

    ;


    public void addPriorityTargetFloor(Integer number) {
        priorityTargetFloors.add(number);
        Collections.sort(priorityTargetFloors);
        if (currentFloor < number) {
            direction = Direction.up;
            logger.append("Lift with capacity " + permissibleWeight + " go up from " + currentFloor + " floor");
        } else {
            if (currentFloor > number) {
                direction = Direction.down;
                Collections.reverse(priorityTargetFloors);
                logger.append("Lift with capacity " + permissibleWeight + " go down from " + currentFloor + " floor");
            } else {
                direction = Direction.none;
            }
        }
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setTargetFloors(List<Integer> targetFloors) {
        this.targetFloors = targetFloors;
    }

    public List<Integer> getPriorityTargetFloors() {
        return priorityTargetFloors;
    }

    public Direction getDirection() {
        return direction;
    }
}
