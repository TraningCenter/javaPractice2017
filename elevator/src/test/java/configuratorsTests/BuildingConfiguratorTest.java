package configuratorsTests;

import ogkostin.elevator.config.BuildingConfigurator;
import ogkostin.elevator.model.Building;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the building's creating
 *
 * @author Oleg Kostin
 */
public class BuildingConfiguratorTest  extends Assert {

    @Test
    public void testConfigure() {
        BuildingConfigurator bc = new BuildingConfigurator() {

            int requestCount = 0;
            int data;

            @Override
            protected int inputData() {
                switch (requestCount) {
                    case 0:
                        data = 60;
                        break;
                    case 1:
                        data = 30;
                        break;
                    case 2:
                        data = 3;
                        break;
                    case 3:
                        data = 3;
                        break;
                }
                requestCount++;
                return data;
            }
        };


        Building building = bc.configure().get(0);
        int[] expected = {60, 30, 3, 3};
        int[] returned = {building.getWidth(), building.getHeight(), building.getFloorCount(), building.getShaftCount()};
        Assert.assertArrayEquals(expected, returned);
    }

}
