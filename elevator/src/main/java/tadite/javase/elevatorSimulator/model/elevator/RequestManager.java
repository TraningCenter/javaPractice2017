package tadite.javase.elevatorSimulator.model.elevator;

import java.util.List;

public interface RequestManager extends AddingRequestManager{
    void removeRequest(ElevatorRequest request);
    List<ElevatorRequest> getRequests();
}
