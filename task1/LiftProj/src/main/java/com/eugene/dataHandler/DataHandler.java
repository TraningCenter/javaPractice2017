package com.eugene.dataHandler;

import com.eugene.сontroller.Entities.House;

import java.util.List;

/**
 * Class for processing initial data
 */
public abstract class DataHandler {

    public abstract List<Integer> getInput();

    public boolean verifyInput(int value, int min, int max) {
        return (value >= min && value <= max);
    }

    public abstract House convertDataToHouse(List<Integer> data);
}
