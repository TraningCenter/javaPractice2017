package configuratorsTests;

import ogkostin.elevator.config.ElevatorConfigurator;
import ogkostin.elevator.config.ShaftConfigrator;
import ogkostin.elevator.model.Building;
import ogkostin.elevator.model.Elevator;
import ogkostin.elevator.model.Shaft;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
/**
 * Tests  creating of elevators
 *
 * @author Oleg Kostin
 */
public class ElevatorConfiguratorTest extends Assert {


    @Test
    public void testCreating() {
        Building building = new Building() {
            {
                setWidth(60);
                setHeight(30);
                setFloorCount(3);
                setShaftCount(1);
            }
        };

        ShaftConfigrator sc = new ShaftConfigrator(building);
        List<Shaft> shafts = sc.configure();
        ElevatorConfigurator ec = new ElevatorConfigurator(shafts, building) {

            int requestCount = 0;
            int data;

            @Override
            protected int inputData() {
                switch (requestCount) {
                    case 0:
                        data = 1;
                        break;
                    case 1:
                        data = 30;
                        break;
                }
                requestCount++;
                return data;
            }
        };
        Elevator elevator = ec.configure().get(0);
        int[] expected = {1, 30};
        int[] returned = {elevator.getCurrentFloor(), elevator.getPermissibleWeight()};
        assertArrayEquals(expected, returned);
    }
}
