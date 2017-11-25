package com.alegerd.model;

import com.alegerd.model.interfaces.IFloor;

import java.util.*;
import java.util.function.Consumer;

/**
 * Floor
 */
public class Floor implements IFloor{

    private Integer number;
    private List<Person> peopleOn = new ArrayList<>();
    private List<Person> arrivedPeople = new LinkedList<>();
    private List<Person> waitingPeople = new ArrayList<>();

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
        for (Person person :
                peopleOn) {
            result += "   " + person.toString() + "\n";
        }
        return result;
    }

    /**
     * Adds person to "waiting" list
     * @param newPerson person
     */
    public void addWaitingPerson(Person newPerson){
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
    public void takePeople(List<Person> people) throws IllegalArgumentException{
        for (Person person :
                people) {
            addArrivedPerson(person);
        }
    }

    /**
     * Gives waiting people
     * @return list with waiting people
     */
    public List<Person> getWaitingPeople(){
        return waitingPeople;
    }

    private void addArrivedPerson(Person newPerson){
        if(newPerson == null)
            throw new IllegalArgumentException("Argument newPerson can't be null");
        if(personIsAlreadyOnFloor(newPerson))
            throw new IllegalArgumentException("Person you want to add is already on the floor");
        else {
            peopleOn.add(newPerson);
            arrivedPeople.add(newPerson);
        }
    }

    private boolean personIsAlreadyOnFloor(Person person){
        boolean result = false;
        for (Person p :
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
    
    public Iterator<Person> getPersonIterator(){
        return new Iterator<Person>() {
            int i = 0;
            
            @Override
            public boolean hasNext() {
                return i < peopleOn.size();
            }

            @Override
            public Person next() {
                Person next = peopleOn.get(i);
                i++;
                return next;
            }
        };
    }
    
    public void forEach(Consumer<? super Person> action){
        for (Person p :
                peopleOn) {
            action.accept(p);
        }
    }
}
