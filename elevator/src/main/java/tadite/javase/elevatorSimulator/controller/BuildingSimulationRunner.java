package tadite.javase.elevatorSimulator.controller;

import tadite.javase.elevatorSimulator.model.building.Building;

/**
 * Building simulation starter
 */
public class BuildingSimulationRunner implements SimulationRunner {
    private Building building;
    private BuildingPrintStrategy printStrategy;
    private int sleepMillis;

    public BuildingSimulationRunner(Building building, BuildingPrintStrategy printStrategy, int sleepMillis) {
        this.building = building;
        this.printStrategy = printStrategy;
        this.sleepMillis = sleepMillis;
    }

    public void startSimulation() {
        clearConsole();
        do {
            printBuilding();
            updateBuilding();
            threadSleep();
        }
        while (building.isActive());
        printBuilding();
        threadSleep();
    }

    private void printBuilding() {
        clearConsole();
        printStrategy.print(building);
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
    }

    private void threadSleep(){
        try {
            Thread.sleep(sleepMillis);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void updateBuilding(){
        building.getTransportIterator().forEachRemaining(transport -> transport.updateState());
        building.getPassengerIterator().forEachRemaining(passenger ->  passenger.updateState());
    }


}
