package avilova.lift.app;

import avilova.lift.app.Lift;
import avilova.lift.app.ui.PrintConsole;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PrintConsoleTest {

    @Test
    public void createMatr() {
        ArrayList<Lift> lifts = new ArrayList<Lift>();
        lifts.add(new Lift(false, 1, 2, 1, 12, 0, 1));
        lifts.add(new Lift(false, 1, 2, 1, 12, 0, 1));
        PrintConsole printConsole = new PrintConsole(lifts, 12, System.console());
        boolean actual = printConsole.createMatr();
        boolean expected = true;
        assertEquals(actual, expected);
    }
}
