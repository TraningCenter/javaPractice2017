package com.eugene.dataHandler;

import com.eugene.сontroller.Entities.Button;
import com.eugene.сontroller.Entities.House;
import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Entities.Passenger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class for processing user input data
 */
public class DataHandlerUserInput extends DataHandler {

    private final int MIN_FLOORS_NUM = 2;
    private final int MAX_FLOORS_NUM = 10;
    private final int MIN_LIFTS_NUM = 1;
    private final int MAX_LIFTS_NUM = 3;
    private final int MIN_PERSONS_NUM = 1;
    private final int MAX_PERSONS_NUM = 5;
    private final int MIN_LIFT_CAPACITY = 2;
    private final int MAX_LIFT_CAPACITY = 5;
    private Scanner reader;

    public DataHandlerUserInput() {
        reader = new Scanner(System.in);
    }

    public DataHandlerUserInput(InputStream in) {
        this.reader = new Scanner(in);
    }

    @Override
    public List<Integer> getInput() {
        System.out.println("Select one of the options:");
        System.out.println("1. Two passengers are served by 2 lifts:");
        System.out.println("2. Lift rides up, takes the passenger and drops it down:");
        System.out.println("3. Simple case with 3 passengers and 1 lift (max num of lift passengers-2):");
        System.out.println("4. Case with 1 lift and 5 passengers:");
        System.out.println("5. First simple case with 3 lift and 5 passengers:");
        System.out.println("6. Second simple case with 3 lift and 5 passengers (2 passengers on 10 floor, 3 passengers on 1 floor):");
        System.out.println("7. Your input:");
        int option = inputWithCheck("", 1, 7);

        List<Integer> result = new ArrayList<>();
        switch (option) {
            case 1:
                //Two passengers are served by 2 lifts:
                result.addAll(Arrays.asList(10, 2, 1, 1, 3, 1, 10, 3, 2, 1, 5, 8, 7));
                break;
            case 2:
                //Lift rides up, takes the passenger and drops it down
                result.addAll(Arrays.asList(10, 1, 1, 1, 3, 1, 3, 1));
                break;
            case 3:
                //Simple case with 3 passengers and 1 lift (max num of lift passengers-2)
                result.addAll(Arrays.asList(10, 1, 1, 1, 2, 3, 3, 9, 5, 8, 6, 8));
                break;
            case 4:
                //Case with 1 lift and 5 passengers
                result.addAll(Arrays.asList(10, 1, 1, 5, 2, 5, 1, 10, 2, 1, 3, 10, 9, 7, 6, 8));
                break;
            case 5:
                //First simple case with 3 lift and 5 passengers
                result.addAll(Arrays.asList(10, 3, 1, 1, 2, 1, 9, 2, 1, 6, 2, 5, 1, 10, 2, 1, 3, 10, 9, 7, 6, 8));
                break;
            case 6:
                //Second simple case with 3 lift and 5 passengers (2 passengers on 10 floor, 3 passengers on 1 floor)
                result.addAll(Arrays.asList(10, 3, 1, 1, 2, 1, 9, 2, 1, 4, 2, 5, 10, 7, 1, 5, 10, 5, 1, 5, 1, 5));
                break;
            case 7:
            default:
                result.addAll(userInput());
                break;
        }
        return result;
    }

    private List<Integer> userInput() {
        List<Integer> result = new ArrayList<>();
        String message = String.format("Enter number of floors (%d..%d)", MIN_FLOORS_NUM, MAX_FLOORS_NUM);
        int numFloors = inputWithCheck(message, MIN_FLOORS_NUM, MAX_FLOORS_NUM);
        result.add(numFloors);
        message = String.format("Enter number of lifts (%d..%d)", MIN_LIFTS_NUM, MAX_LIFTS_NUM);
        int numLifts = inputWithCheck(message, MIN_LIFTS_NUM, MAX_LIFTS_NUM);
        result.add(numLifts);
        result.addAll(inputLiftsInfo(numFloors, numLifts));
        message = String.format("Enter number of persons (%d..%d)", MIN_PERSONS_NUM, MAX_PERSONS_NUM);
        int numPersons = inputWithCheck(message, MIN_PERSONS_NUM, MAX_PERSONS_NUM);
        result.add(numPersons);
        result.addAll(inputPersonsInfo(numFloors, numPersons));//
        return result;
    }

    private List<Integer> inputLiftsInfo(int numFloors, int numLifts) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < numLifts; i++) {
            System.out.printf("--- Lift #%d info:\n", i + 1);
            result.add(inputWithCheck("Lift works(1) or broken(0)", 0, 1));
            String message = String.format("Enter lift start floor (%d..%d)", 1, numFloors);
            result.add(inputWithCheck(message, 1, numFloors));
            message = String.format("Enter maximum number of people for this lift (%d..%d)", MIN_LIFT_CAPACITY, MAX_LIFT_CAPACITY);
            result.add(inputWithCheck(message, MIN_LIFT_CAPACITY, MAX_LIFT_CAPACITY));
        }
        return result;
    }

    private List<Integer> inputPersonsInfo(int numFloors, int numPersons) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < numPersons; i++) {
            System.out.printf("--- Person #%d info:\n", i + 1);
            String message = String.format("Enter person start floor (1..%d)", numFloors);
            int startFloor = inputWithCheck(message, 1, numFloors);
            result.add(startFloor);
            int endFloor;
            do {
                message = String.format("Enter person end floor (1..%d, start!=end)", numFloors);
                endFloor = inputWithCheck(message, 1, numFloors);
            } while (startFloor == endFloor);
            result.add(endFloor);
        }
        return result;
    }

    private int inputWithCheck(String message, int minValue, int maxValue) {
        while (true) {
            System.out.print(message + ": ");
            int tmp = correctInput();
            if (verifyInput(tmp, minValue, maxValue))
                return tmp;
        }
    }

    private int correctInput() {
        while (true) {
            try {
                String s = reader.next();
                return Integer.parseInt(s);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public House convertDataToHouse(List<Integer> data) {
        int numFloors;
        List<Button> buttons = new ArrayList<>();
        List<Lift> lifts = new ArrayList<>();
        List<Passenger> passengers = new ArrayList<>();

        int record = 0;
        numFloors = data.get(record++);
        for (int i = 0; i < numFloors; i++) {
            if (i != 0)
                buttons.add(new Button(i + 1, -1));
            if (i != numFloors - 1)
                buttons.add(new Button(i + 1, 1));
        }
        int numLifts = data.get(record++);
        for (int i = 0; i < numLifts; i++) {
            boolean working = (data.get(record++) == 1);
            int startFloor = data.get(record++);
            int maxWeight = data.get(record++);
            lifts.add(new Lift(working, startFloor, maxWeight));
        }
        int numPersons = data.get(record++);
        for (int i = 0; i < numPersons; i++) {
            int startFloor = data.get(record++);
            int endFloor = data.get(record++);
            passengers.add(new Passenger(startFloor, endFloor));
        }
        return new House(passengers, lifts, buttons, numFloors);
    }

}
