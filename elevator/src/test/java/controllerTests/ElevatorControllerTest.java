package controllerTests;

import ogkostin.elevator.controller.ButtonController;
import ogkostin.elevator.controller.ElevatorController;
import ogkostin.elevator.controller.PersonController;
import ogkostin.elevator.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * Tests conduct of elevators
 *
 * @author Oleg Kostin
 */
public class ElevatorControllerTest extends Assert {

    ElevatorController ec;
    ButtonController bc;
    PersonController pc;

    @Before
    public void init() {
        List<Person> persons = new ArrayList<>();
        List<Floor> floors = new ArrayList<Floor>();
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        floors.add(new Floor() {{
            setHeight(25);
            setNumber(3);
        }});
        floors.add(new Floor() {{
            setHeight(26);
            setNumber(4);
        }});
        floors.add(new Floor() {{
            setHeight(27);
            setNumber(5);
        }});
        floors.add(new Floor() {{
            setHeight(28);
            setNumber(6);
        }});
        floors.add(new Floor() {{
            setHeight(29);
            setNumber(7);
        }});
        List<Elevator> elevators = new ArrayList<>();
        elevators.add(new Elevator() {{
            setHeight(27);
            setDirection(Direction.up);
            setLogger(new Logger());
        }});
        ec = new ElevatorController(elevators, floors);
        bc = new ButtonController(ec);
        pc = new PersonController(persons, elevators, bc, floors);

    }


    @Test
    public void testFloorSearch() {

        ec.action();
        assertEquals(7, ec.getElevators().get(0).getCurrentFloor());
    }

    @Test
    public void testRegister() {
        ec.register(pc);
        ec.getCallersFloors().add(3);
        int number = ec.getElevators().get(0).getTargetFloors().get(0);
        assertEquals(3, number);
    }
}
