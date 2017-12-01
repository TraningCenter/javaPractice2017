package com.alegerd.model;

import com.alegerd.CommandReceiver;
import com.alegerd.Direction;
import com.alegerd.commands.interfaces.*;
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

    private Integer floorNumber;
    private Integer weight;
    private Integer maxWeight;
    private Integer maxFloor;

    private Direction currentDirection = null;
    private List<Integer> fromLift = new LinkedList<>();
    private List<FloorDTO> fromFloors = new LinkedList<>();

    private List<IPerson> peopleIn = new ArrayList<>();
    private List<IFloor> floors = new ArrayList<>();
    private List<LiftButton> buttons = new ArrayList<>();

    public Lift(Integer number, Integer floorNumber){
        this.number = number;
        this.floorNumber = floorNumber;
        makeButtons();
    }

    public Lift(Integer number, List<IFloor> floors){
        this.number = number;
        this.floorNumber = 0;
        this.floors = floors;
        makeButtons();
    }

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

    /**
     * Takes people from chosen floor
     * @param floor chosen floor
     */
    public void takePeopleFromFloor(IFloor floor){

    }

    /**
     * Adds new floor number to priority queue
     * @param floorNumber new floor number
     */
    public void addFloorToVisit(Integer floorNumber){

    }

    /**
     * Executes when lift comes to the target floor
     */
    public void arrived(){
        IFloor floor = floors.get(floorNumber);

        deleteFloorFromLists(floorNumber);

        List<IPerson> whoWantsToLeave = whoWantsToLeave(floor.getNumber());
        floor.takePeople(whoWantsToLeave);
        removeLeavedPeople(whoWantsToLeave);

        List<IPerson> newPeople = floor.getWaitingPeople();
        injectFloorButtonsToPeople(newPeople);
        addNewPeople(newPeople);

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

    public void thinkWhereToGo(){
        checkFloor(floorNumber);
        if(!bypassFromLiftCalls()){
            bypassFromFloorsCalls();
        }
    }

    @Override
    public Integer getNumberOfPeople() {
        return peopleIn.size();
    }

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
                currentDirection = Direction.UP;
                thinkWhereToGo();
            }
        }
    }

    @Override
    public Iterator<IPerson> getPeopleIterator() {
        return new Iterator<IPerson>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < peopleIn.size();
            }

            @Override
            public IPerson next() {
                IPerson next = peopleIn.get(i);
                i++;
                return next;
            }
        };
    }

    @Override
    public void forEachPerson(Consumer<? super IPerson> action) {
        for (IPerson p :
                peopleIn) {
            action.accept(p);
        }
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
        return false;
    }

    private void injectFloorButtonsToPeople(List<IPerson> people){
        for (IPerson person:
             people) {
            person.acceptFloorButtons(buttons);
        }
    }

    private List<IPerson> whoWantsToLeave(Integer floorNumber){
        List<IPerson> people = new ArrayList<>();
        for (IPerson person :
                peopleIn) {
            if (person.getDestinationFloor() == floorNumber) {
                people.add(person);
            }
        }

        return people;
    }

    private void removeLeavedPeople(List<IPerson> leaved){
        peopleIn.removeAll(leaved);
    }

    private void addNewPeople(List<IPerson> newPeople){
        for (IPerson person : newPeople) {
            if(checkWeight(person.getWeight())){
                peopleIn.add(person);
                person.chooseDestinationFloor();
            }
        }
    }

    private boolean checkWeight(Integer weight){
        //реализовать систему веса
        return true;
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
        for (FloorDTO floor:
             fromFloors) {
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
        for (Integer floor :
                fromLift) {
            if(floor == number) {
                arrived();
                return;
            }
        }
        for (FloorDTO dto :
                fromFloors) {
            if((dto.getNumber() == number) && (dto.getDir() == currentDirection)){
                arrived();
                return;
            }
        }

    }

    public Integer getNumber() {
        return number;
    }

    private void makeButtons(){
        for (IFloor floor:
             floors) {
            buttons.add(new LiftButton(this, floor.getNumber()));
        }
    }
}
