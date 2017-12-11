package com.netcracker.elevatorSystem;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Класс для тестирования класса Render
 */
public class RenderTest {
    Render render = new Render();

    @Test
    public void buildElevator() throws Exception {
        Shaft shaft = new Shaft(1);
        for (int i = 0; i < 1; i++) {
            shaft.getElevator().setElevatorIndex(1);
            shaft.getElevator().setCurrentFloor(1);
            shaft.getElevator().setElevatorIndex(1);
        }
        char[][] matrix = new char[4][12];
        boolean actual = render.buildElevator(matrix, shaft);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void buildFrame() throws Exception {
        char[][] matrix = new char[4][12];
        boolean actual = render.buildFrame(matrix);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void draw() throws Exception {
        char[][] matrix = new char[4][12];
        boolean actual = render.draw(matrix);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void showPassengersInfo() throws Exception {
        Shaft shaft = new Shaft(1);
        for (int i = 0; i < 1; i++) {
            ArrayList<Passenger> passengers = new ArrayList<Passenger>();
            for (int j = 0; j < 1; j++) {
                passengers.add(new Passenger(j + 1));
            }
            shaft.getElevator().setPassengers(passengers);

            for (Passenger p : shaft.getElevator().getPassengers()) {
                p.setPassengerIndex(1);
                p.setWeight(85);
                p.setDepartureFloor(8);
                p.setDestinationFloor(2);
                p.setProbabilityIn(0.08);
            }
        }
        String actual = render.showPassengersInfo(shaft.getElevator().getPassengers(), 1);
        String expected = "Passengers of elevator №" + 1 + "\n" + "Passenger №" + 1 + ": " + 8 + " -> " + 2 + " ( " + 85 + "kg ) "
                + "0,08" + "\n";
        assertEquals(actual, expected);
    }

    @Test
    public void showElevatorInfo() throws Exception {
        Shaft shaft = new Shaft(1);

        for (int i = 0; i < 1; i++) {
            ArrayList<Passenger> passengers = new ArrayList<Passenger>();
            for (int j = 0; j < 1; j++) {
                passengers.add(new Passenger(j + 1));
            }
            shaft.getElevator().setPassengers(passengers);
            for (Passenger p : shaft.getElevator().getPassengers()) {
                p.setDepartureFloor(8);
                p.setDestinationFloor(2);
            }
            shaft.getElevator().setPassengersInElevator(passengers);
            shaft.getElevator().setElevatorIndex(1);
            shaft.getElevator().setCurrentFloor(4);
            shaft.getElevator().setCurrentWeight(80);
            shaft.getElevator().setLoadCapacity(200);
        }

        String actual = render.showElevatorInfo(shaft);
        String expected = "Elevator №" + 1 +"\n" + "Floor: "+ 4 + "\n" + "Load capacity: " + 200
                + "\n" + "Current weight: " + 80
                + "\n" + "Number of passengers in the elevator: " + 1 + " ( " + 1 + " " + ")"
                + "\n" + "Number of arrived passengers: " + 0 + "\n" + "\n";
        assertEquals(actual, expected);
    }

    @Test
    public void getCellNumberY() throws Exception {
        int actual = render.getCellNumberY(7);
        int expected = 19;
        assertEquals(actual, expected);
    }

    @Test
    public void getCellNumberX() throws Exception {
        int actual = render.getCellNumberX(3);
        int expected = 14;
        assertEquals(actual, expected);
    }




}