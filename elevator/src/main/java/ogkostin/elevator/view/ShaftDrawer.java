package ogkostin.elevator.view;

import ogkostin.elevator.model.Shaft;
/**
 * Draws the shaft
 *
 * @author Oleg Kostin
 */
public class ShaftDrawer implements ConsoleDrawer {

    Shaft shaft;

    public ShaftDrawer(Shaft shaft) {
        this.shaft = shaft;
    }


    public void draw(char[][] image) {
        for (int i = 2; i < shaft.getHeight(); i++) {
            image[i][shaft.getLeftPadding()] = '|';
            image[i][shaft.getLeftPadding() + 4] = '|';
        }
    }
}
