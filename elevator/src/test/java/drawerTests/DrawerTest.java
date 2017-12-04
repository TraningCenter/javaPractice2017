package drawerTests;

import ogkostin.elevator.config.FloorConfigurator;
import ogkostin.elevator.config.ShaftConfigrator;
import ogkostin.elevator.model.*;
import ogkostin.elevator.view.StandartConsoleDrawer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests drawing of the ui
 *
 * @author Oleg Kostin
 */
public class DrawerTest extends Assert {

    StandartConsoleDrawer scd;
    Building building;
    List<Floor> floors = new ArrayList<>();
    List<Elevator> elevators = new ArrayList<>();
    List<Shaft> shafts = new ArrayList<>();
    List<Person> persons = new ArrayList<>();


    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        building = new Building() {{
            setHeight(30);
            setWidth(60);
            setShaftCount(3);
            setFloorCount(3);
        }};
        FloorConfigurator fc = new FloorConfigurator(building);
        floors = fc.configure();
        ShaftConfigrator sc = new ShaftConfigrator(building);
        shafts = sc.configure();
        persons.add(new Person() {{
            setTargetFloor(2);
        }});
        elevators.add(new Elevator() {{
            setLeftPadding(5);
            setHeight(17);
        }});
        scd = new StandartConsoleDrawer(building, persons, elevators, floors, shafts);
    }


    @Test
    public void drawTest() throws InterruptedException {
        scd.draw();

    }
}
