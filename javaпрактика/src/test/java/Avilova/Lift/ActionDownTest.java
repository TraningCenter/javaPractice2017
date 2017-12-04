package Avilova.Lift;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class ActionDownTest {
    @Test
    void find() {
        Lift lift = new Lift(true,1,12,1,12,1,1);
        for (Passenger passenger:lift.passengerList)
            passenger.setIsInLift(true);
        int expected = lift.passengerList.get(0).getFloorOfDestination();
        ActionDown action = new ActionDown(lift);
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

}