import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.Passenger;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.interfaces.IPassenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PassengerTests {

    List<Floor> floors;
    IPassenger passenger;

    @Before
    public void init(){
        floors = new ArrayList<>();
        floors.add(new Floor(1));
        floors.add(new Floor(2));
        floors.add(new Floor(3));
        floors.add(new Floor(4));
        passenger= new Passenger(floors.get(0), floors.get(3));
    }

    @Test
    public void getDirection(){
        State state = passenger.getDirection();
        Assert.assertEquals(State.UP, state);
        passenger.setStartFloor(floors.get(3));
        passenger.setDestinationFloor(floors.get(0));
        state = passenger.getDirection();
        Assert.assertEquals(State.DOWN, state);
    }
}
