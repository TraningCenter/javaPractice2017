package tadite.javase.elevatorSimulator.controller;

import tadite.javase.elevatorSimulator.model.building.Building;

public class BuildingSimulationRunner {
    private Building building;
    private BuildingPrintStrategy printStrategy;

    public BuildingSimulationRunner(Building building, BuildingPrintStrategy printStrategy) {
        this.building = building;
        this.printStrategy = printStrategy;
    }

    public void startSimulation() {
        while (building.isActive()) {
            updateBuilding();
            printStrategy.print(building);
        }
    }

    private void updateBuilding(){
        building.getTransportIterator().forEachRemaining(transport -> transport.updateState());
        building.getPassengerIterator().forEachRemaining(passenger ->  passenger.updateState());
    }


}
