package Avilova.Lift;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class ControllerOfLiftTest {
    @Test
    void isChange() {
        Lift lift = new Lift(false,1,12,1,12,3,1);
        ControllerOfLift controllerOfLift = new ControllerOfLift(lift);
        for (int i = 0; i < lift.passengerList.size(); i++)
            lift.passengerList.get(i).setIsInLift(true);
        boolean actual = false;
        boolean expected = controllerOfLift.isChange();
        assertEquals(actual,expected);
    }

}