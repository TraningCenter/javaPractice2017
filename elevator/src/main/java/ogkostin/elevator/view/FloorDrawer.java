package ogkostin.elevator.view;

import ogkostin.elevator.model.Floor;
/**
 * Draws the floor
 *
 * @author Oleg Kostin
 */
public class FloorDrawer implements ConsoleDrawer {

    private Floor floor;

    public FloorDrawer(Floor floor) {
        this.floor = floor;
    }


    @Override
    public void draw(char[][] image) {
        for (int j = 2; j < floor.getWidth(); j++) {
            image[floor.getHeight()][j] = '_';
        }
        image[floor.getHeight()][floor.getWidth() + 2] = ' ';
        image[floor.getHeight()][floor.getWidth() + 3] = '[';
        image[floor.getHeight()][floor.getWidth() + 4] = Character.forDigit(floor.getPersonsCount(), 10);
        image[floor.getHeight()][floor.getWidth() + 5] = ']';
        image[floor.getHeight()][floor.getWidth() + 6] = ' ';
        image[floor.getHeight()][floor.getWidth() + 7] = '[';
        image[floor.getHeight()][floor.getWidth() + 8] = Character.forDigit(floor.getCompletedPersonsCount(), 10);
        image[floor.getHeight()][floor.getWidth() + 9] = ']';
    }

    public void setFloor(Floor floor) {
        this.floor = floor;

    }
}
