package ogkostin.elevator.controller;

/**
 * Allows  people to call elevators
 *
 *  @author Oleg Kostin
 */
public class ButtonController implements Controller {


    private ElevatorController elevatorController;

    public ButtonController(ElevatorController elevatorController) {

        this.elevatorController = elevatorController;
    }

    public void callFromFloor(Integer floorNumber) {
        elevatorController.getCallersFloors().add(floorNumber);
    }

    public void cancelCall(Integer number) {
        elevatorController.getCallersFloors().remove(number);
    }


}
