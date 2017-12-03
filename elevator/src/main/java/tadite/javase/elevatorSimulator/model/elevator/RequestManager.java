package tadite.javase.elevatorSimulator.model.elevator;

import java.util.List;

/**
 * Saving requests, and working with it
 */
public interface RequestManager extends AddingRequestManager{
    void removeRequest(ElevatorRequest request);
    List<ElevatorRequest> getRequests();
}
