import com.netcracker.unc.logic.Elevator;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.Passenger;
import com.netcracker.unc.logic.State;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests that verify the correct operation of the internal elevator commands
 */
public class ElevatorTests {

    private IElevator elevator;
    private List<Floor> floors;

    @Before
    public void init() {
        floors = new ArrayList<>();
        for (int i = 1; i <= 8; i++)
            floors.add(new Floor(i));
        // Лифт находится в данный момент на 4 этаже
        elevator = new Elevator(0, floors.get(3), floors, 300);
    }

    @Test
    public void addFloorInQueueWhenFloorIsNotAvailable() {
        Floor floor = new Floor(9);
        elevator.addFloorInQueue(floor);
        Assert.assertFalse(elevator.getAvailableFloors().contains(floor));
    }

    @Test
    public void addFloorInQueueWhenStateIsStopped() {
        elevator.addFloorInQueue(floors.get(5)); //вызов на 6 этаж
        elevator.addFloorInQueue(floors.get(5));
        Assert.assertEquals(1, elevator.getFloorsToVisit().size());
        Assert.assertEquals(State.UP, elevator.getState());
    }

    @Test
    public void addFloorInQueueWhenStateIsUp() {
        // стоим на 4.
        // должно быть после добавлений: 5 8 3 2 1
        elevator.addFloorInQueue(floors.get(7));
        elevator.addFloorInQueue(floors.get(1));
        // должно быть: 8 2
        Assert.assertEquals(2, elevator.getFloorsToVisit().size());
        Assert.assertEquals(floors.get(7), elevator.getNextDestinationFloor());

        elevator.addFloorInQueue(floors.get(0));
        // должно быть: 8 2 1
        Assert.assertEquals(3, elevator.getFloorsToVisit().size());
        Assert.assertEquals(floors.get(0), elevator.getFloorsToVisit().get(2));

        elevator.addFloorInQueue(floors.get(4));
        // должно быть: 5 8 2 1
        Assert.assertEquals(4, elevator.getFloorsToVisit().size());
        Assert.assertEquals(floors.get(4), elevator.getNextDestinationFloor());

        elevator.addFloorInQueue(floors.get(2));
        // должно быть: 5 8 3 2 1
        Assert.assertEquals(5, elevator.getFloorsToVisit().size());
        Assert.assertEquals(floors.get(2), elevator.getFloorsToVisit().get(2));
    }


    @Test
    public void addFloorInQueueWhenStateIsDown() {
        // стоим на 4.
        // должно быть после добавлений: 3 1 5 8
        elevator.addFloorInQueue(floors.get(0));
        elevator.addFloorInQueue(floors.get(7));
        // должно быть: 1 8
        Assert.assertEquals(2, elevator.getFloorsToVisit().size());
        Assert.assertEquals(floors.get(0), elevator.getNextDestinationFloor());

        elevator.addFloorInQueue(floors.get(2));
        // должно быть: 3 1 8
        Assert.assertEquals(3, elevator.getFloorsToVisit().size());
        Assert.assertEquals(floors.get(2), elevator.getNextDestinationFloor());

        elevator.addFloorInQueue(floors.get(4));
        // должно быть: 3 1 5 8
        Assert.assertEquals(4, elevator.getFloorsToVisit().size());
        Assert.assertEquals(floors.get(4), elevator.getFloorsToVisit().get(2));
    }

    @Test
    public void deleteFloorFromQueue() {
        elevator.addFloorInQueue(floors.get(5));
        elevator.addFloorInQueue(floors.get(1));
        Assert.assertEquals(floors.get(5), elevator.getNextDestinationFloor());
        Assert.assertEquals(State.UP, elevator.getState());
        elevator.deleteFloorFromQueue();
        Assert.assertEquals(1, elevator.getFloorsToVisit().size());
        Assert.assertEquals(floors.get(1), elevator.getNextDestinationFloor());
        Assert.assertEquals(State.DOWN, elevator.getState());
        elevator.deleteFloorFromQueue();
        Assert.assertEquals(State.STOPPED, elevator.getState());
    }

    @Test
    public void addPassenger() {
        IPassenger passenger1 = new Passenger(floors.get(5), floors.get(7), 290, 10);
        IPassenger passenger2 = new Passenger(floors.get(0), floors.get(5), 100, 20);
        elevator.addPassenger(passenger1);
        Assert.assertEquals(1, elevator.getPassengers().size());
        Assert.assertFalse(elevator.addPassenger(passenger2));
        Assert.assertEquals(1, elevator.getPassengers().size());
    }

    @Test
    public void deletePassenger() {
        IPassenger passenger1 = new Passenger(floors.get(5), floors.get(7), 290, 10);
        elevator.addPassenger(passenger1);
        Assert.assertEquals(10, elevator.getRemainingCapacity());
        elevator.deletePassenger(passenger1);
        Assert.assertEquals(300, elevator.getRemainingCapacity());
    }

    @Test
    public void initAndAddAvailableFloor() {
        IElevator elevator = new Elevator(1);
        Assert.assertEquals(State.STOPPED, elevator.getState());
        Assert.assertFalse(elevator.isLoaded());
        Assert.assertFalse(elevator.isUnLoaded());
        elevator.setCapacity(500);
        Assert.assertEquals(500, elevator.getRemainingCapacity());
        Assert.assertEquals(500, elevator.getCapacity());
        Assert.assertEquals(0, elevator.getAvailableFloors().size());
        elevator.addAvailableFloor(new Floor(9));
        Assert.assertEquals(1, elevator.getAvailableFloors().size());
        Assert.assertFalse(elevator.setCurrentFloor(new Floor(15)));
    }
}
