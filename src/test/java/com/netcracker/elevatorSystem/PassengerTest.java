package com.netcracker.elevatorSystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Класс для тестирования класса Passenger
 */
public class PassengerTest {
    private Passenger passenger = new Passenger(1);

    @Test
    public void getDepartureFloor() throws Exception {
        passenger.setDepartureFloor(4);
        int actual = passenger.getDepartureFloor();
        int expected = 4;
        assertEquals(actual, expected);
    }

    @Test
    public void getDestinationFloor() throws Exception {
        passenger.setDestinationFloor(7);
        int actual = passenger.getDestinationFloor();
        int expected = 7;
        assertEquals(actual, expected);
    }

    @Test
    public void getStatus() throws Exception {
        passenger.setStatus(false);
        boolean actual = passenger.getStatus();
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void getWeight() throws Exception {
        passenger.setWeight(75);
        int actual = passenger.getWeight();
        int expected = 75;
        assertEquals(actual, expected);
    }

    @Test
    public void getPassengerIndex() throws Exception {
        passenger.setPassengerIndex(1);
        int actual = passenger.getPassengerIndex();
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void isArrived() throws Exception {
        passenger.setIsArrived(true);
        boolean actual = passenger.isArrived();
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void getFloorButton() throws Exception {
        FloorButton button = new FloorButton();
        passenger.setFloorButton(button);
        FloorButton actual = passenger.getFloorButton();
        FloorButton expected = button;
        assertEquals(actual, expected);
    }

    @Test
    public void getElevatorIndex() throws Exception {
        passenger.setElevatorIndex(1);
        int actual = passenger.getElevatorIndex();
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void pushFloorButton() throws Exception {
        passenger.setDepartureFloor(7);
        passenger.setDestinationFloor(1);
        boolean expected = true;
        boolean actual = passenger.pushFloorButton();
        assertEquals(actual, expected);
    }

    @Test
    public void toStringTest() throws Exception {
        passenger.setPassengerIndex(1);
        passenger.setDepartureFloor(2);
        passenger.setDestinationFloor(4);
        passenger.setWeight(70);
        passenger.setProbabilityIn(0.07);
        String actual = passenger.toString();
        String expected = "Passenger №" + 1 + ": " + 2 + " -> " + 4 + " ( " + 70 + "kg ) " + "7%";
        assertEquals(actual, expected);
    }

    @Test
    public void getProbabilityIn() throws Exception {
        passenger.setProbabilityIn(0.7);
        double actual = passenger.getProbabilityIn();
        double expected = 0.7;
        assertEquals(actual, expected, 1e-15);
    }

}