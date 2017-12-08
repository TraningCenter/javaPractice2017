package avilova.lift.app;

import avilova.lift.app.Passenger;
import static org.junit.Assert.*;
import org.junit.Test;

public class PassengerTest {

    @Test
    public void setIsLater() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setIsLater(false);
        boolean actual = passenger.getIsLater();
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void getIsLater() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setIsLater(true);
        boolean actual = passenger.getIsLater();
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void getIsInLift() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setIsInLift(true);
        boolean actual = passenger.getIsInLift();
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void setIsInLift() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setIsInLift(false);
        boolean actual = passenger.getIsInLift();
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void isSuit() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setProbabilityOfEnter(10);
        boolean expected = false;
        boolean actual = passenger.isSuit();
        assertEquals(actual, expected);
    }

    @Test
    public void isSuit2() {
        Passenger passenger = new Passenger(1, 4);
        passenger.setProbabilityOfEnter(40);
        boolean expected = true;
        boolean actual = passenger.isSuit();
        assertEquals(actual, expected);
    }

    @Test
    public void getProbabilityOfEnter() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setProbabilityOfEnter(250);
        int actual = passenger.getProbabilityOfEnter();
        int expected = 250;
        assertEquals(actual, expected);
    }

    @Test
    public void setProbabilityOfEnter() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setProbabilityOfEnter(50);
        int actual = passenger.getProbabilityOfEnter();
        int expected = 50;
        assertEquals(actual, expected);
    }

    @Test
    public void getFloorOfDeparture() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setFloorOfDeparture(1);
        int actual = passenger.getFloorOfDeparture();
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void setFloorOfDeparture() {
        Passenger passenger = new Passenger(1, 3);
        passenger.setFloorOfDeparture(2);
        int actual = passenger.getFloorOfDeparture();
        int expected = 2;
        assertEquals(actual, expected);
    }

    @Test
    public void getFloorOfDestination() {
        Passenger passenger = new Passenger(1, 4);
        passenger.setFloorOfDestination(4);
        int actual = passenger.getFloorOfDestination();
        int expected = 4;
        assertEquals(actual, expected);
    }

    @Test
    public void setFloorOfDestination() {
        Passenger passenger = new Passenger(1, 4);
        passenger.setFloorOfDestination(3);
        int actual = passenger.getFloorOfDestination();
        int expected = 3;
        assertEquals(actual, expected);
    }
}
