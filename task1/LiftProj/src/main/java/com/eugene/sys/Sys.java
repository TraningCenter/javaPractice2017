package com.eugene.sys;

import com.eugene.сontroller.Entities.House;
import com.eugene.dataHandler.*;
import com.eugene.сontroller.Controller;
import com.eugene.visualizer.*;

/**
 * Class that stores and binds all logical application units
 */
public class Sys {

    private DataHandler dataHandler;

    public Sys() {
        dataHandler = new DataHandlerUserInput(System.in);
    }

    public void work() {
        House house = dataHandler.convertDataToHouse(dataHandler.getInput());
        Controller controller = new Controller(house);
        controller.buildStates();
        Visualizer visualizer = new DefaultVisualizer(controller.getHouseStates());
        visualizer.paint();
    }
}
