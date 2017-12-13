package com.eugene.visualizer;

import com.eugene.сontroller.Controller;
import com.eugene.сontroller.Entities.House;
import com.eugene.dataHandler.DataHandlerUserInputTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DefaultVisualizerTest {

    private Visualizer visualizer;

    @Before
    public void init() {
        House house = DataHandlerUserInputTest.generateHouse();
        Controller controller = new Controller(new House(house));
        controller.buildStates();
        visualizer = new DefaultVisualizer(controller.getHouseStates());
    }

    @Test
    public void paint(){
        /*//if used System.out.print() instead console.printf(), this test will cover 100% of DefaultVisualizer class
        try {
            visualizer.paint();
            assertTrue(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}