import com.netcracker.unc.logic.Building;
import com.netcracker.unc.logic.Elevator;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.Passenger;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildingTests {

    private Building building;

    @Before
    public void init() {
        building = new Building();
    }

    @Test
    public void addElevator() {
        IElevator elevator = new Elevator(0, new Floor(1), null, 0);
        building.addElevator(elevator);
        Assert.assertEquals(1, building.getElevators().size());
    }

    @Test
    public void addFloor() {
        Floor floor = new Floor(0);
        building.addFloor(floor);
        Assert.assertEquals(1, building.getFloors().size());
    }

    @Test
    public void initBuilding() {
        List<Floor> floors = new ArrayList<>();
        List<IElevator> elevators = new ArrayList<>();
        elevators.add(new Elevator(0));
        floors.add(new Floor(1));
        floors.add(new Floor(2));
        building = new Building(elevators, floors);
        Assert.assertEquals(1, building.getElevators().size());
        Assert.assertEquals(2, building.getFloors().size());
    }

    @Test
    public void initFloor() {
        List<IPassenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(new Floor(1), new Floor(5)));
        Floor floor = new Floor(1, passengers);
        Assert.assertEquals(passengers, floor.getPassengers());
        Assert.assertFalse(floor.isPushedButtonDown());
        Assert.assertFalse(floor.isPushedButtonUp());
    }
}
