package com.alegerd.model;

import java.util.*;

/**
 * Floor
 */
public class Floor{
    public Integer number;
    private List<Person> peopleOn = new ArrayList<>();
    private List<Person> arrivedPeople = new LinkedList<>();
    private List<Person> waitingPeople = new ArrayList<>();

    public Floor(){

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
}
