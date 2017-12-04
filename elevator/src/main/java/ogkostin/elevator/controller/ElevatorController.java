package ogkostin.elevator.controller;

import ogkostin.elevator.model.Elevator;
import ogkostin.elevator.model.Floor;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage conduct of elevators
 *
 *  @author Oleg Kostin
 */
public class ElevatorController implements Controller {

    private List<Elevator> elevators;
    private List<Integer> callersFloors = new ArrayList<>();
    private List<Floor> floors;

    public ElevatorController(List<Elevator> elevators, List<Floor> floors) {
        this.elevators = elevators;
        this.floors = floors;

    }

    public void register(PersonController personController) {
        for (Elevator elevator : elevators) {
            elevator.registerObserver(personController);
            elevator.setTargetFloors(callersFloors);
        }
    }

    public void action() {

        checkFloor();

        for (Elevator elevator : elevators) {
            elevator.move();
        }


    }

    private void checkFloor() {
        for (Elevator elevator : elevators) {
            for (Floor floor : floors) {
                if (elevator.getHeight() == (floor.getHeight() - 2)) {
                    if (elevator.getOwnPriorityFloor() != 0 && elevator.getOwnPriorityFloor() == floor.getNumber()) {
                        elevator.deleteOwnPriorityFloor();
                    }
                    elevator.setCurrentFloor(floor.getNumber());
                    elevator.notifyObservers();
                }
            }
        }
    }

    public List<Integer> getCallersFloors() {
        return callersFloors;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }


}
