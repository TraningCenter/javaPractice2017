package Avilova.Lift;
import static  org.junit.Assert.*;

class PassengerTest {

    @org.junit.jupiter.api.Test
    void setIsLater() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setIsLater(false);
        boolean actual = passenger.getIsLater();
        boolean expected = false;
        assertEquals(actual,expected);
    }

    @org.junit.jupiter.api.Test
    void getIsLater() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setIsLater(true);
        boolean actual = passenger.getIsLater();
        boolean expected = true;
        assertEquals(actual,expected);
    }

    @org.junit.jupiter.api.Test
    void getIsInLift() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setIsInLift(true);
        boolean actual = passenger.getIsInLift();
        boolean expected = true;
        assertEquals(actual,expected);
    }

    @org.junit.jupiter.api.Test
    void setIsInLift() {
        Passenger passenger = new Passenger(1, 2);
        passenger.setIsInLift(false);
        boolean actual = passenger.getIsInLift();
        boolean expected = false;
        assertEquals(actual,expected);
    }

}