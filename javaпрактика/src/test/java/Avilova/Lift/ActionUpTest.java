package Avilova.Lift;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class ActionUpTest {
    @Test
    void find() {
        Lift lift = new Lift(true,1,12,1,12,1,1);
        lift.passengerList.get(0).setIsInLift(true);
        int expected = lift.passengerList.get(0).getFloorOfDestination();
        ActionUp action = new ActionUp(lift);
        int actual = action.find();
        assertEquals(actual,expected);
    }

    @Test
    void find2() {
        Lift lift = new Lift(true,1,12,1,12,1,1);
        lift.passengerList.get(0).setFloorOfDeparture(2);
        lift.passengerList.get(0).setFloorOfDestination(12);
        int expected = 2;
        ActionUp action = new ActionUp(lift);
        int actual = action.find();
        assertEquals(actual,expected);
    }

    @Test
    void anyoneElse() {
        Lift lift = new Lift(true,1,12,1,12,0,1);
        ActionUp action = new ActionUp(lift);
        boolean expected = false;
        boolean actual = action.anyoneElse();
        assertEquals(actual,expected);
    }

    @Test
    void anyoneElse2() {
        Lift lift = new Lift(true,2,12,1,3,3,1);
        lift.passengerList.get(0).setIsInLift(true);
        lift.passengerList.get(0).setFloorOfDestination(2);

        ActionUp action = new ActionUp(lift);
        boolean expected = true;
        boolean actual = action.anyoneElse();
        assertEquals(actual,expected);
    }

    @Test
    void run() {

        Lift lift = new Lift(true,1,12,1,12,1,1);
        lift.passengerList.get(0).setFloorOfDeparture(10);
        lift.passengerList.get(0).setFloorOfDestination(12);

        ActionUp action = new ActionUp(lift);
        boolean expected = true;
        boolean actual = action.run(10);
        assertEquals(actual,expected);
    }

    @Test
    void run2() {

        Lift lift = new Lift(true,12,12,1,12,1,1);
        lift.passengerList.get(0).setFloorOfDeparture(10);
        lift.passengerList.get(0).setFloorOfDestination(11);

        ActionUp action = new ActionUp(lift);
        boolean expected = true;
        boolean actual = action.run(11);
        assertEquals(actual,expected);
    }

    @Test
    void run3() {

        Lift lift = new Lift(true,10,12,1,12,1,1);
        lift.passengerList.get(0).setFloorOfDeparture(9);
        lift.passengerList.get(0).setFloorOfDestination(10);
        lift.passengerList.get(0).setIsInLift(true);

        ActionUp action = new ActionUp(lift);
        boolean expected = true;
        boolean actual = action.run(10);
        assertEquals(actual,expected);
    }

    @Test
    void run4() {

        Lift lift = new Lift(true,10,12,1,12,1,1);
        lift.passengerList.get(0).setFloorOfDeparture(10);
        lift.passengerList.get(0).setFloorOfDestination(11);
        lift.passengerList.get(0).setProbabilityOfEnter(1);

        ActionUp action = new ActionUp(lift);
        boolean expected = true;
        boolean actual = action.run(10);
        assertEquals(actual,expected);
    }

    @Test
    void run5() {

        Lift lift = new Lift(true,10,1,1,12,1,1);
        lift.passengerList.get(0).setFloorOfDeparture(10);
        lift.passengerList.get(0).setFloorOfDestination(11);
        lift.passengerList.get(0).setProbabilityOfEnter(100);
        lift.passengerList.get(0).setFloorOfDeparture(10);
        lift.passengerList.get(0).setFloorOfDestination(12);
        lift.passengerList.get(0).setProbabilityOfEnter(100);

        ActionUp action = new ActionUp(lift);
        boolean expected = true;
        boolean actual = action.run(10);
        assertEquals(actual,expected);
    }


}