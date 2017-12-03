package tadite.javase.elevatorSimulator.controller;

import tadite.javase.elevatorSimulator.model.building.Building;

import java.util.Iterator;

public interface BuildingConfigurator {
    void setFloorCount(int floorCount);
    void setSlotCount(int slotCount);
    void addElevatorCreateConfig(ElevatorCreateConfig elevatorConfig);
    void addPersonCreateConfig(PersonCreateConfig personConfig);
    Iterator<ElevatorCreateConfig> getElevatorCreateConfigs();
    Iterator<PersonCreateConfig> getPersonCreateConfigs();
    int getFloorCount();
    int getSlotCount();
    void removeElevatorCreateConfig(int index);
    void removePersonCreateConfig(int index);
    void clear();
    Building createBuilding();
}
