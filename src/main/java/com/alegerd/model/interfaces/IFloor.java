package com.alegerd.model.interfaces;

import com.alegerd.exceptions.LiftWeightException;
import com.alegerd.model.Person;
import com.alegerd.model.buttons.ICallLiftButton;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public interface IFloor {
     Integer getNumberOfPeople();

     void setNumber(Integer number);

     String toString();

    /**
     * Adds person to "waiting" list
     * @param newPerson person
     */
     void addWaitingPerson(IPerson newPerson);

    /**
     * Takes people from lift
     * @param people people from lift
     * @throws IllegalArgumentException if some people are nulls or already on the floor
     */
     void takePeople(List<IPerson> people) throws IllegalArgumentException;

    /**
     * Gives waiting people
     * @return list with waiting people
     */
     List<IPerson> getWaitingPeople();

     Integer getNumber();

     Iterator<IPerson> getPersonIterator();

     void forEach(Consumer<? super IPerson> action);

     void acceptLiftButtons(List<ICallLiftButton> buttons);

     void injectLiftButtonsToPeople();

     Integer howManyPeopleWaiting();

     IPerson getWaitingPerson(Integer liftCurrentWeight, Integer liftMaxWeight) throws LiftWeightException;
}
