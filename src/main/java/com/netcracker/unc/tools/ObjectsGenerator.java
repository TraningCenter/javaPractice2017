package com.netcracker.unc.tools;


import com.netcracker.unc.commands.CommandManager;
import com.netcracker.unc.commands.building.CallElevatorBuildingCommand;
import com.netcracker.unc.commands.floor.AddNewPassengerFloorCommand;
import com.netcracker.unc.commands.vizualizer.UpdateFloorVisualizerCommand;
import com.netcracker.unc.logic.Building;
import com.netcracker.unc.logic.Elevator;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.Passenger;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;
import com.netcracker.unc.visualization.Visualizer;

import java.util.*;

public class ObjectsGenerator {

    public static IElevator elevatorGeneration(int id, List<Floor> floors) {
        Random rnd = new Random(System.currentTimeMillis());
        IElevator elevator = new Elevator(id);
        int countFloors = floors.size();
        int minFloor = 1 + rnd.nextInt(countFloors - 1);
        int maxFloor = minFloor + 1 + rnd.nextInt(countFloors - minFloor);
        for (int i = minFloor; i <= maxFloor; i++) {
            elevator.addAvailableFloor(floors.get(i - 1));
        }
        int currentFloor = minFloor + rnd.nextInt(maxFloor - minFloor + 1);
        elevator.setCurrentFloor(floors.get(currentFloor - 1));
        elevator.setCapacity(100 + rnd.nextInt(501));
        return elevator;
    }

    public static IPassenger passengerGeneration(List<Floor> floors) {
        Random rnd = new Random(System.currentTimeMillis());
        IPassenger passenger = new Passenger();
        int countFloors = floors.size();
        int startFloor = 1 + rnd.nextInt(countFloors);
        int destFloor;
        while (true) {
            destFloor = 1 + rnd.nextInt(countFloors);
            if (destFloor != startFloor) {
                break;
            }
        }
        passenger.setStartFloor(floors.get(startFloor - 1));
        passenger.setDestinationFloor(floors.get(destFloor - 1));
        passenger.setWeight(20 + rnd.nextInt(131));
        passenger.setProbabilityOfChoice(rnd.nextInt(41));
        return passenger;
    }

    public static void defaultConfiguration(Building building, CommandManager commandManager, Visualizer visualizer, Queue<IPassenger> waitingCalls) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            floors.add(new Floor(i));
        }
        building.setFloors(floors);
        List<IElevator> elevators = new ArrayList<>();
        elevators.add(new Elevator(0, floors.get(0), floors, 500));
        elevators.add(new Elevator(1, floors.get(1), floors, 500));
        elevators.add(new Elevator(2, floors.get(4), floors, 500));
        building.setElevators(elevators);
        visualizer.setConfiguration(building);

        addPassenger(building, commandManager, visualizer, waitingCalls, new Passenger(floors.get(0), floors.get(2), 49, 0));
        addPassenger(building, commandManager, visualizer, waitingCalls, new Passenger(floors.get(0), floors.get(3), 56, 0));
        addPassenger(building, commandManager, visualizer, waitingCalls, new Passenger(floors.get(4), floors.get(1), 68, 0));
        addPassenger(building, commandManager, visualizer, waitingCalls, new Passenger(floors.get(3), floors.get(1), 68, 0));
        addPassenger(building, commandManager, visualizer, waitingCalls, new Passenger(floors.get(2), floors.get(0), 100, 0));
        addPassenger(building, commandManager, visualizer, waitingCalls, new Passenger(floors.get(4), floors.get(0), 100, 0));
    }


    private static void addPassenger(Building building, CommandManager commandManager, Visualizer visualizer, Queue<IPassenger> waitingCalls, IPassenger passenger) {
        commandManager.addCommand(new AddNewPassengerFloorCommand(passenger, passenger.getStartFloor()));
        commandManager.addCommand(new CallElevatorBuildingCommand(building.getElevators(), passenger.getStartFloor(), passenger.getDestinationFloor(), passenger.getDirection(), waitingCalls));
        commandManager.addCommand(new UpdateFloorVisualizerCommand(passenger.getStartFloor(), visualizer.getFloorPictureById(passenger.getStartFloor().getId())));
    }
}
