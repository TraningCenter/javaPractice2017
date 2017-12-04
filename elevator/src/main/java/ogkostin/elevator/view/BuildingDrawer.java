package ogkostin.elevator.view;

import ogkostin.elevator.model.Building;

/**
 * Draws the building
 *
 *  @author Oleg Kostin
 */
public class BuildingDrawer implements ConsoleDrawer {

    private char[][] image;

    Building building;

    public BuildingDrawer(Building building) {
        this.image = image;
        this.building = building;
    }

    public void setImage(char[][] image) {
        this.image = image;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    private void drawLeftWall() {
        for (int i = 0; i < building.getHeight(); i++) {
            for (int j = 0; j < 2; j++) {
                image[i][j] = '|';
            }
        }
    }

    private void drawRightWall() {
        for (int i = 0; i < building.getHeight(); i++) {
            for (int j = building.getWidth() - 2; j < building.getWidth(); j++) {
                image[i][j] = '|';
            }
        }
    }

    private void drawGround() {
        for (int i = building.getHeight() - 2; i < building.getHeight(); i++) {
            for (int j = 0; j < building.getWidth(); j++) {
                image[i][j] = '-';
            }
        }
    }

    private void drawRoof() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < building.getWidth(); j++) {
                image[i][j] = '-';
            }
        }
    }


    @Override
    public void draw(char[][] image) {

        this.image = image;
        drawLeftWall();
        drawRightWall();
        drawGround();
        drawRoof();


    }
}
