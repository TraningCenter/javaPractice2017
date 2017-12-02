package tadite.javase.elevatorSimulator.controller;

import tadite.javase.elevatorSimulator.model.building.Building;
import tadite.javase.elevatorSimulator.model.building.DefaultBuilding;
import tadite.javase.elevatorSimulator.model.building.DefaultFloorGetter;
import tadite.javase.elevatorSimulator.model.elevator.*;
import tadite.javase.elevatorSimulator.model.floor.DefaultFloor;
import tadite.javase.elevatorSimulator.model.floor.EmptySlot;
import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.floor.Slot;
import tadite.javase.elevatorSimulator.model.misc.Button;
import tadite.javase.elevatorSimulator.model.misc.DefaultButton;
import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.passenger.*;

import java.util.*;

public class DefaultBuildingConfigurator implements BuildingConfigurator {
    private List<ElevatorCreateConfig> elevatorCreateConfigs = new LinkedList<>();
    private List<PersonCreateConfig> personCreateConfigs = new LinkedList<>();
    private int floorCount;
    private int slotCount;

    @Override
    public void setFloorCount(int floorCount) {
        this.floorCount=floorCount;
    }

    @Override
    public void setSlotCount(int slotCount) {
        this.slotCount = slotCount;
    }

    @Override
    public void addElevatorCreateConfig(ElevatorCreateConfig elevatorConfig) {
        elevatorCreateConfigs.add(elevatorConfig);
    }

    @Override
    public void addPersonCreateConfig(PersonCreateConfig personConfig) {
        personCreateConfigs.add(personConfig);
    }

    @Override
    public Iterator<ElevatorCreateConfig> getElevatorCreateConfigs() {
        return elevatorCreateConfigs.iterator();
    }

    @Override
    public Iterator<PersonCreateConfig> getPersonCreateConfigs() {
        return personCreateConfigs.iterator();
    }

    @Override
    public void removeElevatorCreateConfig(int index) {
        if (index<elevatorCreateConfigs.size())
            elevatorCreateConfigs.remove(index);
    }

    @Override
    public void removePersonCreateConfig(int index) {
        if (index<personCreateConfigs.size())
            personCreateConfigs.remove(index);
    }

    @Override
    public Building createBuilding() {
        List<Floor> floors = new ArrayList<>();
        List<Passenger> passengers = new ArrayList<>();
        List<IndoorTransport> indoorTransports = new ArrayList<>();

        List<List<Slot>> slotsLists = new ArrayList<>();
        for (int level = 1; level < floorCount+1; level++) {
            List<Slot> slots = new ArrayList<>();
            for (int position = 0; position < slotCount; position++) {
                slots.add(new EmptySlot(new Location(position, level)));
            }
            slotsLists.add(slots);
        }

        for (ElevatorCreateConfig elevatorCreateConfig : elevatorCreateConfigs) {
            ElevatorConfig config = new ElevatorConfig(elevatorCreateConfig.getMinLevel(),elevatorCreateConfig.getMaxLevel(),elevatorCreateConfig.getPosition());

            DefaultRequestManager requestManager = new DefaultRequestManager();
            Button button = new DefaultButton(requestManager);
            DefaultElevator defaultElevator = new DefaultElevator(new Location(elevatorCreateConfig.getPosition(),elevatorCreateConfig.getElevatorStartLevel()), button);

            ElevatorTargetCalculationStrategy targetCalculationStrategy = new LastJobFirstElevatorTargetCalculationStrategy();

            Map<Integer, ElevatorDoorMechanism> doorMechanismMap = new HashMap<>();
            for (int level = 1; level < floorCount + 1; level++) {
                DefaultElevatorDoor defaultElevatorDoor = new DefaultElevatorDoor(
                        new Location(elevatorCreateConfig.getPosition(),level), config, new DefaultButton(requestManager),defaultElevator);
                slotsLists.get(level-1).set(elevatorCreateConfig.getPosition(),defaultElevatorDoor);
                doorMechanismMap.put(level,defaultElevatorDoor);
            }

            indoorTransports.add(new ElevatorController(config,defaultElevator,targetCalculationStrategy,doorMechanismMap,requestManager));
        }

        for (int level = 1; level < floorCount+1; level++) {
            floors.add(new DefaultFloor(level,slotsLists.get(level-1)));
        }

        FloorGetter floorGetter = new DefaultFloorGetter(floors);

        for (PersonCreateConfig personCreateConfig : personCreateConfigs) {
            Person person = new Person(new Location(personCreateConfig.getStartPosition(), personCreateConfig.getStartLevel()),
                    floors.get(personCreateConfig.getStartLevel() - 1),
                    new Location(personCreateConfig.getTargetPosition(), personCreateConfig.getTargetLevel()),
                    floorGetter);
            person.changeState(new GoToElevatorDoorPersonState(person));
            passengers.add(person);
        }

        return new DefaultBuilding(floors,passengers,indoorTransports);
    }
}
