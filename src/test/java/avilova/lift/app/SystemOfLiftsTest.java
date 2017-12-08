package avilova.lift.app;

import avilova.lift.app.Lift;
import avilova.lift.app.SystemOfLifts;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SystemOfLiftsTest {

    @Test
    public void isEnd() {
        ArrayList<Lift> lifts = new ArrayList<Lift>();
        lifts.add(new Lift(false, 1, 2, 1, 12, 0, 1));
        lifts.add(new Lift(false, 1, 2, 1, 12, 0, 1));
        SystemOfLifts systemOfLifts = new SystemOfLifts(12, lifts, System.console());
        boolean actual = systemOfLifts.isEnd();
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void isEnd2() {
        ArrayList<Lift> lifts = new ArrayList<Lift>();
        lifts.add(new Lift(true, 1, 2, 1, 12, 12, 1));
        lifts.add(new Lift(true, 1, 2, 1, 12, 12, 2));
        SystemOfLifts systemOfLifts = new SystemOfLifts(12, lifts, System.console());
        boolean actual = systemOfLifts.isEnd();
        boolean expected = false;
        assertEquals(actual, expected);
    }

}
