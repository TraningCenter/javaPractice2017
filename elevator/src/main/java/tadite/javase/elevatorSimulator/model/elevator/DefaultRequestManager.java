package tadite.javase.elevatorSimulator.model.elevator;

import java.util.LinkedList;
import java.util.List;

public class DefaultRequestManager implements RequestManager {
    private List<ElevatorRequest> requests = new LinkedList<>();

    @Override
    public void removeRequest(ElevatorRequest request) {
        requests.remove(request);
    }

    @Override
    public List<ElevatorRequest> getRequests() {
        return requests;
    }

    @Override
    public void addRequest(ElevatorRequest request) {
        requests.add(request);
    }
}
