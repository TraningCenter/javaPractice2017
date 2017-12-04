package configuratorsTests;

import ogkostin.elevator.config.FloorConfigurator;
import ogkostin.elevator.model.Building;
import ogkostin.elevator.model.Floor;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Tests  creating of floors
 *
 * @author Oleg Kostin
 */
public class FloorConfiguratorTest extends Assert {
    @Test
    public void testCreating() {

        Building building = new Building() {
            {
                setWidth(60);
                setHeight(30);
                setFloorCount(3);
            }
        };
        FloorConfigurator fc = new FloorConfigurator(building);
        List<Floor> floors = fc.configure();
        assertEquals(3, floors.size());
    }
}
