
import com.alegerd.model.Floor;
import com.alegerd.model.House;
import com.alegerd.model.Lift;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.IHouse;
import com.alegerd.model.interfaces.ILift;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HouseTest extends Assert{

    @Before
    public void setUp(){

    }

    @After
    public void end(){

    }

    @Test
    public void test(){
        IHouse house;
        List<IFloor> floors = new ArrayList<>();
        List<ILift> lifts = new ArrayList<>();
        floors.add(new Floor(0));
        floors.add(new Floor(2));
        lifts.add(new Lift(1,1));
        house = new House(floors, lifts);
        house.addFloor(new Floor(2));
        long floorsCount = house.getNumberOfFloors();
        long liftsCount = house.getNumberOfLifts();
        assertEquals(3, floorsCount);
        assertEquals(1,liftsCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFloorException(){
        List<IFloor> floors = new ArrayList<>();
        List<ILift> lifts = new ArrayList<>();
        IHouse house = new House(floors, lifts);
        house.addFloor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLiftException(){
        List<IFloor> floors = new ArrayList<>();
        List<ILift> lifts = new ArrayList<>();
        IHouse house = new House(floors, lifts);
        house.addLift(null);
    }

    @Test
    public void testIterator(){
        IHouse house;
        List<IFloor> floors = new ArrayList<>();
        List<ILift> lifts = new ArrayList<>();
        house = new House(floors, lifts);

        ILift lift = new Lift(2,1);
        house.addLift(lift);
        ILift actual = null;
        Iterator<ILift> iter = house.liftIterator();
        if(iter.hasNext()){
            actual = iter.next();
        }

        IFloor floor = new Floor(2);
        house.addFloor(floor);
        IFloor actual2 = null;
        Iterator<IFloor> iterator = house.floorIterator();
        if(iterator.hasNext()){
            actual2 = iterator.next();
        }

        assertEquals(lift, actual);
        assertEquals(floor,actual2);
    }
}
