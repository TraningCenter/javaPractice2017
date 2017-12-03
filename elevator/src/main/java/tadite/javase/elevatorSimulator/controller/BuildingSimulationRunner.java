package tadite.javase.elevatorSimulator.controller;

import tadite.javase.elevatorSimulator.model.building.Building;

public class BuildingSimulationRunner implements SimulationRunner {
    private Building building;
    private BuildingPrintStrategy printStrategy;

    public BuildingSimulationRunner(Building building, BuildingPrintStrategy printStrategy) {
        this.building = building;
        this.printStrategy = printStrategy;
    }

    public void startSimulation() {
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
        System.out.print("\033[H\033[2J");
        printStrategy.print(building);
    }

    private void threadSleep(){
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void updateBuilding(){
        building.getTransportIterator().forEachRemaining(transport -> transport.updateState());
        building.getPassengerIterator().forEachRemaining(passenger ->  passenger.updateState());
    }


}
