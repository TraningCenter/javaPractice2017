package com.netcracker.elevatorSystem;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Класс для тестирования класса MainVisualizer
 */
public class MainVisualizerTest {
    Shaft[] shafts = new Shaft[4];
    MainVisualizer mainVisualizer = new MainVisualizer(shafts);

    @Test
    public void getHeight() throws Exception {
        Shaft.numberOfFloors = 3;
        int actual = mainVisualizer.getHeight();
        int expected = 10;
        assertEquals(actual, expected);
    }

    @Test
    public void getWidth() throws Exception {
        int actual = mainVisualizer.getWidth();
        int expected = 27;
        assertEquals(actual, expected);
    }


    @Test
    public void showPassengersInfo() throws Exception {
        Shaft[] shaft = new Shaft[1];
        for (int i = 0; i < 1; i++) {
            shaft[i] = new Shaft(i+1);
        }
        for (int i = 0; i < 1; i++) {
            ArrayList<Passenger> passengers = new ArrayList<Passenger>();
            for (int j = 0; j < 1; j++) {
                passengers.add(new Passenger(j + 1));
            }
            shaft[i].getElevator().setPassengers(passengers);

            for (Passenger p : shaft[i].getElevator().getPassengers()) {
                p.setPassengerIndex(1);
                p.setWeight(70);
                p.setDepartureFloor(3);
                p.setDestinationFloor(5);
                p.setProbabilityIn(0.05);
            }
        }
        mainVisualizer.setShafts(shaft);
        String actual = mainVisualizer.showPassengersInfo();
        String  expected = "Passengers of elevator №" + 1 + "\n" + "Passenger №" + 1 + ": " + 3 + " -> " + 5 + " ( " + 70 + "kg ) "
                + "0,05" + "\n";
        assertEquals(actual, expected);
    }

    @Test
    public void showElevatorsInfo() throws Exception {
        Shaft[] shaft = new Shaft[1];
        for (int i = 0; i < 1; i++) {
            shaft[i] = new Shaft(i+1);
        }
        for (int i = 0; i < 1; i++) {
            ArrayList<Passenger> passengers = new ArrayList<Passenger>();
            for (int j = 0; j < 1; j++) {
                passengers.add(new Passenger(j + 1));
            }
            shaft[i].getElevator().setPassengers(passengers);
            for (Passenger p : shaft[i].getElevator().getPassengers()) {
                p.setDepartureFloor(8);
                p.setDestinationFloor(2);
            }
            shaft[i].getElevator().setPassengersInElevator(passengers);
            shaft[i].getElevator().setElevatorIndex(1);
            shaft[i].getElevator().setCurrentFloor(5);
            shaft[i].getElevator().setCurrentWeight(60);
            shaft[i].getElevator().setLoadCapacity(150);
        }
        mainVisualizer.setShafts(shaft);
        String actual = mainVisualizer.showElevatorsInfo();
        String expected = "Elevator №" + 1 +"\n" + "Floor: "+ 5 + "\n" + "Load capacity: " + 150
                + "\n" + "Current weight: " + 60
                + "\n" + "Number of passengers in the elevator: " + 1 + " ( " + 1 + " " + ")"
                + "\n" + "Number of arrived passengers: " + 0 + "\n" + "\n";
        assertEquals(actual, expected);
    }

}