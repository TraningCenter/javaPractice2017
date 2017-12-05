
import com.alegerd.mainData.Application;
import com.alegerd.model.*;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.ILift;
import com.alegerd.model.interfaces.IPerson;
import com.alegerd.utils.Config;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class LiftTest extends Assert {

    private Map<Lift,Integer> checkNumber = new HashMap<>();
    private Map<Lift,Integer> checkFloor = new HashMap<>();
    private Map<Lift, String> toString = new HashMap<>();
    private House house;
    private Application application;

    List<IFloor> floors = new ArrayList<>();
    List<ILift> lifts = new ArrayList<>();
    List<IPerson> people = new ArrayList<>();

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

        Config.setTimeInterval(0);
        Config.setStopDrawing(true);
    }

    private House makeHouse(List<IFloor> floors, List<ILift> lifts){
        int floorNum = 10;
        int liftNum = 1;
        List<IPerson> people = new ArrayList<>();

        for (int i = 0; i < floorNum; i++) {
            floors.add(new Floor(i));
        }
        for (int i = 0; i < liftNum; i++) {
            lifts.add(new Lift(i,floors,1000));
        }

        people.add(new Person(1,45,2,3));
        people.add(new Person(2,45,6,2));
        people.add(new Person(1,45,1,7));
        people.add(new Person(2,45,5,6));
        people.add(new Person(1,45,4,3));
        people.add(new Person(2,45,7,1));

        for (IFloor floor : floors) {
            for (IPerson person : people) {
                if (floor.getNumber().equals(person.getFloorNumber())){
                    floor.addWaitingPerson(person);
                }
            }
        }

        return new House(floors, lifts);
    }

    private House makeHouse2(List<IFloor> floors, List<ILift> lifts){
        int floorNum = 10;
        int liftNum = 1;
        List<IPerson> people = new ArrayList<>();

        for (int i = 0; i < floorNum; i++) {
            floors.add(new Floor(i));
        }
        for (int i = 0; i < liftNum; i++) {
            lifts.add(new Lift(i,floors,46));
        }

        people.add(new Person(1,45,2,3));
        people.add(new Person(2,45,6,2));
        people.add(new Person(1,45,1,7));
        people.add(new Person(2,45,5,6));
        people.add(new Person(1,45,4,3));
        people.add(new Person(2,45,7,1));

        for (IFloor floor : floors) {
            for (IPerson person : people) {
                if (floor.getNumber().equals(person.getFloorNumber())){
                    floor.addWaitingPerson(person);
                }
            }
        }

        return new House(floors, lifts);
    }

    private House makeHouse3(List<IFloor> floors, List<ILift> lifts){
        int floorNum = 10;
        int liftNum = 2;
        List<IPerson> people = new ArrayList<>();

        for (int i = 0; i < floorNum; i++) {
            floors.add(new Floor(i));
        }
        lifts.add(new Lift(1,floors,1000, 5));
        lifts.add(new Lift(1,floors,1000, 2));

        people.add(new Person(1,45,2,3, 0));
        people.add(new Person(1,45,6,3,1));
        people.add(new Person(1,45,2,3,1));
        people.add(new Person(2,45,6,2,0));
        people.add(new Person(1,45,1,7,1));
        people.add(new Person(2,45,5,6,0));
        people.add(new Person(1,45,4,3,1));
        people.add(new Person(2,45,7,1,0));


        for (IFloor floor : floors) {
            for (IPerson person : people) {
                if (floor.getNumber().equals(person.getFloorNumber())){
                    floor.addWaitingPerson(person);
                }
            }
        }

        return new House(floors, lifts);
    }

    @After
    public void end(){
        checkNumber.clear();
        checkFloor.clear();
        toString.clear();

        Config.setTimeInterval(1000);
        Config.setStopDrawing(false);
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
    public void testLift(){

        List<IFloor> floors = new ArrayList<>();
        List<ILift> lifts = new ArrayList<>();
        house = makeHouse(floors, lifts);

        try{
            application = new Application(house);
            application.start();

            for (IFloor floor : floors) {
                Iterator<IPerson> personIterator = floor.getPersonIterator();
                while (personIterator.hasNext()){
                    Integer floorNum = personIterator.next().getFloorNumber();
                    Integer destNum = personIterator.next().getDestinationFloor();
                    assertEquals(destNum, floorNum);
                }
            }

        }
        catch (Exception e){}
    }

    @Test
    public void testOverweightLift(){

        List<IFloor> floors = new ArrayList<>();
        List<ILift> lifts = new ArrayList<>();
        house = makeHouse2(floors, lifts);

        try{
            application = new Application(house);
            application.start();

            for (IFloor floor : floors) {
                Iterator<IPerson> personIterator = floor.getPersonIterator();
                while (personIterator.hasNext()){
                    Integer floorNum = personIterator.next().getFloorNumber();
                    Integer destNum = personIterator.next().getDestinationFloor();
                    assertEquals(destNum, floorNum);
                }
            }

        }
        catch (Exception e){}
    }

    @Test
    public void testLiftBypassing(){

        List<IFloor> floors = new ArrayList<>();
        List<ILift> lifts = new ArrayList<>();
        house = makeHouse3(floors, lifts);

        try{
            application = new Application(house);
            application.start();

            for (IFloor floor : floors) {
                Iterator<IPerson> personIterator = floor.getPersonIterator();
                while (personIterator.hasNext()){
                    Integer floorNum = personIterator.next().getFloorNumber();
                    Integer destNum = personIterator.next().getDestinationFloor();
                    assertEquals(destNum, floorNum);
                }
            }

        }
        catch (Exception e){}
    }

    @Test
    public void testApplicationConstructor(){

        List<IFloor> floors = new ArrayList<>();
        List<ILift> lifts = new ArrayList<>();
        house = makeHouse2(floors, lifts);

        try{
            String input = null;
            application = new Application(input);
            application.start();

            for (IFloor floor : floors) {
                Iterator<IPerson> personIterator = floor.getPersonIterator();
                while (personIterator.hasNext()){
                    Integer floorNum = personIterator.next().getFloorNumber();
                    Integer destNum = personIterator.next().getDestinationFloor();
                    assertEquals(destNum, floorNum);
                }
            }

        }
        catch (Exception e){}
    }

    /*
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
    */

}
