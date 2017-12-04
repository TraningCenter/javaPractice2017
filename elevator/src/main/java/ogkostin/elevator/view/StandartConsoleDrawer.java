package ogkostin.elevator.view;

import ogkostin.elevator.model.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Prepares image , clear screen and draws ui
 *
 * @author Oleg Kostin
 */
public class StandartConsoleDrawer implements Drawer {

    private int height;
    private int width;
    private String stringImage;
    private char[][] image;
    private BuildingDrawer buildingDrawer;
    private List<ShaftDrawer> shaftDrawers;
    private List<FloorDrawer> floorDrawers;
    private List<ElevatorDrawer> elevatorDrawers;
    private List<ConsoleDrawer> consoleDrawers;


    public StandartConsoleDrawer(Building building, List<Person> persons, List<Elevator> elevators, List<Floor> floors, List<Shaft> shafts) {
        height = building.getHeight() + 2;
        width = building.getWidth() + 9;
        image = new char[height][width];

        consoleDrawers = new ArrayList<>();
        buildingDrawer = new BuildingDrawer(building);

        consoleDrawers.add(buildingDrawer);

        for (Floor floor : floors) {
            floorDrawers = new ArrayList<>();
            floorDrawers.add(new FloorDrawer(floor));
            consoleDrawers.add(new FloorDrawer(floor));
        }
        for (Shaft shaft : shafts) {
            shaftDrawers = new ArrayList<>();
            shaftDrawers.add(new ShaftDrawer(shaft));
            consoleDrawers.add(new ShaftDrawer(shaft));
        }

        for (Elevator elevator : elevators) {
            elevatorDrawers = new ArrayList<>();
            elevatorDrawers.add(new ElevatorDrawer(elevator));
            consoleDrawers.add(new ElevatorDrawer(elevator));
        }


    }


    public void fillImage(char[][] image) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                image[i][j] = ' ';
            }
        }
    }

    private String parse() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(image[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void draw() throws InterruptedException {
        System.out.print("\033[H\033[2J");


        fillImage(image);

        for (ConsoleDrawer cd : consoleDrawers) {
            cd.draw(image);
        }

        System.out.print(parse());

    }

}



