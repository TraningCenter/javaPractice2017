package com.netcracker.unc;

import com.netcracker.unc.commands.CommandManager;
import com.netcracker.unc.commands.building.CallElevatorBuildingCommand;
import com.netcracker.unc.commands.elevator.LoadPassengersElevatorCommand;
import com.netcracker.unc.commands.elevator.UnLoadPassengersElevatorCommand;
import com.netcracker.unc.commands.floor.AddNewPassengerFloorCommand;
import com.netcracker.unc.commands.vizualizer.*;
import com.netcracker.unc.logic.Building;
import com.netcracker.unc.logic.Elevator;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.Passenger;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;
import com.netcracker.unc.tools.ObjectsGenerator;
import com.netcracker.unc.tools.UserInteractor;
import com.netcracker.unc.visualization.Visualizer;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class which emulates a multi-storey building with elevators
 */
public class ApplicationManager {

    private int countFloors;
    private static final int MAX_COUNT_OF_FLOORS = 10;
    private static final int MAX_COUNT_OF_ELEVATORS = 6;

    private Building building;
    private CommandManager commandManager;
    private Queue<IPassenger> waitingCalls;
    private Visualizer visualizer;

    public ApplicationManager() {
        building = new Building();
        commandManager = new CommandManager();
        waitingCalls = new LinkedList<>();
        visualizer = new Visualizer();
    }

    void start() {
        try {
            // настройки консоли для отображения UTF-8 символов отрисовки лифта и этажа
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            // очищение экрана cmd
            System.out.print("\033[H\033[2J");

            menu();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ApplicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void menu() {
        boolean exit = false;
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            System.out.println("Выберите конфигурацию: ");
            System.out.println("1. По умолчанию");
            System.out.println("2. Сгенерировать самостоятельно");
            System.out.println("0. Выход");
            try {
                i = UserInteractor.inputInt(scanner, 0, 2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            switch (i) {
                case 1:
                    defaultConfiguration();
                    runSimulation();
                    break;
                case 2:
                    inputConfiguration(scanner);
                    runSimulation();
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }

    private void inputConfiguration(Scanner scanner) {
        building = new Building();
        commandManager = new CommandManager();
        waitingCalls = new LinkedList<>();
        visualizer = new Visualizer();
        countFloors = inputInt(scanner, "Введите количество этажей в доме: ", 2, MAX_COUNT_OF_FLOORS);
        // добавляем этажи в здание
        for (int i = 1; i <= countFloors; i++) {
            building.addFloor(new Floor(i));
        }
        int countElevators = inputInt(scanner, "Введите количество лифтов в доме: ", 1, MAX_COUNT_OF_ELEVATORS);
        // добавляем лифты в здание (данные заполняются вручную или автоматически
        for (int i = 0; i < countElevators; i++) {
            building.addElevator(inputElevatorInfo(scanner, i));
        }
        visualizer.setConfiguration(building);
        // добавляем пассажиров
        String line;
        IPassenger passenger;
        while (true) {
            System.out.print("Добавить пассажира? (Y/N) ");
            line = scanner.nextLine();
            if (line.equalsIgnoreCase("yes") || line.equalsIgnoreCase("y")) {
                passenger = inputPassengerInfo(scanner);
                Floor startFloor = passenger.getStartFloor();
                commandManager.addCommand(new AddNewPassengerFloorCommand(passenger, startFloor));
                commandManager.addCommand(new CallElevatorBuildingCommand(building.getElevators(), startFloor, passenger.getDestinationFloor(), passenger.getDirection(), waitingCalls));
                commandManager.addCommand(new UpdateFloorVisualizerCommand(startFloor, visualizer.getFloorPictureById(startFloor.getId())));
            } else {
                break;
            }
        }
    }

    private void runSimulation() {
        List<IElevator> elevators = building.getElevators();
        while (!commandManager.isEmpty()) {
            while (!commandManager.isEmpty()) {
                commandManager.executeNextCommand();
            }
            visualizer.visualize();
            for (IElevator elevator : elevators) {
                if (elevator.getFloorsToVisit().isEmpty()) {
                    continue;
                }

                if (elevator.isInFloor() && elevator.getCurrentFloor() == elevator.getNextDestinationFloor()) {
                    if (!elevator.isOpened()) {
                        if (!elevator.isUnLoaded() || !elevator.isLoaded()) {
                            commandManager.addCommand(new OpenDoorsVisualizerCommand(elevator, visualizer.getElevatorPictureList().get(elevator.getId())));
                            continue;
                        }
                    } else {
                        if (!elevator.isUnLoaded() && !elevator.getPassengers().isEmpty()) {
                            commandManager.addCommand(new UnLoadPassengersElevatorCommand(elevator));
                            commandManager.addCommand(new LoadPassengersVisualizerCommand(elevator, visualizer.getElevatorPictureList().get(elevator.getId()), visualizer.getFloorPictureById(elevator.getCurrentFloor().getId())));
                            continue;
                        }
                        if (!elevator.isLoaded()) {
                            commandManager.addCommand(new LoadPassengersElevatorCommand(elevator, waitingCalls));
                            commandManager.addCommand(new LoadPassengersVisualizerCommand(elevator, visualizer.getElevatorPictureList().get(elevator.getId()), visualizer.getFloorPictureById(elevator.getCurrentFloor().getId())));
                            commandManager.addCommand(new CloseDoorsVisualizerCommand(elevator, visualizer.getElevatorPictureList().get(elevator.getId())));
                            continue;
                        }
                    }
                }
                commandManager.addCommand(new MoveElevatorVisualizerCommand(elevator, visualizer.getElevatorPictureList().get(elevator.getId()), visualizer.getFloorPictureById(elevator.getCurrentFloor().getId()), building.getFloors()));
            }
            if (!waitingCalls.isEmpty()) {
                List<IPassenger> wc = new ArrayList<>(waitingCalls);
                for (IPassenger waitingCall : wc) {
                    waitingCalls.remove(waitingCall);
                    commandManager.addCommand(new CallElevatorBuildingCommand(elevators, waitingCall.getStartFloor(), waitingCall.getDestinationFloor(), waitingCall.getDirection(), waitingCalls));
                    commandManager.addCommand(new UpdateFloorVisualizerCommand(waitingCall.getStartFloor(), visualizer.getFloorPictureById(waitingCall.getStartFloor().getId())));
                }
            }
        }
    }

    private int inputInt(Scanner scanner, String msg, int min, int max) {
        int i;
        while (true) {
            System.out.print(msg);
            try {
                i = UserInteractor.inputInt(scanner, min, max);
                return i;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private IElevator inputElevatorInfo(Scanner scanner, int id) {
        int minFloor;
        int maxFloor;
        List<Floor> floors = building.getFloors();
        int currentFloor;

        System.out.print(String.format("Лифт #%d. Ввести информацию вручную? (Y/N)", id));
        String line = scanner.nextLine();

        IElevator elevator = new Elevator(id);
        if (line.equalsIgnoreCase("yes") || line.equalsIgnoreCase("y")) {
            minFloor = inputInt(scanner, "Введите минимальный доступный этаж: ", 1, countFloors - 1);
            maxFloor = inputInt(scanner, "Введите максимальный доступный этаж: ", minFloor + 1, countFloors);
            for (int i = minFloor; i <= maxFloor; i++) {
                elevator.addAvailableFloor(floors.get(i - 1));
            }
            while (true) {
                currentFloor = inputInt(scanner, "Введите этаж стоянки лифта: ", minFloor, maxFloor);
                if (elevator.setCurrentFloor(floors.get(currentFloor - 1))) {
                    break;
                } else {
                    System.out.println(String.format("Этаж #%d недоступен для этого лифта!", currentFloor));
                }
            }
            elevator.setCapacity(inputInt(scanner, "Введите грузоподъемность лифта: ", 100, 600));
        } else {
            elevator = ObjectsGenerator.elevatorGeneration(id, floors);
            System.out.println(String.format("INFO: Лифт #%d. Мин. этаж: %d, макс. этаж: %d, тек. этаж: %d, грузопод. : %d", id, elevator.getAvailableFloors().get(0).getId(), elevator.getAvailableFloors().get(elevator.getAvailableFloors().size() - 1).getId(), elevator.getCurrentFloor().getId(), elevator.getCapacity()));
        }
        return elevator;
    }

    private IPassenger inputPassengerInfo(Scanner scanner) {
        List<Floor> floors = building.getFloors();
        int startFloor;
        int destFloor;

        System.out.print("Ввести информацию о пассажире вручную? (Y/N)");
        String line = scanner.nextLine();

        IPassenger passenger = new Passenger();
        if (line.equalsIgnoreCase("yes") || line.equalsIgnoreCase("y")) {
            startFloor = inputInt(scanner, "Введите начальный этаж: ", 1, countFloors);
            while (true) {
                destFloor = inputInt(scanner, "Введите конечный этаж: ", 1, countFloors);
                if (destFloor != startFloor) {
                    break;
                } else {
                    System.out.println("Это начальный этаж. Необходимо выбрать другой этаж!");
                }
            }
            passenger.setStartFloor(floors.get(startFloor - 1));
            passenger.setDestinationFloor(floors.get(destFloor - 1));
            passenger.setWeight(inputInt(scanner, "Введите вес пассажира: ", 20, 150));
            passenger.setProbabilityOfChoice(inputInt(scanner, "Введите вероятность передумать (в процентах): ", 0, 100));
        } else {
            passenger = ObjectsGenerator.passengerGeneration(floors);
            System.out.println(String.format("INFO: Пассажир. ОТ: %d, ДО: %d, вес: %d, вероятн. передумать: %d %%", passenger.getStartFloor().getId(), passenger.getDestinationFloor().getId(), passenger.getWeight(), passenger.getProbabilityOfChoice()));
        }
        return passenger;
    }

    private void defaultConfiguration() {
        building = new Building();
        commandManager = new CommandManager();
        waitingCalls = new LinkedList<>();
        visualizer = new Visualizer();
        ObjectsGenerator.defaultConfiguration(building, commandManager, visualizer, waitingCalls);
    }
}
