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
    public int getFloorCount() {
        return floorCount;
    }

    @Override
    public int getSlotCount() {
        return slotCount;
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

        List<List<Slot>> slotsLists = createEmptySlotsForAllBuilding();

        for (ElevatorCreateConfig elevatorCreateConfig : elevatorCreateConfigs) {
            ElevatorController elevatorController = createElevatorController(slotsLists, elevatorCreateConfig);

            indoorTransports.add(elevatorController);
        }

        for (int level = 1; level < floorCount+1; level++) {
            floors.add(new DefaultFloor(level,slotsLists.get(level-1)));
        }

        FloorGetter floorGetter = new DefaultFloorGetter(floors);

        for (PersonCreateConfig personCreateConfig : personCreateConfigs) {
            Person person = createPerson(floors, floorGetter, personCreateConfig);
            passengers.add(person);
        }

        return new DefaultBuilding(floors,passengers,indoorTransports);
    }

    private Person createPerson(List<Floor> floors, FloorGetter floorGetter, PersonCreateConfig personCreateConfig) {
        Person person = new Person(new Location(personCreateConfig.getStartPosition(), personCreateConfig.getStartLevel()),
                floors.get(personCreateConfig.getStartLevel() - 1),
                new Location(personCreateConfig.getTargetPosition(), personCreateConfig.getTargetLevel()),
                floorGetter);
        person.changeState(new GoToElevatorDoorPersonState(person));
        return person;
    }

    private ElevatorController createElevatorController(List<List<Slot>> slotsLists, ElevatorCreateConfig elevatorCreateConfig) {
        ElevatorConfig config = createElevatorConfig(elevatorCreateConfig);
        DefaultRequestManager requestManager = new DefaultRequestManager();
        DefaultElevator defaultElevator = createElevator(elevatorCreateConfig, requestManager);
        ElevatorTargetCalculationStrategy targetCalculationStrategy = new ShortestJobFirstElevatorTargetCalculationStrategy();
        Map<Integer, ElevatorDoorMechanism> doorMechanismMap = createIntegerElevatorDoorMechanismMap(slotsLists, config, requestManager, defaultElevator);

        return new ElevatorController(config, defaultElevator, targetCalculationStrategy, doorMechanismMap, requestManager);
    }

    private Map<Integer, ElevatorDoorMechanism> createIntegerElevatorDoorMechanismMap(List<List<Slot>> slotsLists, ElevatorConfig config, DefaultRequestManager requestManager, DefaultElevator defaultElevator) {
        Map<Integer, ElevatorDoorMechanism> doorMechanismMap = new HashMap<>();
        for (int level = 1; level < floorCount + 1; level++) {
            int elevatorPosition = config.getPosition();

            DefaultElevatorDoor defaultElevatorDoor = new DefaultElevatorDoor(
                    new Location(elevatorPosition,level), config, new DefaultButton(requestManager),defaultElevator);

            slotsLists.get(level-1).set(elevatorPosition,defaultElevatorDoor);

            doorMechanismMap.put(level,defaultElevatorDoor);
        }
        return doorMechanismMap;
    }

    private DefaultElevator createElevator(ElevatorCreateConfig elevatorCreateConfig, DefaultRequestManager requestManager) {
        Button button = new DefaultButton(requestManager);
        return new DefaultElevator(new Location(elevatorCreateConfig.getPosition(),elevatorCreateConfig.getElevatorStartLevel()), button);
    }

    private ElevatorConfig createElevatorConfig(ElevatorCreateConfig elevatorCreateConfig) {
        return new ElevatorConfig(elevatorCreateConfig.getMinLevel(),elevatorCreateConfig.getMaxLevel(),elevatorCreateConfig.getPosition());
    }

    private List<List<Slot>> createEmptySlotsForAllBuilding() {
        List<List<Slot>> slotsLists = new ArrayList<>();
        for (int level = 1; level < floorCount+1; level++) {
            slotsLists.add(createEmptySlots(level));
        }
        return slotsLists;
    }

    private List<Slot> createEmptySlots(int level) {
        List<Slot> slots = new ArrayList<>();
        for (int position = 0; position < slotCount; position++) {
            slots.add(new EmptySlot(new Location(position, level)));
        }
        return slots;
    }
}
