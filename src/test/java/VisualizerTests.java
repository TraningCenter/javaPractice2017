import com.netcracker.unc.logic.Building;
import com.netcracker.unc.logic.Elevator;
import com.netcracker.unc.logic.Floor;
import com.netcracker.unc.logic.interfaces.IElevator;
import com.netcracker.unc.visualization.Visualizer;
import com.netcracker.unc.visualization.VisualizerConfig;
import com.netcracker.unc.visualization.elements.ElevatorPicture;
import com.netcracker.unc.visualization.elements.FloorPicture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.netcracker.unc.visualization.VisualizerConfig.ELEVATOR_HEIGHT;

public class VisualizerTests {

    Visualizer visualizer;

    @Before
    public void init() {
        VisualizerConfig.FLOOR_WIDTH = 4;
        VisualizerConfig.ROOF_HEIGHT = 3;
        VisualizerConfig.ELEVATOR_WIDTH = 3;
        VisualizerConfig.SEPARATOR_WIDTH = 2;
        ELEVATOR_HEIGHT = 3;
        VisualizerConfig.FLOOR_HEIGHT = 3;
        visualizer = new Visualizer();
    }

    @Test
    public void elevatorPictureDraw() {
        ElevatorPicture elevatorPicture = new ElevatorPicture(0, 1, 3, false);
        String string = elevatorPicture.draw(-1);
        Assert.assertEquals(ELEVATOR_HEIGHT + 2 + 4, string.length());
        Assert.assertEquals("|       |", string);
        string = elevatorPicture.draw(1);
        Assert.assertEquals("| |¯¯¯| |", string);
        string = elevatorPicture.draw(ELEVATOR_HEIGHT / 2 + elevatorPicture.getYCoordinate());
        Assert.assertEquals("| | 3 | |", string);
        elevatorPicture.setOpened(true);
        string = elevatorPicture.draw(ELEVATOR_HEIGHT / 2 + elevatorPicture.getYCoordinate());
        Assert.assertEquals("| |<3>| |", string);
        string = elevatorPicture.draw(ELEVATOR_HEIGHT - 1 + elevatorPicture.getYCoordinate());
        Assert.assertEquals("| |___| |", string);
    }

    @Test
    public void floorPictureDraw() {
        FloorPicture floorPicture = new FloorPicture(3, 1, 3, false, false);
        String string = floorPicture.draw(1);
        Assert.assertEquals("    ", string);
        floorPicture.setPushedUp(true);
        string = floorPicture.draw(1);
        Assert.assertEquals("   ˄", string);
        string = floorPicture.draw(2);
        Assert.assertEquals(" 3  ", string);
        string = floorPicture.draw(3);
        Assert.assertEquals("    ", string);
        floorPicture.setPushedDown(true);
        string = floorPicture.draw(3);
        Assert.assertEquals("   ˅", string);
    }

    @Test
    public void drawRoof() {
        ElevatorPicture elevatorPicture = new ElevatorPicture(0, 1, 3, false);
        ElevatorPicture elevatorPicture2 = new ElevatorPicture(1, 1, 0, false);
        List<ElevatorPicture> list = new ArrayList<>();
        list.add(elevatorPicture);
        list.add(elevatorPicture2);
        visualizer.setElevatorPictureList(list);
        String string = visualizer.drawRoof();
        Assert.assertEquals(189, string.length());
    }

    @Test
    public void drawFloors() {
        ElevatorPicture elevatorPicture = new ElevatorPicture(0, 1, 3, false);
        ElevatorPicture elevatorPicture2 = new ElevatorPicture(1, 1, 0, false);
        FloorPicture floorPicture = new FloorPicture(2, 1, 1, false, false);
        FloorPicture floorPicture2 = new FloorPicture(1, 4, 1, false, false);
        List<ElevatorPicture> elevatorPictures = new ArrayList<>();
        List<FloorPicture> floorPictures = new ArrayList<>();
        elevatorPictures.add(elevatorPicture);
        elevatorPictures.add(elevatorPicture2);
        floorPictures.add(floorPicture);
        floorPictures.add(floorPicture2);
        visualizer.setElevatorPictureList(elevatorPictures);
        visualizer.setFloorPicturesList(floorPictures);
        String string = visualizer.drawFloors();
        Assert.assertEquals(312, string.length());
    }

    @Test
    public void getFloorPictureById() {
        FloorPicture floorPicture = new FloorPicture(2, 1, 1, false, false);
        FloorPicture floorPicture2 = new FloorPicture(1, 4, 1, false, false);
        List<FloorPicture> floorPictures = new ArrayList<>();
        floorPictures.add(floorPicture);
        floorPictures.add(floorPicture2);
        visualizer.setFloorPicturesList(floorPictures);
        FloorPicture newFloorPicture = visualizer.getFloorPictureById(2);
        Assert.assertEquals(floorPicture, newFloorPicture);
        newFloorPicture = visualizer.getFloorPictureById(1);
        Assert.assertEquals(floorPicture2, newFloorPicture);
        newFloorPicture = visualizer.getFloorPictureById(5);
        Assert.assertNull(newFloorPicture);


    }

    @Test
    public void setConfiguration() {
        Building building = new Building();
        visualizer.setConfiguration(building);
        Assert.assertEquals(0, visualizer.getElevatorPictureList().size());
        Assert.assertEquals(0, visualizer.getFloorPicturesList().size());
        List<Floor> floors = new ArrayList<>();
        floors.add(new Floor(1));
        floors.add(new Floor(2));
        building.setFloors(floors);
        visualizer.setConfiguration(building);
        Assert.assertEquals(0, visualizer.getElevatorPictureList().size());
        Assert.assertEquals(0, visualizer.getFloorPicturesList().size());
        List<IElevator> elevators = new ArrayList<>();
        elevators.add(new Elevator(0, floors.get(0), floors, 500));
        building.setElevators(elevators);
        visualizer.setConfiguration(building);
        Assert.assertEquals(1, visualizer.getElevatorPictureList().size());
        Assert.assertEquals(2, visualizer.getFloorPicturesList().size());
        Assert.assertEquals(4, visualizer.getElevatorPictureList().get(0).getYCoordinate());
        Assert.assertEquals(1, visualizer.getFloorPicturesList().get(0).getyCoordinate());
    }
}
