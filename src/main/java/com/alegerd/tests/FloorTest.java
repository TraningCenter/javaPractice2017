package com.alegerd.tests;

import com.alegerd.exceptions.LiftWeightException;
import com.alegerd.model.Floor;
import com.alegerd.model.Lift;
import com.alegerd.model.Person;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.IPerson;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class FloorTest extends Assert {

    private Map<Floor, String> toString = new HashMap<>();
    private Map<Floor, Integer> getNumber = new HashMap<>();
    private Map<Floor, Person> addPerson = new HashMap<>();

    @Before
    public void setUp(){
        toString.put(new Floor(0),
                "Floor number: " + 0 +
                "\n Amount of people: " + 0 +
                "\n Amount of arrived people: " + 0+
                "\n Amount of waiting people: " + 0+
                "\n People: ");

        getNumber.put(new Floor(0), 0);
        getNumber.put(new Floor(1), 1);
        getNumber.put(new Floor(2), 2);
        getNumber.put(new Floor(3), 3);

        addPerson.put(new Floor(0),new Person(0,50,0,0));
    }

    @After
    public void clearTestData() {
        toString.clear();
        getNumber.clear();
        addPerson.clear();
    }

    @Test
    public void testToString(){
        for (Map.Entry<Floor, String> entry :
                toString.entrySet()) {
            assertEquals(entry.getValue(), entry.getKey().toString());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingPersonException(){
        for (Map.Entry<Floor, Person> entry :
                addPerson.entrySet()) {
           entry.getKey().addWaitingPerson(null);
        }
    }

    @Test
    public void testAddingPersonWithIterator(){
        for (Map.Entry<Floor, Person> entry :
                addPerson.entrySet()) {
            IPerson person = entry.getValue();
            entry.getKey().addWaitingPerson(person);
            Iterator<IPerson> iter = entry.getKey().getPersonIterator();
            IPerson newPerson = iter.next();
         assertEquals(person,newPerson);
        }
    }

    @Test(expected = LiftWeightException.class)
    public void testAddingException() throws Exception{
        for (Map.Entry<Floor, Person> entry :
                addPerson.entrySet()) {
            IPerson person = entry.getValue();
            entry.getKey().addWaitingPerson(person);
            IPerson newPerson = entry.getKey().getWaitingPerson(0,10);
            assertEquals(person,newPerson);
        }
    }

    @Test
    public void testAddingExceptionMessage(){
        for (Map.Entry<Floor, Person> entry :
                addPerson.entrySet()) {
            IPerson person = entry.getValue();
            entry.getKey().addWaitingPerson(person);
            try {
                IPerson newPerson = entry.getKey().getWaitingPerson(0, 10);
            }
            catch (LiftWeightException e) {
                assertEquals("Lift overweight", e.getMessage());
            }
        }
    }

    @Test
    public void testGetNumber(){
        for (Map.Entry<Floor, Integer> entry :
                getNumber.entrySet()) {
            assertEquals(entry.getValue(), entry.getKey().getNumber());
        }
    }

    @Test
    public void testSetNumber(){
        for (Map.Entry<Floor, Integer> entry :
                getNumber.entrySet()) {
            Integer oldNumber = entry.getValue();
            IFloor floor = entry.getKey();
            floor.setNumber(oldNumber+1);
            long newNumber = floor.getNumber();
            assertEquals(oldNumber+1, newNumber);
        }
    }

    @Test
    public void testHowManyPeopleWaiting() throws Exception{
        for (Map.Entry<Floor, Person> entry :
                addPerson.entrySet()) {

            IFloor floor = entry.getKey();
            floor.addWaitingPerson(new Person(1,1,1,1));
            floor.addWaitingPerson(new Person(1,1,1,1));

            long count = floor.howManyPeopleWaiting();
            assertEquals(2,count);
        }
    }

    @Test
    public void testPeopleCount() throws Exception{
        for (Map.Entry<Floor, Person> entry :
                addPerson.entrySet()) {

            IFloor floor = entry.getKey();
            floor.addWaitingPerson(new Person(1,1,1,1));
            floor.addWaitingPerson(new Person(1,1,1,1));

            long count = floor.getNumberOfPeople();
            assertEquals(2,count);
        }
    }

    @Test
    public void testIteratorHasNext() {

        IFloor floor = new Floor(0);
        Iterator<IPerson> iterator = floor.getPersonIterator();
        Boolean b = iterator.hasNext();
        assertEquals(false,b);
    }

    @Test
    public void testIteratorNext() {

        IFloor floor = new Floor(0);
        IPerson testPerson = new Person(0,1,1,1);
        floor.addWaitingPerson(testPerson);
        Iterator<IPerson> iterator = floor.getPersonIterator();
        IPerson newPerson = null;
        if(iterator.hasNext()){
            newPerson = iterator.next();
        }
        assertEquals(testPerson, newPerson);
    }

    @Test
    public void testAddingPerson() throws Exception{
        for (Map.Entry<Floor, Person> entry :
                addPerson.entrySet()) {
            IPerson person = entry.getValue();
            entry.getKey().addWaitingPerson(person);
            IPerson newPerson = entry.getKey().getWaitingPerson(0,60);
            assertEquals(person,newPerson);
        }
    }

    @Test
    public void testTakePerson() throws Exception{
        IFloor floor = new Floor(0);
        List<IPerson> list = new ArrayList<>();
        IPerson testPerson = new Person(1,1,1,1);
        list.add(testPerson);
        floor.takePeople(list);
        IPerson person = null;
        Iterator<IPerson> iter = floor.getPersonIterator();
        if(iter.hasNext()){
            person = iter.next();
        }
        assertEquals(testPerson, person);
    }
}
