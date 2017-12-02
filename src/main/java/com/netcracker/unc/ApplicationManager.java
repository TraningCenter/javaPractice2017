package com.netcracker.unc;

import com.netcracker.unc.logic.Elevator;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.State;

import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationManager {
    private static int countFloors;
    private static int countElevators;
    private static final int maxCountOfFloors = 10;
    private static final int maxCountOfElevators = 6;

    public static void main(String[] args) {
        //inputConfiguration();
        Elevator elevator = new Elevator(1, new Floor(4), new ArrayList<Floor>(), 10);
        elevator.setState(State.UP);
        elevator.addFloorInQueue(new Floor(1));
        elevator.addFloorInQueue(new Floor(6));
        elevator.addFloorInQueue(new Floor(7));
        elevator.addFloorInQueue(new Floor(3));
        elevator.addFloorInQueue(new Floor(8));
        elevator.addFloorInQueue(new Floor(5));
        System.out.println(elevator.getFloorsToVisit().toString());
        Elevator elevator2 = new Elevator(2, new Floor(5), new ArrayList<Floor>(), 10);
        elevator2.setState(State.DOWN);
        elevator2.addFloorInQueue(new Floor(4));
        elevator2.addFloorInQueue(new Floor(8));
        elevator2.addFloorInQueue(new Floor(2));
        elevator2.addFloorInQueue(new Floor(7));

        Elevator elevator3 = new Elevator(3, new Floor(5), new ArrayList<Floor>(), 10);
        elevator3.setState(State.STOPPED);
        elevator3.addFloorInQueue(new Floor(7));
        elevator3.addFloorInQueue(new Floor(1));
        elevator3.addFloorInQueue(new Floor(6));
        elevator3.addFloorInQueue(new Floor(8));
        System.out.println(elevator.getFloorsToVisit().toString());
        elevator3.deleteFloorFromQueue();
        //System.out.println(countFloors);
        //System.out.println(countElevators);
    }

    private static void inputConfiguration() {
        countFloors = tryInputInt("Введите количество этажей в доме: ", 2, maxCountOfFloors);
        countElevators = tryInputInt("Введите количество лифтов в доме: ", 1, maxCountOfElevators);
    }

    private static int tryInputInt(String msg, int min, int max) {
        Scanner in = new Scanner(System.in);
        int i;
        while (true) {
            System.out.print(msg);
            String line = in.nextLine();
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

}
