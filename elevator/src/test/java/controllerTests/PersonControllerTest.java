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
 * Tests conduct of people
 *
 * @author Oleg Kostin
 */
public class PersonControllerTest extends Assert {

    ElevatorController ec;
    ButtonController bc;
    PersonController pc;
    List<Elevator> elevators = new ArrayList<>();
    List<Person> persons = new ArrayList<>();
    List<Floor> floors = new ArrayList<Floor>();

    @Before
    public void init() {


        floors.add(new Floor() {{
            setHeight(25);
            setNumber(1);
        }});
        floors.add(new Floor() {{
            setHeight(26);
            setNumber(2);
        }});
        floors.add(new Floor() {{
            setHeight(27);
            setNumber(3);
        }});
        elevators.add(new Elevator() {{
            setDirection(Direction.up);
            setHeight(27);
            setCurrentFloor(3);
            setLogger(new Logger());
        }});
        persons.add(new Person() {{
            setCurrentFloor(3);
            setDirection(Direction.up);
            setLogger(new Logger());
        }});
        ec = new ElevatorController(elevators, floors);
        bc = new ButtonController(ec);
        pc = new PersonController(persons, elevators, bc, floors);

    }


    @Test
    public void testAction() {
        Person person = persons.get(0);
        pc.update(3, Direction.up, elevators.get(0));
        Person personInElevator = elevators.get(0).getPersons().get(0);
        assertTrue(person == personInElevator);
    }

    @Test
    public void isCompletedtest() {
        pc = new PersonController(persons, elevators, bc, floors);
        pc.setCompletePersonsNumber(3);
        pc.setPersonsNumber(3);
        assertTrue(pc.isCompleted());
    }

}
