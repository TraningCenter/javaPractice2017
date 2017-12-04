package ogkostin.elevator.config;

import ogkostin.elevator.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Creates a list of elevators with parameters, which you
 * input in the command line.
 *
 *  @author Oleg Kostin
 */
public class ElevatorConfigurator implements Configurator {


    private List<Shaft> shafts;
    private Building building;
    private List<Elevator> elevators;
    private int minWeight = 1000;
    private Scanner in = new Scanner(System.in);

    public ElevatorConfigurator(List<Shaft> shafts, Building building) {
        this.shafts = shafts;
        this.building = building;
    }


    protected int inputData() {
        String temp = in.nextLine();
        try {
            int var = Integer.parseInt(temp);
            return var;
        }
        catch (NumberFormatException ex){
            System.out.println("Input number, please");
            return inputData();
        }
    }

    private void configElevator(Shaft shaft) {
        Elevator elevator = new Elevator();
        System.out.println("Elevator " + shaft.getNumber() + " config");
        configStartFloor(elevator);
        configWeight(elevator);
        elevator.setPersonCount(0);
        elevator.setLeftPadding(shaft.getLeftPadding() + 1);
        elevators.add(elevator);
    }

    @Override
    public List<Elevator> configure() {
        elevators = new ArrayList<>();
        for (Shaft shaft : shafts) {
            configElevator(shaft);
        }
        building.setElevatorMinWeight(minWeight);
        return elevators;
    }

    private void configStartFloor(Elevator elevator) {
        System.out.println("Input starting floor" + "(between 1 and " + building.getFloorCount() + ")");
        int temp = inputData();
        if (temp > 0 && temp <= building.getFloorCount()) {
            elevator.setHeight((building.getHeight() - 5) - (temp - 1) * (building.getHeight() - 4) / building.getFloorCount());
            elevator.setCurrentFloor(temp);
        } else {
            System.out.println("Invalid input");
            configStartFloor(elevator);
        }

    }

    private void configWeight(Elevator elevator) {
        System.out.println("Input permissible weight");
        int temp = inputData();
        if (temp > 0) {
            elevator.setPermissibleWeight(temp);
            if (temp <= minWeight) {
                minWeight = temp;
            }
        } else {
            System.out.println("Incorrect input");
            configWeight(elevator);
        }
    }
}
