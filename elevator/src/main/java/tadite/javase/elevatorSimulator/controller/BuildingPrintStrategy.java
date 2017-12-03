package tadite.javase.elevatorSimulator.controller;

import tadite.javase.elevatorSimulator.model.building.Building;

/**
 * Printing building state
 */
public interface BuildingPrintStrategy {
    void print(Building building);
}
