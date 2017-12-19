package com.eugene.сontroller;

import com.eugene.сontroller.Entities.House;
import com.eugene.dataHandler.DataHandlerUserInputTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SnapshotTest {

    private House house;
    private Controller controller;

    @Before
    public void init() {
        house = DataHandlerUserInputTest.staticGenerateHouse();
        controller = new Controller(new House(house));
        controller.buildStates();
    }

    @Test
    public void getHouse() throws Exception {
        assertEquals(controller.getHouseStates().get(0).getHouse(), house);
    }

}
