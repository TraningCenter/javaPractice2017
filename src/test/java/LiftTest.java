
import com.alegerd.mainData.Application;
import com.alegerd.model.Direction;
import com.alegerd.model.Floor;
import com.alegerd.model.Lift;
import com.alegerd.model.Person;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.ILift;
import com.alegerd.model.interfaces.IPerson;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiftTest extends Assert {

    private Map<Lift,Integer> checkNumber = new HashMap<>();
    private Map<Lift,Integer> checkFloor = new HashMap<>();
    private Map<Lift, String> toString = new HashMap<>();

    @Before
    public void setUp(){
        checkNumber.put(new Lift(0,0),0);
        checkNumber.put(new Lift(1,0),1);
        checkNumber.put(new Lift(2,0),2);

        checkFloor.put(new Lift(0,0),0);
        checkFloor.put(new Lift(1,2),2);
        checkFloor.put(new Lift(2,4),4);

        toString.put(new Lift(0,0),
                "Lift number: " + 0 + ", number of people: " + 0 + "\n");
        toString.put(new Lift(1,1),
                "Lift number: " + 1 + ", number of people: " + 0 + "\n");
        toString.put(new Lift(2,0),
                "Lift number: " + 2 + ", number of people: " + 0 + "\n");
    }

    @After
    public void end(){
        checkNumber.clear();
    }

    @Test
    public void testGetNumber(){
        for (Map.Entry<Lift, Integer> entry :
                checkNumber.entrySet()) {
            ILift lift = entry.getKey();
            long oldNumber = entry.getValue();
            long newNum = lift.getNumber();
            assertEquals(oldNumber, newNum);
        }
    }

    @Test
    public void testToString(){
        for (Map.Entry<Lift, String> entry :
                toString.entrySet()) {
            ILift lift = entry.getKey();
            String line = entry.getValue();
            assertEquals(line, lift.toString());
        }
    }

    @Test
    public void testGetFloor(){
        for (Map.Entry<Lift, Integer> entry :
                checkFloor.entrySet()) {
            ILift lift = entry.getKey();
            long expected = entry.getValue();
            long actual = lift.getFloorLiftOn();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testNumberOfPeople(){
        ILift lift = new Lift(0,0);
        long count = lift.getNumberOfPeople();
        assertEquals(0,count);
    }

    @Test
    public void testLiftButtons(){
        Application app = new Application();
        app.buildModel(null);
        ILift lift = new Lift(0,0);
        Direction firstDir;
        lift.liftButtonPushed(3);
        lift.thinkWhereToGo();
        firstDir = lift.getCurrentDirection();
        assertEquals(Direction.UP, firstDir);
    }

    @Test
    public void testFloorButtons(){
        Application app = new Application();
        app.buildModel(null);
        app.testApp();
        IPerson person = new Person(1,34,2,3);

        IFloor first = new Floor(1);
        IFloor second = new Floor(2);
        IFloor third = new Floor(3);

        List<IFloor> floors = new ArrayList<>();
        floors.add(first);
        floors.add(second);
        floors.add(third);
        second.addWaitingPerson(person);

        ILift lift = new Lift(0,floors,1000);
        Direction firstDir;
        lift.callingButtonPushed(2,Direction.UP);

        firstDir = lift.getCurrentDirection();
        assertEquals(Direction.UP, firstDir);
    }

}
