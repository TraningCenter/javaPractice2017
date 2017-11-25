package com.alegerd.tests;

import com.alegerd.model.Floor;
import com.alegerd.model.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloorTest extends Assert {
    private Floor floor = new Floor();

    @Before
    public void setUpNewPersons(){
    }

    @After
    public void clearTestData() {

    }

    /*
    @Test(expected = IllegalArgumentException.class)
    public void testAddArrivedPersonException(){
        floor.addWaitingPerson(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddArrivedPersonException2(){
        Person p = new Person();
        floor.addWaitingPerson(p);
        floor.addWaitingPerson(p);
    }
    */

}
