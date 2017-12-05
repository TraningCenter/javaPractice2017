package com.alegerd.model;

import com.alegerd.commands.interfaces.CommandReceiver;
import com.alegerd.commands.interfaces.*;
import com.alegerd.exceptions.LiftWeightException;
import com.alegerd.model.buttons.IButton;
import com.alegerd.model.buttons.LiftButton;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.ILift;
import com.alegerd.model.interfaces.IPerson;

import java.util.*;
import java.util.function.Consumer;
/**
 * Lift
 */
public class Lift implements ILift{

    class FloorDTO{
        Integer number;
        Direction dir;

        public FloorDTO(Integer number, Direction dir) {
            this.number = number;
            this.dir = dir;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public Direction getDir() {
            return dir;
        }

        public void setDir(Direction dir) {
            this.dir = dir;
        }

        public boolean equals(FloorDTO other){
            return number.equals(other.getNumber())&&dir.equals(other.dir);
        }
    }

    public Integer number;
    private Integer floorNumber = 0;
    private Integer weight = 0;
    private Integer maxWeight;
    private Integer maxFloor;

    private Direction currentDirection = null;
    private List<Integer> fromLift = new LinkedList<>();
    private List<FloorDTO> fromFloors = new LinkedList<>();

    private List<IPerson> peopleIn = new ArrayList<>();
    private List<IFloor> floors = new ArrayList<>();
    private List<IButton> buttons = new ArrayList<>();

    public Lift(Integer number, Integer floorNumber){
        this.number = number;
        this.floorNumber = floorNumber;
        makeButtons();
    }

    public Lift(Integer number, List<IFloor> floors, Integer maxWeight, Integer floorNumber){
        this.number = number;
        this.floorNumber = 0;
        this.floors = floors;
        this.maxWeight = maxWeight;
        this.floorNumber = floorNumber;
        makeButtons();
    }

    public Lift(Integer number, List<IFloor> floors, Integer maxWeight){
        this.number = number;
        this.floorNumber = 0;
        this.floors = floors;
        this.maxWeight = maxWeight;
        makeButtons();
    }

    /**
     *
     * @return string information about class
     */
    @Override
    public String toString() {
        String result;
        result = "Lift number: " + number + ", number of people: " + peopleIn.size() + "\n";
        for (IPerson p :
                peopleIn) {
            result += "   " + p.toString() + "\n";
        }
        return result;
    }

    private void takePeopleFromFloorOneByOne(IFloor floor){
            int count = floor.howManyPeopleWaiting();
            try {
                for (int i = 0; i < count; i++) {
                    try {
                        IPerson newPerson = floor.getWaitingPerson(weight, maxWeight);
                        weight += newPerson.getWeight();
                        injectFloorButtonsToPeople(newPerson);
                        setNewLiftToPeople(newPerson);
                        addNewPeople(newPerson);
                    }catch (LiftWeightException e){}
                }
            }catch (NullPointerException e){}
    }

    /**
     * Executes when lift comes to the target floor
     */
    public void arrived(){
            IFloor floor = floors.get(floorNumber);

            takePeopleFromFloorOneByOne(floor);

            List<IPerson> whoWantsToLeave = whoWantsToLeave(floor.getNumber());
            floor.takePeople(whoWantsToLeave);
            removeLeavedPeople(whoWantsToLeave);

            deleteFloorFromLists(floorNumber);

    }

    /**
     * Moves lift towards the next floor
     */
    public void moveOneFloor(){
        if(currentDirection == Direction.UP){
            floorNumber++;
            CommandReceiver.addNewCommand(new LiftWereToGoCommand(this));
        }
        else if(currentDirection == Direction.DOWN){
            floorNumber--;
            CommandReceiver.addNewCommand(new LiftWereToGoCommand(this));
        }
    }

    /**
     * Lift chooses the direction (Up / Down)
     */
    public void thinkWhereToGo(){
        checkFloor(floorNumber);
        if(!bypassFromLiftCalls()){
            bypassFromFloorsCalls();
        }
    }

    /**
     *
     * @return number of people in lift
     */
    @Override
    public Integer getNumberOfPeople() {
        return peopleIn.size();
    }

    /**
     *
     * @return number of lift lift is on
     */
    @Override
    public Integer getFloorLiftOn() {
        return floorNumber;
    }

    /**
     * Invokes when person pushes a floor-number-button
     * in lift to choose the floor he wants to go to
     */
    public void liftButtonPushed(Integer floorNumber){
        if(isNotAlreadyInList(floorNumber)) {
            addFloorToFromLiftList(floorNumber);
        }
    }

    /**
     * Invokes when person calls the lift from floor
     */
    public void callingButtonPushed(Integer floorNumber, Direction direction){
        FloorDTO dto = new FloorDTO(floorNumber, direction);
        if(isNotAlreadyInList(dto)) {
            addFloorToFromFloorsList(dto);
            if(currentDirection == null){
                currentDirection = (floorNumber >= this.floorNumber)? Direction.UP : Direction.DOWN;
                thinkWhereToGo();
            }
        }
    }

    public Integer getNumber() {
        return number;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    private boolean bypassFromLiftCalls(){

        if(currentDirection == Direction.UP) {

            //есть люди в лифте, которые хотят ехать вверх
            for (Integer floor : fromLift) {
                if (floor > getFloorLiftOn()){
                    CommandReceiver.addNewCommand(new MoveLiftCommand(this));
                    return true;
                }
            }

            //есть люди в лифте, которые хотят ехать вниз
            for (Integer floor : fromLift) {
                if (floor < getFloorLiftOn()){
                    currentDirection = Direction.DOWN;
                    CommandReceiver.addNewCommand(new MoveLiftCommand(this));
                    return true;
                }
            }

        }
        else if(currentDirection == Direction.DOWN) {

            //есть люди в лифте, которые хотят ехать вниз
            for (Integer floor : fromLift) {
                if (floor < getFloorLiftOn()){
                    CommandReceiver.addNewCommand(new MoveLiftCommand(this));
                    return true;
                }
            }

            //есть люди в лифте, которые хотят ехать вверх
            for (Integer floor : fromLift) {
                if (floor > getFloorLiftOn()){
                    currentDirection = Direction.UP;
                    CommandReceiver.addNewCommand(new MoveLiftCommand(this));
                    return true;
                }
            }
        }
        else if(currentDirection == null) {

            //есть люди в лифте, которые хотят ехать вверх
            for (Integer floor : fromLift) {
                if (floor > getFloorLiftOn()){
                    currentDirection = Direction.UP;
                    CommandReceiver.addNewCommand(new MoveLiftCommand(this));
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bypassFromFloorsCalls(){
        if(currentDirection == Direction.UP) {

            for (FloorDTO floor : fromFloors) {
                if (floor.getNumber() > getFloorLiftOn()){
                    CommandReceiver.addNewCommand(new MoveLiftCommand(this));
                    return true;
                }
            }

            for (FloorDTO floor : fromFloors) {
                if (floor.getNumber() < getFloorLiftOn()){
                    currentDirection = Direction.DOWN;
                    CommandReceiver.addNewCommand(new MoveLiftCommand(this));
                    return true;
                }
            }

        }
        else if(currentDirection == Direction.DOWN) {

            for (FloorDTO floor : fromFloors) {
                if (floor.getNumber() < getFloorLiftOn()){
                    CommandReceiver.addNewCommand(new MoveLiftCommand(this));
                    return true;
                }
            }

            for (FloorDTO floor : fromFloors) {
                if (floor.getNumber() > getFloorLiftOn()){
                    currentDirection = Direction.UP;
                    CommandReceiver.addNewCommand(new MoveLiftCommand(this));
                    return true;
                }
            }
        }
        else if(currentDirection == null){
            for (FloorDTO floor : fromFloors) {
                if (floor.getNumber() > getFloorLiftOn()){
                    currentDirection = Direction.UP;
                    CommandReceiver.addNewCommand(new MoveLiftCommand(this));
                    return true;
                }
            }
        }
        return false;
    }

    private void injectFloorButtonsToPeople(IPerson person){
        person.acceptFloorButtons(buttons);
    }

    private List<IPerson> whoWantsToLeave(Integer floorNumber){
        List<IPerson> people = new ArrayList<>();
        peopleIn.forEach(person -> {
            if(person.getDestinationFloor() == floorNumber) people.add(person);
        });

        return people;
    }

    private void removeLeavedPeople(List<IPerson> leaved){
        for (IPerson person :
                leaved) {
            weight -= person.getWeight();
        }
        peopleIn.removeAll(leaved);
    }

    private void addNewPeople(IPerson person){
            peopleIn.add(person);
            person.chooseDestinationFloor();
    }

    public void addFloorToFromLiftList(Integer floor) {
        for (int i = 0; i < fromLift.size(); i++){
            Integer num = fromLift.get(i);
            if(num > floor){
                fromLift.add(i, floor);
                return;
            }
        }

        fromLift.add(floor);
    }

    private boolean isNotAlreadyInList(FloorDTO dto){
        for (FloorDTO d: fromFloors) {
            if(d.equals(dto)){
                return false;
            }
        }
        for (Integer d: fromLift) {
            if(d.equals(dto.getNumber())){
                return false;
            }
        }
        return true;
    }

    private boolean isNotAlreadyInList(Integer floorNumber){
        for (FloorDTO d: fromFloors) {
            if(d.getNumber().equals(floorNumber)){
                return false;
            }
        }
        for (Integer d: fromLift) {
            if(d.equals(floorNumber)){
                return false;
            }
        }
        return true;
    }

    private void deleteFloorFromLists(Integer number){
        fromLift.remove(number);
        FloorDTO del = null;
        for (FloorDTO floor : fromFloors) {
            if(floor.getNumber() == number){
                del = floor;
            }
        }
        if(del != null)
            fromFloors.remove(del);
    }

    private void addFloorToFromFloorsList(FloorDTO dto) {
        for (int i = 0; i < fromFloors.size(); i++){
            Integer num = fromFloors.get(i).getNumber();
            if(num > dto.getNumber()){
                fromFloors.add(i, dto);
                return;
            }
        }

        fromFloors.add(dto);
    }

    private void checkFloor(Integer number){
        for (Integer floor : fromLift) {
            if(floor == number) {
                arrived();
                return;
            }
        }
        for (FloorDTO dto : fromFloors) {
            if(dto.getNumber() == number){
                if(dto.getDir() == currentDirection) {
                    arrived();
                    return;
                }
                else if(changeDirection(currentDirection)){
                    arrived();
                    return;
                }
            }
        }

    }

    /**
     * Checks maybe lift needs to change direction
     * @param direction
     * @return
     */
    private boolean changeDirection(Direction direction){
        if(currentDirection == Direction.UP) {
            for (Integer floor : fromLift) {
                if (floor > getFloorLiftOn()) {
                    return false;
                }
            }

            for (FloorDTO floor : fromFloors) {
                if (floor.getNumber() > getFloorLiftOn()) {
                    return false;
                }
            }
            currentDirection = Direction.DOWN;
            return true;
        }
        if(currentDirection == Direction.DOWN) {
            for (Integer floor : fromLift) {
                if (floor < getFloorLiftOn()) {
                    return false;
                }
            }

            for (FloorDTO floor : fromFloors) {
                if (floor.getNumber() < getFloorLiftOn()) {
                    return false;
                }
            }
            currentDirection = Direction.UP;
            return true;
        }
        return false;
    }

    private void makeButtons(){
        for (IFloor floor:
             floors) {
            buttons.add(new LiftButton(this, floor.getNumber()));
        }
    }

    private void setNewLiftToPeople(IPerson person){
        person.setWaitsForLiftNumber(number);
    }
}
