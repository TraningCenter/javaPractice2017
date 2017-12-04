package configuratorsTests;

import ogkostin.elevator.config.PersonsConfigurator;
import ogkostin.elevator.model.Building;
import ogkostin.elevator.model.Person;
import org.junit.Assert;
import org.junit.Test;
/**
 * Tests  creating of people
 *
 * @author Oleg Kostin
 */
public class PersonConfiguratorTest extends Assert {
    @Test
    public void testCreating() {
        Building building = new Building() {
            {
                setElevatorMinWeight(5);
                setFloorCount(3);

            }
        };


        PersonsConfigurator pc = new PersonsConfigurator(building) {

            int requestCount = 0;
            int data;

            @Override
            protected int inputData() {
                switch (requestCount) {
                    case 0:
                        data = 1;
                        break;
                    case 1:
                        data = 3;
                        break;
                    case 2:
                        data = 1;
                        break;
                    case 3:
                        data = 3;
                        break;
                    case 4:
                        data = 0;
                        break;
                }
                requestCount++;
                return data;
            }
        };
        Person person = pc.configure().get(0);
        int[] expected = {3, 1, 3};
        int[] returned = {person.getWeight(), person.getCurrentFloor(), person.getTargetFloor()};
        assertArrayEquals(expected, returned);
    }
}
