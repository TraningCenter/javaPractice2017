package com.alegerd.model.interfaces;

import com.alegerd.model.Person;

import java.util.List;

public interface IFloor {
     Integer getNumberOfPeople();

     void setNumber(Integer number);

     String toString();

    /**
     * Adds person to "waiting" list
     * @param newPerson person
     */
     void addWaitingPerson(Person newPerson);

    /**
     * Takes people from lift
     * @param people people from lift
     * @throws IllegalArgumentException if some people are nulls or already on the floor
     */
     void takePeople(List<Person> people) throws IllegalArgumentException;

    /**
     * Gives waiting people
     * @return list with waiting people
     */
     List<Person> getWaitingPeople();
}
