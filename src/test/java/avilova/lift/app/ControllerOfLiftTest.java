package avilova.lift.app;
import avilova.lift.app.Lift;
import avilova.lift.app.ControllerOfLift;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ControllerOfLiftTest {
    @Test
    public void isChange() {
        Lift lift = new Lift(false,1,12,1,12,13,1);
        ControllerOfLift controllerOfLift = new ControllerOfLift(lift);
        for (int i = 0; i < lift.passengerList.size(); i++)
            lift.passengerList.get(i).setIsInLift(true);
        boolean actual = false;
        boolean expected = controllerOfLift.isChange();
        assertEquals(actual,expected);
    }

    @Test
    public void isChange2() {
        Lift lift = new Lift(false,1,12,1,12,1,1);
        ControllerOfLift controllerOfLift = new ControllerOfLift(lift);
        lift.passengerList.get(0).setFloorOfDeparture(1);
        lift.passengerList.get(0).setFloorOfDestination(2);
        lift.passengerList.get(0).setProbabilityOfEnter(100);
        boolean actual = true;
        boolean expected = controllerOfLift.isChange();
        assertEquals(actual,expected);
    }

    @Test
    public void isChange3() {
        Lift lift = new Lift(true,1,12,1,12,1,1);
        ControllerOfLift controllerOfLift = new ControllerOfLift(lift);
        lift.passengerList.get(0).setFloorOfDeparture(2);
        lift.passengerList.get(0).setFloorOfDestination(1);
        lift.passengerList.get(0).setProbabilityOfEnter(100);
        boolean actual = true;
        boolean expected = controllerOfLift.isChange();
        assertEquals(actual,expected);
    }

    @Test
    public void run() {
        Lift lift = new Lift(false,1,12,1,12,100,0);
        ControllerOfLift controllerOfLift = new ControllerOfLift(lift);
        boolean actual = true;
        boolean expected = controllerOfLift.run();
        assertEquals(actual,expected);

    }

    @Test
    public void run2() {
        Lift lift = new Lift(true,1,12,1,12,50,0);
        ControllerOfLift controllerOfLift = new ControllerOfLift(lift);
        boolean actual = true;
        boolean expected = controllerOfLift.run();
        assertEquals(actual,expected);

    }
}