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

    private House staticHouse;
    private House smartHouse1;
    private House smartHouse2;
    private House smartHouse3;
    private House smartHouse4;

    @Before
    public void init() {
        staticHouse = DataHandlerUserInputTest.staticGenerateHouse();
        smartHouse1 = DataHandlerUserInputTest.smartGenerateHouse(
                DataHandlerUserInputTest.stringToListInt("10 3 1 1 200 1 10 200 1 5 200 5 1 5 1 5 4 1 10 9 8 7"));
        smartHouse1 = DataHandlerUserInputTest.smartGenerateHouse(
                DataHandlerUserInputTest.stringToListInt("10 3 1 1 200 1 10 200 1 5 200 5 1 5 1 5 4 1 10 9 8 7"));
        smartHouse2 = DataHandlerUserInputTest.smartGenerateHouse(
                DataHandlerUserInputTest.stringToListInt("10 3 1 1 200 1 10 200 1 5 200 5 1 5 1 7 4 1 10 7 8 7"));
        smartHouse3 = DataHandlerUserInputTest.smartGenerateHouse(
                DataHandlerUserInputTest.stringToListInt("10 3 1 1 200 1 10 200 1 5 200 5 1 5 4 1 10 7 8 7 1 7"));
        smartHouse4 = DataHandlerUserInputTest.smartGenerateHouse(
                DataHandlerUserInputTest.stringToListInt("10 3 1 1 200 1 10 200 1 5 200 5 1 5 1 7 4 1 10 7 8 10"));
    }

    @Test
    public void buildStatesTest() {
Controller staticHouseController = new Controller(staticHouse);
        staticHouseController.buildStates();
        testRealization(staticHouse);

        Controller smartHouseController1 = new Controller(smartHouse1);
        smartHouseController1.buildStates();
        testRealization(smartHouse1);

        Controller smartHouseController2 = new Controller(smartHouse2);
        smartHouseController2.buildStates();
        testRealization(smartHouse2);

        Controller smartHouseController3 = new Controller(smartHouse3);
        smartHouseController3.buildStates();
        testRealization(smartHouse3);

        Controller smartHouseController4 = new Controller(smartHouse4);
        smartHouseController4.buildStates();
        testRealization(smartHouse4);
    }

    private void testRealization(House house) {

        //see that all people reach the floors they wanted
        for (Passenger p : staticHouse.getPassengers()) {
            assertEquals(p.getEndFloor(), p.getFloor());
        }

        //see that all buttons are off
        for (Button b : staticHouse.getButtons()) {
            assertFalse(b.getOn());
        }

        //see that lifts remain on the floors of one of the passengers
        for (Lift l : staticHouse.getLifts()) {
            boolean flag = false;
            for (Passenger p : staticHouse.getPassengers()) {
                if (l.getFloor() == p.getFloor()) {
                    flag = true;
                    break;
                }
            }
            assertTrue(flag);
        }

    }

}
