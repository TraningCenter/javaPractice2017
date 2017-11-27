package com.alegerd.model;

import com.alegerd.CommandReceiver;
import com.alegerd.Direction;
import com.alegerd.commands.interfaces.LiftArrivedCommand;
import com.alegerd.commands.interfaces.LiftWereToGoCommand;
import com.alegerd.commands.interfaces.MoveLiftCommand;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.ILift;
import com.alegerd.model.interfaces.IPerson;

import java.util.*;
import java.util.function.Consumer;
/**
 * Lift
 */
public class Lift implements ILift{
    public Integer number;

    private Integer floorNumber;
    private Integer weight;
    private Integer maxWeight;
    private Integer maxFloor;
    private List<Integer> floorsToVisit = new ArrayList<>();
    private List<IPerson> peopleIn = new ArrayList<>();
    private List<IFloor> floors = new ArrayList<>();
    private Integer targetFloor;
    private Queue<Integer> floorsQueue = new LinkedList<>();

    public Lift(Integer number, Integer floorNumber){
        this.number = number;
        this.floorNumber = floorNumber;
    }

    public Lift(Integer number){
        this.number = number;
        this.floorNumber = 0;
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

        IFloor floor = floors.get(targetFloor);

        List<IPerson> whoWantsToLeave = whoWantsToLeave(targetFloor);
        floor.takePeople(whoWantsToLeave);
        removeLeavedPeople(whoWantsToLeave);

        List<IPerson> newPeople = floor.getWaitingPeople();
        addNewPeople(newPeople);
    }

    /**
     * Moves lift towards the next floor
     */
    public void moveOneFloor(){
        if(floorNumber < targetFloor){
            System.out.println(floorNumber);
            floorNumber++;
            CommandReceiver.addNewCommand(new LiftWereToGoCommand(this)); //едем вверх
        }
        else if(floorNumber > targetFloor){
            System.out.println(floorNumber);
            floorNumber--;
            CommandReceiver.addNewCommand(new LiftWereToGoCommand(this)); //едем вниз
        }
        else CommandReceiver.addNewCommand(new LiftArrivedCommand(this)); //приехали
    }

    int i = 0;
    public void thinkWhereToGo(){
        if(i == 0) {
            targetFloor = floorsQueue.poll();
            i=1;
        }
        CommandReceiver.addNewCommand(new MoveLiftCommand(this));
    }

    /**
     * Invokes when person pushes a floor-number-button
     * in lift to choose the floor he wants to go to
     */
    public void liftButtonPushed(){

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
     * Invokes when person calls the lift from floor
     */
    public void callingButtonPushed(Integer floorNumber, Direction direction){
        //System.out.print("Лифт " + number + " Вызов с этажа " + floorNumber);
        //String s = direction.equals(Direction.UP) ? " вверх" : " вниз";
        //System.out.println(s);
        floorsQueue.add(floorNumber - 1);
        CommandReceiver.addNewCommand(new LiftWereToGoCommand(this));
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
        for (IPerson person : leaved) {
            peopleIn.remove(person);
        }
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

    public Integer getNumber() {
        return number;
    }
}
