package ogkostin.elevator.view;

import ogkostin.elevator.model.Elevator;
/**
 * Draws the elevator
 *
 * @author Oleg Kostin
 */
public class ElevatorDrawer implements ConsoleDrawer {

    Elevator elevator;


    public ElevatorDrawer(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void draw(char[][] image) {


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (j == 1 && i == 1) {
                    if (elevator.getPersonCount() != 0)
                        image[elevator.getHeight() + i][elevator.getLeftPadding() + j] = Character.forDigit(elevator.getPersonCount(), 10);
                    else
                        image[elevator.getHeight() + i][elevator.getLeftPadding() + j] = ' ';
                } else
                    image[elevator.getHeight() + i][elevator.getLeftPadding() + j] = '@';
            }
        }
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }
}
