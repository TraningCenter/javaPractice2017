package com.eugene.сontroller;

import com.eugene.сontroller.Entities.Button;
import com.eugene.сontroller.Entities.House;
import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Entities.Passenger;
import com.eugene.dataHandler.DataHandlerUserInputTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ControllerTest {

    private House house;

    @Before
    public void init() {
        house = DataHandlerUserInputTest.generateHouse();
    }

    @Test
    public void buildStatesTest() {
        Controller controller = new Controller(house);
        controller.buildStates();

        //see that all people reach the floors they wanted
        for (Passenger p : house.getPassengers()) {
            assertEquals(p.getFloor(), p.getFloor());
        }

        //see that all buttons are off
        for (Button b : house.getButtons()) {
            assertFalse(b.getOn());
        }

        //see that lifts remain on the floors of one of the passengers
        for (Lift l : house.getLifts()) {
            boolean flag = false;
            for (Passenger p : house.getPassengers()) {
                if (l.getFloor() == p.getFloor()) {
                    flag = true;
                    break;
                }
            }
            assertTrue(flag);
        }

    }

}