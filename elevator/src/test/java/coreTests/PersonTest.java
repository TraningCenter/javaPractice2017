package coreTests;

import ogkostin.elevator.model.Elevator;
import ogkostin.elevator.model.Floor;
import ogkostin.elevator.model.Logger;
import ogkostin.elevator.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * Tests main functions of the person
 *
 * @author Oleg Kostin
 */
public class PersonTest extends Assert {

    Elevator elevator;
    Floor floor;
    Person person;

    @Before
    public void init() {
        person = new Person() {{
            setWeight(2);
            setTargetFloor(3);
            setLogger(new Logger());
        }};
        floor = new Floor() {{
            setPersonsCount(1);
        }};
        elevator = new Elevator() {{
            setCurrentWeight(0);
            setPersonCount(0);
            setPermissibleWeight(3);
            setLogger(new Logger());
        }};
    }

    @Test
    public void enteringTest() {
        person.enter(elevator, floor);
        int temp = elevator.getPriorityTargetFloors().get(0);
        assertEquals(3, temp);
        assertEquals(1, elevator.getPersonCount());
    }

    @Test
    public void goOutTest() {
        person.enter(elevator, floor);
        person.goOut(elevator, floor);
        assertEquals(0, elevator.getPersonCount());
    }

}
