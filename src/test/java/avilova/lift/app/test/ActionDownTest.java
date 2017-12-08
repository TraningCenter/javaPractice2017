package avilova.lift.app.test;

import avilova.lift.app.Lift;
import avilova.lift.app.interfaces.impl.ActionDown;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ActionDownTest {

    @Test
    public void find() {
        Lift lift = new Lift(false, 1, 12, 1, 12, 1, 1);
        lift.passengerList.get(0).setIsInLift(true);
        int expected = lift.passengerList.get(0).getFloorOfDestination();
        ActionDown action = new ActionDown(lift);
        int actual = action.find();
        assertEquals(actual, expected);
    }

    @Test
    public void find2() {
        Lift lift = new Lift(false, 1, 12, 1, 12, 1, 1);
        lift.passengerList.get(0).setFloorOfDeparture(11);
        lift.passengerList.get(0).setFloorOfDestination(2);
        int expected = 11;
        ActionDown action = new ActionDown(lift);
        int actual = action.find();
        assertEquals(actual, expected);
    }

    @Test
    public void anyoneElse() {
        Lift lift = new Lift(false, 1, 12, 1, 12, 0, 1);
        ActionDown action = new ActionDown(lift);
        boolean expected = false;
        boolean actual = action.anyoneElse();
        assertEquals(actual, expected);
    }

    @Test
    public void anyoneElse2() {
        Lift lift = new Lift(false, 2, 12, 1, 3, 3, 1);
        lift.passengerList.get(0).setIsInLift(true);
        lift.passengerList.get(0).setFloorOfDestination(2);

        ActionDown action = new ActionDown(lift);
        boolean expected = true;
        boolean actual = action.anyoneElse();
        assertEquals(actual, expected);
    }

    @Test
    public void run() {

        Lift lift = new Lift(false, 1, 12, 1, 12, 1, 1);
        lift.passengerList.get(0).setFloorOfDeparture(11);
        lift.passengerList.get(0).setFloorOfDestination(2);

        ActionDown action = new ActionDown(lift);
        boolean expected = true;
        boolean actual = action.run(11);
        assertEquals(actual, expected);
    }

    @Test
    public void run2() {

        Lift lift = new Lift(false, 12, 12, 1, 12, 1, 1);
        lift.passengerList.get(0).setFloorOfDeparture(11);
        lift.passengerList.get(0).setFloorOfDestination(2);

        ActionDown action = new ActionDown(lift);
        boolean expected = true;
        boolean actual = action.run(11);
        assertEquals(actual, expected);
    }

    @Test
    public void run3() {

        Lift lift = new Lift(false, 10, 12, 1, 12, 1, 1);
        lift.passengerList.get(0).setFloorOfDeparture(11);
        lift.passengerList.get(0).setFloorOfDestination(10);
        lift.passengerList.get(0).setIsInLift(true);

        ActionDown action = new ActionDown(lift);
        boolean expected = true;
        boolean actual = action.run(10);
        assertEquals(actual, expected);
    }

    @Test
    public void run4() {

        Lift lift = new Lift(false, 10, 12, 1, 12, 1, 1);
        lift.passengerList.get(0).setFloorOfDeparture(10);
        lift.passengerList.get(0).setFloorOfDestination(9);
        lift.passengerList.get(0).setProbabilityOfEnter(1);

        ActionDown action = new ActionDown(lift);
        boolean expected = true;
        boolean actual = action.run(10);
        assertEquals(actual, expected);
    }

    @Test
    public void run5() {

        Lift lift = new Lift(false, 10, 12, 1, 12, 1, 1);
        lift.passengerList.get(0).setFloorOfDeparture(10);
        lift.passengerList.get(0).setFloorOfDestination(9);
        lift.passengerList.get(0).setIsInLift(true);

        ActionDown action = new ActionDown(lift);
        boolean expected = true;
        boolean actual = action.run(10);
        assertEquals(actual, expected);
    }

    @Test
    public void run6() {

        Lift lift = new Lift(false, 10, 1, 1, 12, 1, 1);
        lift.passengerList.get(0).setFloorOfDeparture(10);
        lift.passengerList.get(0).setFloorOfDestination(9);
        lift.passengerList.get(0).setProbabilityOfEnter(100);
        lift.passengerList.get(0).setFloorOfDeparture(10);
        lift.passengerList.get(0).setFloorOfDestination(8);
        lift.passengerList.get(0).setProbabilityOfEnter(100);

        ActionDown action = new ActionDown(lift);
        boolean expected = true;
        boolean actual = action.run(10);
        assertEquals(actual, expected);
    }
}
