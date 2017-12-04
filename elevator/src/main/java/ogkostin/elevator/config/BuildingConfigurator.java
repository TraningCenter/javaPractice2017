package ogkostin.elevator.config;

import ogkostin.elevator.model.Building;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Creates the building with parameters, which you
 * input in the command line.
 *
 *  @author Oleg Kostin
 */
public class BuildingConfigurator implements Configurator {

    private Building building;

    private Scanner in = new Scanner(System.in);

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

    private void configFloors() {
        System.out.println("Input number of floors (more than 1)");
        int count = inputData();
        if ((building.getHeight() - 7) / 3 >= count && count > 1) {
            building.setFloorCount(count);
        } else {
            if (count > 1) {
                System.out.println("Too many floors");
                configFloors();
            } else {
                System.out.println("Incorrect input");
                configFloors();
            }
        }
    }

    private void configShafts() {
        System.out.println("Input number of elevators");
        int count = inputData();
        if ((building.getWidth() - 4) / 8 >= count & count>0) {
            building.setShaftCount(count);
            building.setElevatorCount(count);
        } else {
            if (count >0) {
                System.out.println("Too many elevators");
                configShafts();
            }else{
                System.out.println("Incorrect input");
                configShafts();
            }
        }
    }

    @Override
    public List<Building> configure() {
        building = new Building();
        configWidth();
        configHeight();
        configFloors();
        configShafts();
        return new ArrayList<Building>() {
            {
                add(building);
            }
        };
    }

    private void configWidth(){
        System.out.println("Input building's width(from 30 to 100)");
        int temp = inputData();
        if (temp>=30 && temp<=100) {
            building.setWidth(temp);
        }
        else {
            System.out.println("Incorrect input");
            configWidth();
        }
    }

    private void configHeight(){
        System.out.println("Input building's height(from 20 to 40)");
        int temp = inputData();
        if (temp>=20 && temp<=40) {
            building.setHeight(temp);
        }
        else {
            System.out.println("Incorrect input");
            configHeight();
        }
    }
}
