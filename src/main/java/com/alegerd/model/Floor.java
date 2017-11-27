package com.alegerd.model;

import com.alegerd.model.buttons.ICallLiftButton;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.IPerson;

import java.util.*;
import java.util.function.Consumer;

/**
 * Floor
 */
public class Floor implements IFloor{

    private Integer number;
    private List<IPerson> peopleOn = new ArrayList<>();
    private List<IPerson> arrivedPeople = new LinkedList<>();
    private List<IPerson> waitingPeople = new ArrayList<>();

    private List<ICallLiftButton> liftButtons = new ArrayList<>();

    public Floor(Integer number){
        this.number = number;
    }

    @Override
    public String toString() {
        String result;
        result = "Floor number: " + number +
                    "\n Amount of people: " + peopleOn.size() +
                    "\n Amount of arrived people: " + arrivedPeople.size() +
                    "\n Amount of waiting people: " + waitingPeople.size() +
                    "\n People: ";
        for (IPerson person :
                peopleOn) {
            result += "   " + person.toString() + "\n";
        }
        return result;
    }

    /**
     * Adds person to "waiting" list
     * @param newPerson person
     */
    public void addWaitingPerson(IPerson newPerson){
        if(newPerson == null)
            throw new IllegalArgumentException("argument newPerson can't be null");
        if(personIsAlreadyOnFloor(newPerson))
            throw new IllegalArgumentException("Person you want to add is already on the floor");
        else {
            peopleOn.add(newPerson);
            waitingPeople.add(newPerson);
        }
    }

    /**
     * Takes people from lift
     * @param people people from lift
     * @throws IllegalArgumentException if some people are nulls or already on the floor
     */
    public void takePeople(List<IPerson> people) throws IllegalArgumentException{
        for (IPerson person :
                people) {
            addArrivedPerson(person);
        }
    }

    /**
     * Gives waiting people
     * @return list with waiting people
     */
    public List<IPerson> getWaitingPeople(){
        List<IPerson> people = new ArrayList<>();
        for (IPerson p :
                waitingPeople) {
            people.add(p);
        }
        removeWaitingPeople();
        return people;
    }

    private void removeWaitingPeople(){
        for (IPerson person : waitingPeople) {
            peopleOn.remove(person);
        }
        waitingPeople.clear();
    }

    private void addArrivedPerson(IPerson newPerson){
        if(newPerson == null)
            throw new IllegalArgumentException("Argument newPerson can't be null");
        if(personIsAlreadyOnFloor(newPerson))
            throw new IllegalArgumentException("Person you want to add is already on the floor");
        else {
            peopleOn.add(newPerson);
            arrivedPeople.add(newPerson);
        }
    }

    private boolean personIsAlreadyOnFloor(IPerson person){
        boolean result = false;
        for (IPerson p :
                peopleOn) {
            if(p == person)result = true;
            return result;
        }
        return result;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getNumberOfPeople(){
        return peopleOn.size();
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    
    public Iterator<IPerson> getPersonIterator(){
        return new Iterator<IPerson>() {
            int i = 0;
            
            @Override
            public boolean hasNext() {
                return i < peopleOn.size();
            }

            @Override
            public IPerson next() {
                IPerson next = peopleOn.get(i);
                i++;
                return next;
            }
        };
    }
    
    public void forEach(Consumer<? super IPerson> action){
        for (IPerson p :
                peopleOn) {
            action.accept(p);
        }
    }

    @Override
    public void acceptLiftButtons(List<ICallLiftButton> buttons) {
        this.liftButtons = buttons;
    }

    @Override
    public void injectLiftButtonsToPeople() {
        if(liftButtons == null) throw new NullPointerException("There no lift buttons on the floor");
        for (IPerson person : peopleOn) {
            person.acceptLiftButtons(liftButtons);
        }
    }
}
