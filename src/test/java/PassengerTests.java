import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.Passenger;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.interfaces.IPassenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests that verify the correct direction of passengers
 */
public class PassengerTests {

    private List<Floor> floors;
    private IPassenger passenger;

    @Before
    public void init() {
        floors = new ArrayList<>();
        for (int i = 1; i <= 4; i++)
            floors.add(new Floor(i));
        passenger = new Passenger();
        passenger.setStartFloor(floors.get(0));
        passenger.setDestinationFloor(floors.get(3));
        passenger.setWeight(50);
        passenger.setProbabilityOfChoice(15);
    }

    @Test
    public void getDirectionPassenger() {
        State state = passenger.getDirection();
        Assert.assertEquals(State.UP, state);
        passenger.setStartFloor(floors.get(3));
        passenger.setDestinationFloor(floors.get(0));
        state = passenger.getDirection();
        Assert.assertEquals(State.DOWN, state);
        passenger.setDestinationFloor(floors.get(3));
        state = passenger.getDirection();
        Assert.assertEquals(State.STOPPED, state);
    }
}
