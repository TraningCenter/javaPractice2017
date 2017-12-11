package com.netcracker.elevatorSystem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;


import static org.junit.Assert.*;

/**
 * Класс для тестирования класса Elevator
 */
public class ElevatorTest {
    private Elevator elevator = new Elevator(1);

    @Test
    public void addNewDestination() throws Exception {
        boolean actual = elevator.addNewDestination(5);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void removeDestination() throws Exception {
        boolean actual = elevator.removeDestination(4);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void removePostponedDestination() throws Exception {
        boolean actual = elevator.removePostponedDestination(5);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void fillPostponedTarget() throws Exception {
        Passenger passenger = new Passenger(1);
        boolean actual = elevator.fillPostponedTarget(passenger);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void getCurrentFloor() throws Exception {
        elevator.setCurrentFloor(4);
        int actual = elevator.getCurrentFloor();
        int expected = 4;
        assertEquals(actual, expected);
    }

    @Test
    public void getLoadCapacity() throws Exception {
        elevator.setLoadCapacity(500);
        int actual = elevator.getLoadCapacity();
        int expected = 500;
        assertEquals(actual, expected);
    }

    @Test
    public void getCurrentWeight() throws Exception {
        elevator.setCurrentWeight(120);
        int actual = elevator.getCurrentWeight();
        int expected = 120;
        assertEquals(actual, expected);
    }

    @Test
    public void getTarget() throws Exception {
        TreeSet<Integer> target = new TreeSet<Integer>();
        elevator.setTarget(target);
        TreeSet<Integer> actual = elevator.getTarget();
        TreeSet<Integer> expected = target;
        assertEquals(actual, expected);
    }

    @Test
    public void getPostponedTarget() throws Exception {
        TreeSet<Integer> postponedTarget = new TreeSet<Integer>();
        elevator.setPostponedTarget(postponedTarget);
        TreeSet<Integer> actual = elevator.getPostponedTarget();
        TreeSet<Integer> expected = postponedTarget;
        assertEquals(actual, expected);
    }

    @Test
    public void getPassengersInElevator() throws Exception {
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        elevator.setPassengersInElevator(passengers);
        ArrayList<Passenger> actual = elevator.getPassengersInElevator();
        ArrayList<Passenger> expected = passengers;
        assertEquals(actual, expected);
    }

    @Test
    public void getPassengers() throws Exception {
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        elevator.setPassengers(passengers);
        ArrayList<Passenger> actual = elevator.getPassengers();
        ArrayList<Passenger> expected = passengers;
        assertEquals(actual, expected);
    }

    @Test
    public void getElevatorIndex() throws Exception {
        elevator.setElevatorIndex(1);
        int actual = elevator.getElevatorIndex();
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void getDirection() throws Exception {
        elevator.setDirection(Direction.DOWN);
        Direction actual = elevator.getDirection();
        Direction expected = Direction.DOWN;
        assertEquals(actual, expected);
    }


    @Test
    public void load() throws Exception {
        Passenger passenger = new Passenger(1);
        passenger.setDepartureFloor(2);
        passenger.setDestinationFloor(5);
        passenger.setWeight(50);
        boolean expected = true;
        boolean actual = elevator.load(passenger);
        assertEquals(actual, expected);
    }

    @Test
    public void unload() throws Exception {
        Passenger passenger = new Passenger(2);
        passenger.setDestinationFloor(5);
        passenger.setWeight(50);
        boolean expected = true;
        boolean actual = elevator.unload(passenger);
        assertEquals(actual, expected);
    }

    @Test
    public void makeDirection_1() throws Exception {
        elevator.setCurrentFloor(2);
        boolean expected = true;
        boolean actual = elevator.makeDirection(5);
        assertEquals(actual, expected);
    }

    @Test
    public void makeDirection_2() throws Exception {
        elevator.setCurrentFloor(3);
        boolean expected = true;
        boolean actual = elevator.makeDirection(2);
        assertEquals(actual, expected);
    }

    @Test
    public void moveToNextFloor_1() throws Exception {
        elevator.setCurrentFloor(1);
        boolean expected = true;
        boolean actual = elevator.moveToNextFloor(4);
        assertEquals(actual, expected);
    }

    @Test
    public void moveToNextFloor_2() throws Exception {
        elevator.setCurrentFloor(5);
        boolean expected = true;
        boolean actual = elevator.moveToNextFloor(3);
        assertEquals(actual, expected);
    }

    @Test
    public void toStringTest() throws Exception {
        elevator.setElevatorIndex(1);
        elevator.setCurrentFloor(4);
        elevator.setLoadCapacity(200);
        elevator.setCurrentWeight(78);
        ArrayList<Passenger> pasInEl = new ArrayList<Passenger>();
        for (int j = 0; j < 1; j++) {
            pasInEl.add(new Passenger(j + 1));
        }
        elevator.setPassengers(pasInEl);
        elevator.setPassengersInElevator(pasInEl);
        String actual = elevator.toString();
        String expected = "Elevator №" + 1 +"\n" + "Floor: "+ 4 + "\n" + "Load capacity: " + 200
                + "\n" + "Current weight: " + 78
                + "\n" + "Number of passengers in the elevator: " + 1 + " ( " + 1 + " " + ")" + "\n" + "Number of arrived passengers: " + 0 + "\n";
        assertEquals(actual, expected);

    }

}