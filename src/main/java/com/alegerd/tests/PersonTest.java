package com.alegerd.tests;

import com.alegerd.model.Person;
import com.alegerd.model.interfaces.IPerson;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PersonTest extends Assert {

    private Map<Person, Integer> checkId = new HashMap<>();
    private Map<Person, Integer> checkDestFloor = new HashMap<>();
    private Map<Person, String> toString = new HashMap<>();
    private Map<Person, Integer> checkFloor = new HashMap<>();
    private Map<Person, Integer> checkWeight = new HashMap<>();
    private Map<Person, Integer> checkLiftNumber = new HashMap<>();

    @Before
    public void setUp(){
        checkId.put(new Person(1,45,0,2), 1);
        checkId.put(new Person(2,45,0,2), 2);
        checkId.put(new Person(3,45,0,2), 3);

        checkDestFloor.put(new Person(1,45,0,2), 2);
        checkDestFloor.put(new Person(2,45,0,2), 2);
        checkDestFloor.put(new Person(3,45,0,2), 2);

        checkFloor.put(new Person(1,45,0,2), 0);
        checkFloor.put(new Person(2,45,20,2), 20);
        checkFloor.put(new Person(3,45,10,2), 10);

        checkWeight.put(new Person(1,35,0,2), 35);
        checkWeight.put(new Person(2,25,20,2), 25);
        checkWeight.put(new Person(3,65,10,2), 65);

        checkLiftNumber.put(new Person(1,35,0,2,1), 1);
        checkLiftNumber.put(new Person(2,25,20,2,0), 0);
        checkLiftNumber.put(new Person(3,65,10,2,2), 2);

        toString.put(new Person(1,45,0,2),
                "Person: id: " + 1 + ", weight: " + 45 + ", destination: " + 2 + " floor.\n");

        toString.put(new Person(2,35,0,4),
                "Person: id: " + 2 + ", weight: " + 35 + ", destination: " + 4 + " floor.\n");

        toString.put(new Person(3,46,0,9),
                "Person: id: " + 3 + ", weight: " + 46 + ", destination: " + 9 + " floor.\n");
    }

    @After
    public void clearTestData() {
        checkId.clear();
        checkDestFloor.clear();
        checkFloor.clear();
        checkWeight.clear();
        checkLiftNumber.clear();
        toString.clear();
    }


    @Test
    public void testPersonsId(){
        for (Map.Entry<Person, Integer> entry :
                checkId.entrySet()) {
            assertEquals(entry.getValue(), entry.getKey().getId());
        }
    }

    @Test
    public void testPersonsDestFloor(){
        for (Map.Entry<Person, Integer> entry :
                checkDestFloor.entrySet()) {
            assertEquals(entry.getValue(), entry.getKey().getDestinationFloor());
        }
    }

    @Test
    public void testPersonsFloor(){
        for (Map.Entry<Person, Integer> entry :
                checkFloor.entrySet()) {
            assertEquals(entry.getValue(), entry.getKey().getFloorNumber());
        }
    }

    @Test
    public void testPersonsWeight(){
        for (Map.Entry<Person, Integer> entry :
                checkWeight.entrySet()) {
            assertEquals(entry.getValue(), entry.getKey().getWeight());
        }
    }

    @Test
    public void testLift(){
        for (Map.Entry<Person, Integer> entry :
                checkLiftNumber.entrySet()) {
            assertEquals(entry.getValue(), entry.getKey().getWaitsForLiftNumber());
        }
    }

    @Test
    public void checkSetId(){
        for (Map.Entry<Person, Integer> entry :
                checkId.entrySet()) {
            Person person = entry.getKey();
            Integer oldId = entry.getValue();
            person.setId(oldId+1);
            long newId = person.getId();
            assertEquals((oldId+1),newId);
        }
    }

    @Test
    public void checkSetWeight(){
        for (Map.Entry<Person, Integer> entry :
                checkWeight.entrySet()) {
            Person person = entry.getKey();
            Integer oldWeight = entry.getValue();
            person.setWeight(oldWeight+1);
            long newWeight = person.getWeight();
            assertEquals((oldWeight+1),newWeight);
        }
    }

    @Test
    public void checkSetFloor(){
        for (Map.Entry<Person, Integer> entry :
                checkFloor.entrySet()) {
            Person person = entry.getKey();
            Integer oldFloor = entry.getValue();
            person.setFloorNumber(oldFloor+1);
            long newNum = person.getFloorNumber();
            assertEquals((oldFloor+1),newNum);
        }
    }

    @Test
    public void checkSetDestFloor(){
        for (Map.Entry<Person, Integer> entry :
                checkDestFloor.entrySet()) {
            Person person = entry.getKey();
            Integer oldFloor = entry.getValue();
            person.setDestinationFloor(oldFloor+1);
            long newNum = person.getDestinationFloor();
            assertEquals((oldFloor+1),newNum);
        }
    }

    @Test
    public void checkLiftNumber(){
        for (Map.Entry<Person, Integer> entry :
                checkLiftNumber.entrySet()) {
            Person person = entry.getKey();
            Integer oldLift = entry.getValue();
            person.setWaitsForLiftNumber(oldLift+1);
            long newLift = person.getWaitsForLiftNumber();
            assertEquals((oldLift+1),newLift);
        }
    }

    @Test
    public void testToString(){
        for (Map.Entry<Person, String> entry :
                toString.entrySet()) {
            assertEquals(entry.getValue(), entry.getKey().toString());
        }
    }



}
