package tadite.javase.elevatorSimulator.model.misc;

import tadite.javase.elevatorSimulator.model.elevator.AddingRequestManager;
import tadite.javase.elevatorSimulator.model.elevator.ElevatorRequest;

/**
 * Implements button logic, takes level number and add request with it in RequestManager
 */
public class DefaultButton implements Button{
    private AddingRequestManager addingRequestManager;

    public DefaultButton(AddingRequestManager addingRequestManager) {
        this.addingRequestManager = addingRequestManager;
    }

    @Override
    public void addRequest(int level) {
        addingRequestManager.addRequest(new ElevatorRequest(level));
    }
}
