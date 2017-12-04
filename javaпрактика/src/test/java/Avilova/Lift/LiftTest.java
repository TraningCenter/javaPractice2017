package Avilova.Lift;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class LiftTest {
    @Test
    void getRout() {
        Lift lift = new Lift(false,1,2,1,12,3,1);
        boolean actual = lift.getRout();
        boolean expected = false;
        assertEquals(actual,expected);
    }

    @Test
    void setRout() {
        Lift lift = new Lift(false,1,2,1,12,3,1);
        lift.setRout(true);
        boolean actual = lift.getRout();
        boolean expected = true;
        assertEquals(actual,expected);
    }

    @Test
    void getLocation() {
        Lift lift = new Lift(false,1,2,1,12,3,1);
        int actual = lift.getLocation();
        int expected = 1;
        assertEquals(actual,expected);
    }

    @Test
    void setLocation() {
        Lift lift = new Lift(false,1,2,1,12,3,1);
        lift.setLocation(2);
        int actual = lift.getLocation();
        int expected = 2;
        assertEquals(actual,expected);
    }

    @Test
    void getVol() {
        Lift lift = new Lift(false,1,2,1,12,3,1);
        int actual = lift.getVol();
        int expected = 2;
        assertEquals(actual,expected);
    }

    @Test
    void getPossibleInitialFloor() {
        Lift lift = new Lift(false,1,2,1,12,3,1);
        int actual = lift.getPossibleInitialFloor();
        int expected = 1;
        assertEquals(actual,expected);
    }

    @Test
    void getPossibleFinaleFloor() {
        Lift lift = new Lift(false,1,2,1,12,3,1);
        int actual = lift.getPossibleFinaleFloor();
        int expected = 12;
        assertEquals(actual,expected);
    }

    @Test
    void getNumber() {
        Lift lift = new Lift(false,1,2,1,12,3,1);
        int actual = lift.getNumber();
        int expected = 1;
        assertEquals(actual,expected);
    }

    @Test
    void colOfPassangeInLift() {
        Lift lift = new Lift(false,1,12,1,12,3,1);
        for (int i = 0; i < lift.passengerList.size(); i++)
            lift.passengerList.get(i).setIsInLift(true);
        int actual = lift.colOfPassangeInLift();
        int expected = lift.passengerList.size();
        assertEquals(actual,expected);
    }

}