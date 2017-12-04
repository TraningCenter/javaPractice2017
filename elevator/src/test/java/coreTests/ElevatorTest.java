package coreTests;

import ogkostin.elevator.model.Direction;
import ogkostin.elevator.model.Elevator;
import ogkostin.elevator.model.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests main functions of the elevator
 *
 * @author Oleg Kostin
 */
public class ElevatorTest extends Assert {

    Elevator elevator;


    @Before
    public void init() {
        elevator = new Elevator() {{
            setDirection(Direction.none);
            setCurrentFloor(1);
            setHeight(20);
            setLogger(new Logger());
        }};
    }

    @Test
    public void testAddingPriorityFloor() {
        elevator.addPriorityTargetFloor(3);
        assertEquals(Direction.up, elevator.getDirection());
    }

    @Test
    public void testMoving() {
        elevator.addPriorityTargetFloor(3);
        int startPosition = elevator.getHeight();
        elevator.move();
        assertEquals(startPosition - 1, elevator.getHeight());
    }
}
