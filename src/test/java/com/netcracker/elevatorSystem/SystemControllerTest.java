package com.netcracker.elevatorSystem;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Класс для тестирования класса SystemController
 */
public class SystemControllerTest {
    SystemController systemController = new SystemController();
    Shaft shaft = new Shaft(1);
    @Test
    public void fillInitialTarget() throws Exception {
            ArrayList<Passenger> newPassengers = new ArrayList<Passenger>();
            for (int j = 0; j < 5; j++) {
                newPassengers.add(new Passenger(j + 1));
            }
            shaft.getElevator().setPassengers(newPassengers);
            shaft.getElevator().setLoadCapacity(200);
            for (Passenger pas : shaft.getElevator().getPassengers()) {
                pas.setDepartureFloor(2);
                pas.setWeight(65);
            }
        boolean actual = systemController.fillInitialTarget();
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void createCurrentTarget_1() throws Exception {

        Shaft[] shafts = new Shaft[8];
        for (int i = 0; i < shafts.length; i++) {
            shafts[i] = new Shaft(i+1);
            shafts[i].setElevator(new Elevator(i+1));
        }
        for (int i = 0; i < shafts.length; i++) {
            ArrayList<Passenger> newPassengers = new ArrayList<Passenger>();
            for (int j = 0; j < 3; j++) {
                newPassengers.add(new Passenger(j + 1));
            }
            shafts[i].getElevator().setPassengers(newPassengers);
            shafts[i].getElevator().setLoadCapacity(350);
            shafts[i].getElevator().setCurrentFloor(4);
            for (Passenger pas : shafts[i].getElevator().getPassengers()) {
                pas.setDepartureFloor(5);
                pas.setDestinationFloor(7);
                pas.setWeight(65);
            }
            shafts[i].getElevator().getTarget().add(5);
        }
        systemController.setShafts(shafts);
        int actual = systemController.createCurrentTarget(0);
        int expected = 5;
        assertEquals(actual, expected);
    }

    @Test
    public void createCurrentTarget_2() throws Exception {

        Shaft[] shafts = new Shaft[1];
        for (int i = 0; i < shafts.length; i++) {
            shafts[i] = new Shaft(i+1);
            shafts[i].setElevator(new Elevator(i+1));
        }
        for (int i = 0; i < shafts.length; i++) {
            ArrayList<Passenger> newPassengers = new ArrayList<Passenger>();
            for (int j = 0; j < 1; j++) {
                newPassengers.add(new Passenger(j + 1));
            }
            shafts[i].getElevator().setPassengers(newPassengers);
            shafts[i].getElevator().setLoadCapacity(350);
            shafts[i].getElevator().setCurrentFloor(1);
        }
        systemController.setShafts(shafts);
        int actual = systemController.createCurrentTarget(0);
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void startSystem_1() throws Exception {
        Shaft[] shafts = new Shaft[2];
        Shaft.numberOfFloors = 4;
        systemController.setNumberOfShafts(1);
        for (int i = 0; i < shafts.length; i++) {
            shafts[i] = new Shaft(i+1);
            shafts[i].setElevator(new Elevator(i+1));
        }
        for (int i = 0; i < shafts.length; i++) {
            ArrayList<Passenger> newPassengers = new ArrayList<Passenger>();
            for (int j = 0; j < 3; j++) {
                newPassengers.add(new Passenger(j + 1));
            }
            shafts[i].getElevator().setPassengers(newPassengers);
            shafts[i].getElevator().setLoadCapacity(120);

        }
        shafts[0].getElevator().getPassengers().get(0).setDepartureFloor(4);
        shafts[0].getElevator().getPassengers().get(0).setDestinationFloor(1);
        shafts[0].getElevator().getPassengers().get(0).setWeight(25);
        shafts[0].getElevator().getPassengers().get(0).setProbabilityIn(1);
        shafts[0].getElevator().getPassengers().get(1).setDepartureFloor(3);
        shafts[0].getElevator().getPassengers().get(1).setDestinationFloor(2);
        shafts[0].getElevator().getPassengers().get(1).setWeight(96);
        shafts[0].getElevator().getPassengers().get(1).setProbabilityIn(1);
        shafts[0].getElevator().getPassengers().get(2).setDepartureFloor(2);
        shafts[0].getElevator().getPassengers().get(2).setDestinationFloor(1);
        shafts[0].getElevator().getPassengers().get(2).setWeight(75);
        shafts[0].getElevator().getPassengers().get(2).setProbabilityIn(1);

        shafts[1].getElevator().getPassengers().get(0).setDepartureFloor(1);
        shafts[1].getElevator().getPassengers().get(0).setDestinationFloor(3);
        shafts[1].getElevator().getPassengers().get(0).setWeight(25);
        shafts[1].getElevator().getPassengers().get(0).setProbabilityIn(1);
        shafts[1].getElevator().getPassengers().get(1).setDepartureFloor(3);
        shafts[1].getElevator().getPassengers().get(1).setDestinationFloor(1);
        shafts[1].getElevator().getPassengers().get(1).setWeight(75);
        shafts[1].getElevator().getPassengers().get(1).setProbabilityIn(1);
        shafts[1].getElevator().getPassengers().get(2).setDepartureFloor(5);
        shafts[1].getElevator().getPassengers().get(2).setDestinationFloor(2);
        shafts[1].getElevator().getPassengers().get(2).setWeight(75);
        shafts[1].getElevator().getPassengers().get(2).setProbabilityIn(1);


        systemController.setShafts(shafts);
        systemController.fillInitialTarget();
        boolean actual = systemController.startSystem(0);
        boolean expected = true;
        assertEquals(actual, expected);

    }

    @Test
    public void startSystem_2() throws Exception {
        Shaft[] shafts = new Shaft[2];
        Shaft.numberOfFloors = 3;
        systemController.setNumberOfShafts(2);
        for (int i = 0; i < shafts.length; i++) {
            shafts[i] = new Shaft(i+1);
            shafts[i].setElevator(new Elevator(i+1));
        }
        for (int i = 0; i < shafts.length; i++) {
            ArrayList<Passenger> newPassengers = new ArrayList<Passenger>();
            for (int j = 0; j < 2; j++) {
                newPassengers.add(new Passenger(j + 1));
            }
            shafts[i].getElevator().setPassengers(newPassengers);
            shafts[i].getElevator().setLoadCapacity(550);
        }
        shafts[0].getElevator().getPassengers().get(0).setDepartureFloor(1);
        shafts[0].getElevator().getPassengers().get(0).setDestinationFloor(3);
        shafts[0].getElevator().getPassengers().get(0).setWeight(25);
        shafts[0].getElevator().getPassengers().get(0).setProbabilityIn(1);
        shafts[0].getElevator().getPassengers().get(1).setDepartureFloor(2);
        shafts[0].getElevator().getPassengers().get(1).setDestinationFloor(1);
        shafts[0].getElevator().getPassengers().get(1).setWeight(75);
        shafts[0].getElevator().getPassengers().get(1).setProbabilityIn(1);

        shafts[1].getElevator().getPassengers().get(0).setDepartureFloor(1);
        shafts[1].getElevator().getPassengers().get(0).setDestinationFloor(2);
        shafts[1].getElevator().getPassengers().get(0).setWeight(25);
        shafts[1].getElevator().getPassengers().get(0).setProbabilityIn(1);
        shafts[1].getElevator().getPassengers().get(1).setDepartureFloor(2);
        shafts[1].getElevator().getPassengers().get(1).setDestinationFloor(3);
        shafts[1].getElevator().getPassengers().get(1).setWeight(75);
        shafts[1].getElevator().getPassengers().get(1).setProbabilityIn(1);

        systemController.setShafts(shafts);
        systemController.fillInitialTarget();
        boolean actual = systemController.startSystem(0);
        boolean expected = true;
        assertEquals(actual, expected);

    }

}