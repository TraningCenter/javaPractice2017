package com.netcracker.unc;

import com.netcracker.unc.commands.CommandManager;
import com.netcracker.unc.commands.building.CallElevatorBuildingCommand;
import com.netcracker.unc.commands.elevator.LoadPassengersElevatorCommand;
import com.netcracker.unc.commands.elevator.UnLoadPassengersElevatorCommand;
import com.netcracker.unc.commands.floor.AddNewPassengerFloorCommand;
import com.netcracker.unc.commands.vizualizer.*;
import com.netcracker.unc.logic.*;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;
import com.netcracker.unc.visualization.Visualizer;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class ApplicationManager {
    private static int countFloors;
    private static int countElevators;
    private static final int maxCountOfFloors = 10;
    private static final int maxCountOfElevators = 6;

    private static Building building;
    private static CommandManager commandManager;
    private static Queue<WaitingCall> waitingCalls;
    private static Visualizer visualizer;


    public static void main(String[] args) {
        try {
            // настройки консоли для отображения UTF-8 символов отрисовки лифта и этажа
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            // очищение экрана cmd
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        building = new Building();
        commandManager = new CommandManager();
        waitingCalls = new LinkedList<>();
        visualizer = new Visualizer();

        inputConfiguration();
        visualizer.visualize();
        List<IElevator> elevators = building.getElevators();
        while (!commandManager.isEmpty()) {
            while (!commandManager.isEmpty()) {
                commandManager.executeNextCommand();
            }
            visualizer.visualize();
            for (IElevator elevator : elevators) {
                if (elevator.getFloorsToVisit().isEmpty())
                    continue;

                if (elevator.isInFloor() && elevator.getCurrentFloor() == elevator.getNextDestinationFloor()) {
                    if (!elevator.isOpened()) {
                        if (!elevator.isUnLoaded() || !elevator.isLoaded()) {
                            commandManager.addCommand(new OpenDoorsVisualizerCommand(elevator, visualizer.getElevatorPictureList().get(elevator.getId())));
                            continue;
                        }
                    } else {
                        if (!elevator.isUnLoaded() && !elevator.getPassengers().isEmpty()) {
                            commandManager.addCommand(new UnLoadPassengersElevatorCommand(elevator));
                            commandManager.addCommand(new LoadPassengersVisualizerCommand(elevator, visualizer.getElevatorPictureList().get(elevator.getId()), elevator.getCurrentFloor(), visualizer.getFloorPictureById(elevator.getCurrentFloor().getId())));
                            continue;
                        }
                        if (!elevator.isLoaded()) {
                            commandManager.addCommand(new LoadPassengersElevatorCommand(elevator, waitingCalls));
                            commandManager.addCommand(new LoadPassengersVisualizerCommand(elevator, visualizer.getElevatorPictureList().get(elevator.getId()), elevator.getCurrentFloor(), visualizer.getFloorPictureById(elevator.getCurrentFloor().getId())));
                            commandManager.addCommand(new CloseDoorsVisualizerCommand(elevator, visualizer.getElevatorPictureList().get(elevator.getId())));
                            continue;
                        }
                    }
                }
                commandManager.addCommand(new MoveElevatorVisualizerCommand(elevator, visualizer.getElevatorPictureList().get(elevator.getId()), visualizer.getFloorPictureById(elevator.getCurrentFloor().getId()), building.getFloors()));
            }
            if (!waitingCalls.isEmpty()) {
                List<WaitingCall> wc = new ArrayList<>(waitingCalls);
                for (WaitingCall waitingCall : wc) {
                    waitingCalls.remove(waitingCall);
                    commandManager.addCommand(new CallElevatorBuildingCommand(elevators, waitingCall.getStartFloor(), waitingCall.getDestFloor(), waitingCall.getDirection(), waitingCalls));
                    commandManager.addCommand(new UpdateFloorVisualizerCommand(waitingCall.getStartFloor(), visualizer.getFloorPictureById(waitingCall.getStartFloor().getId())));
                }
            }
        }
        System.out.println();
    }

    private static void inputConfiguration() {
        Scanner scanner = new Scanner(System.in);
        countFloors = inputInt(scanner, "Введите количество этажей в доме: ", 2, maxCountOfFloors);
        // добавляем этажи в здание
        for (int i = 1; i <= countFloors; i++)
            building.addFloor(new Floor(i));
        countElevators = inputInt(scanner, "Введите количество лифтов в доме: ", 1, maxCountOfElevators);
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
            } else
                break;
        }
    }

    private static int inputInt(Scanner scanner, String msg, int min, int max) {
        int i;
        while (true) {
            System.out.print(msg);
            String line = scanner.nextLine();
            try {
                i = Integer.parseInt(line);
                if (i >= min && i <= max)
                    break;
                else
                    System.out.println(String.format("Введите число в диапазоне (%d;%d)", min, max));

            } catch (NumberFormatException nfe) {
                System.out.println("Ошибка! Введите число! ");
            }
        }
        return i;
    }

    private static IElevator inputElevatorInfo(Scanner scanner, int id) {
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
            for (int i = minFloor; i <= maxFloor; i++)
                elevator.addAvailableFloor(floors.get(i - 1));
            while (true) {
                currentFloor = inputInt(scanner, "Введите этаж стоянки лифта: ", minFloor, maxFloor);
                if (elevator.setCurrentFloor(floors.get(currentFloor - 1)))
                    break;
                else
                    System.out.println(String.format("Этаж #%d недоступен для этого лифта!", currentFloor));
            }
            elevator.setCapacity(inputInt(scanner, "Введите грузоподъемность лифта: ", 100, 600));
        } else {
            Random rnd = new Random(System.currentTimeMillis());
            minFloor = 1 + rnd.nextInt(countFloors - 1);
            maxFloor = minFloor + 1 + rnd.nextInt(countFloors - minFloor);
            for (int i = minFloor; i <= maxFloor; i++)
                elevator.addAvailableFloor(floors.get(i - 1));
            currentFloor = minFloor + rnd.nextInt(maxFloor - minFloor + 1);
            elevator.setCurrentFloor(floors.get(currentFloor - 1));
            elevator.setCapacity(100 + rnd.nextInt(501));
            System.out.println(String.format("INFO: Лифт #%d. Мин. этаж: %d, макс. этаж: %d, тек. этаж: %d, грузопод. : %d", id, minFloor, maxFloor, currentFloor, elevator.getCapacity()));
        }
        return elevator;
    }

    private static IPassenger inputPassengerInfo(Scanner scanner) {
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
                if (destFloor != startFloor)
                    break;
                else
                    System.out.println("Это начальный этаж. Необходимо выбрать другой этаж!");
            }
            passenger.setStartFloor(floors.get(startFloor - 1));
            passenger.setDestinationFloor(floors.get(destFloor - 1));
            passenger.setWeight(inputInt(scanner, "Введите вес пассажира: ", 20, 150));
            passenger.setProbabilityOfChoice(inputInt(scanner, "Введите вероятность передумать (в процентах): ", 0, 100));
        } else {
            Random rnd = new Random(System.currentTimeMillis());
            startFloor = 1 + rnd.nextInt(countFloors);
            while (true) {
                destFloor = 1 + rnd.nextInt(countFloors);
                if (destFloor != startFloor)
                    break;
            }
            passenger.setStartFloor(floors.get(startFloor - 1));
            passenger.setDestinationFloor(floors.get(destFloor - 1));
            passenger.setWeight(20 + rnd.nextInt(131));
            passenger.setProbabilityOfChoice(rnd.nextInt(41));
            System.out.println(String.format("INFO: Пассажир. ОТ: %d, ДО: %d, вес: %d, вероятн. передумать: %d %%", startFloor, destFloor, passenger.getWeight(), passenger.getProbabilityOfChoice()));
        }
        return passenger;
    }
}
