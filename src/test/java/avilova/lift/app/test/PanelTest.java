package avilova.lift.app.test;
import avilova.lift.app.Lift;
import avilova.lift.app.ui.Panel;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PanelTest {
    @Test
    public void showGraphics() {
        ArrayList<Lift> lifts = new ArrayList<Lift>();
        lifts.add(new Lift(true,1,2,1,12,12,0));
        lifts.add(new Lift(true,1,2,1,12,12,1));

        int numberOfFloors = 12;
        String [][] matr = new String[2 * numberOfFloors + 1][7 + (lifts.size() - 1) * 4];
        Panel panel = new Panel(matr, numberOfFloors, lifts, System.console());
        boolean expected = true;
        boolean actual = panel.showGraphics();
        assertEquals(actual,expected);
    }

}