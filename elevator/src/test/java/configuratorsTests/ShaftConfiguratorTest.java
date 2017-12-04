package configuratorsTests;

import ogkostin.elevator.config.ShaftConfigrator;
import ogkostin.elevator.model.Building;
import ogkostin.elevator.model.Shaft;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
/**
 * Tests  creating of shafts
 *
 * @author Oleg Kostin
 */
public class ShaftConfiguratorTest extends Assert {

    @Test
    public void testCreating() {
        Building building = new Building() {
            {
                setWidth(60);
                setHeight(30);
                setShaftCount(3);
            }
        };

        ShaftConfigrator sc = new ShaftConfigrator(building);
        List<Shaft> shafts = sc.configure();
        assertEquals(3, shafts.size());
    }
}
