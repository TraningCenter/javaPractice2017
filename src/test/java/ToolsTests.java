import com.netcracker.unc.commands.CommandManager;
import com.netcracker.unc.logic.Building;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.logic.interfaces.IPassenger;
import com.netcracker.unc.tools.ObjectsGenerator;
import com.netcracker.unc.tools.UserInteractor;
import com.netcracker.unc.visualization.Visualizer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ToolsTests {

    private List<Floor> floors;

    @Before
    public void init() {
        floors = new ArrayList<>();
        for (int i = 1; i <= 8; i++)
            floors.add(new Floor(i));
    }

    @Test
    public void inputIntTest() {
        int i = 0;
        Scanner scanner = new Scanner("3\nq\n15");
        try {
            i = UserInteractor.inputInt(scanner, 0, 10);
            Assert.assertEquals(3, i);
        } catch (Exception ignored) {
        }
        try {
            i = UserInteractor.inputInt(scanner, 0, 10);
        } catch (Exception e) {
            Assert.assertEquals(3, i);
        }
        try {
            i = UserInteractor.inputInt(scanner, 0, 10);
        } catch (Exception e) {
            Assert.assertEquals(3, i);
        }
    }

    @Test
    public void elevatorGenerationTest() {
        IElevator elevator = ObjectsGenerator.elevatorGeneration(0, floors);
        Assert.assertTrue(elevator.getAvailableFloors().size() > 1);
        Assert.assertTrue(elevator.getCapacity() > 0 && elevator.getCapacity() < 600);
        Assert.assertNotNull(elevator.getCurrentFloor());
    }

    @Test
    public void passengerGenerationTest() {
        IPassenger passenger = ObjectsGenerator.passengerGeneration(floors);
        Assert.assertTrue(floors.contains(passenger.getStartFloor()));
        Assert.assertTrue(floors.contains(passenger.getDestinationFloor()));
        Assert.assertTrue(passenger.getWeight() > 0 && passenger.getWeight() < 200);
        Assert.assertTrue(passenger.getProbabilityOfChoice() >= 0 && passenger.getProbabilityOfChoice() < 100);
    }

    @Test
    public void defaultConfigurationTest() {
        Building building = new Building();
        CommandManager commandManager = new CommandManager();
        Queue<IPassenger> waitingCalls = new LinkedList<>();
        Visualizer visualizer = new Visualizer();
        ObjectsGenerator.defaultConfiguration(building, commandManager, visualizer, waitingCalls);
        Assert.assertEquals(5, building.getFloors().size());
        Assert.assertEquals(3, building.getElevators().size());
        Assert.assertEquals(0, building.getFloors().get(0).getPassengers().size());
        Assert.assertFalse(commandManager.isEmpty());
    }
}
