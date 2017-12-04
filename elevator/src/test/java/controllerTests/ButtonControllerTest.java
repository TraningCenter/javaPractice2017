package controllerTests;

import ogkostin.elevator.controller.ButtonController;
import ogkostin.elevator.controller.ElevatorController;
import ogkostin.elevator.model.Elevator;
import ogkostin.elevator.model.Floor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * Tests conduct of the button
 *
 * @author Oleg Kostin
 */
public class ButtonControllerTest extends Assert {

    ElevatorController ec;
    ButtonController bc;

    @Before
    public void init() {
        List<Floor> floors = new ArrayList<Floor>();
        List<Elevator> elevators = new ArrayList<>();
        ec = new ElevatorController(elevators, floors);
        bc = new ButtonController(ec);
    }

    @Test
    public void testCall() {
        bc.callFromFloor(3);
        assertTrue(ec.getCallersFloors().contains(3));
    }

    @Test
    public void testCancel() {
        bc.callFromFloor(3);
        bc.cancelCall(3);
        assertFalse(ec.getCallersFloors().contains(3));
    }

}
