package tadite.javase.elevatorSimulator.model.elevator;

import tadite.javase.elevatorSimulator.model.misc.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ElevatorController implements IndoorTransport{

    private ElevatorRequest targetRequest;
    private ElevatorConfig config;
    private ElevatorMechanism elevatorMechanism;
    private ElevatorTargetCalculationStrategy targetCalculationStrategy;
    private Map<Integer, ElevatorDoorMechanism> doorMechanisms;
    private RequestManager requestManager;

    private List<Location> usedLocations = new ArrayList<>();

    public  ElevatorController(ElevatorConfig config,
                              ElevatorMechanism elevatorMechanism,
                              ElevatorTargetCalculationStrategy targetCalculationStrategy,
                              Map<Integer, ElevatorDoorMechanism> doorMechanisms,
                              RequestManager requestManager) {
        this.config = config;
        setUsedLocations(config);
        this.elevatorMechanism = elevatorMechanism;
        this.targetCalculationStrategy = targetCalculationStrategy;
        this.doorMechanisms = doorMechanisms;
        this.requestManager = requestManager;
    }

    private void setUsedLocations(ElevatorConfig config) {
        for (int level = config.getMinLevel();level<=config.getMaxLevel();level++)
            usedLocations.add(new Location(config.getPosition(),level));

    }

    @Override
    public void updateState() {
        if (targetRequest==null){
            tryGetTargetRequest();
            if (targetRequest==null)
                return;
        }

        if (onTargetLevel())
            openDoorAndCompleteRequest();
        else
            moveElevatorMechanism();
    }

    private void tryGetTargetRequest() {
        targetRequest = targetCalculationStrategy.calculateTargetLevel(requestManager.getRequests());
    }

    private void openDoorAndCompleteRequest() {
        doorMechanisms.get(targetRequest.getLevel()).openDoor();
        elevatorMechanism.onDoorOpened();
        doorMechanisms.get(targetRequest.getLevel()).closeDoor();
        targetRequestCompleted();
    }

    private boolean onTargetLevel() {
        return targetRequest.getLevel().equals(getLocation().getLevel());
    }

    private void moveElevatorMechanism() {
        if (targetRequest.getLevel()>getLocation().getLevel()){
            elevatorMechanism.moveUp();
        } else if (targetRequest.getLevel()<getLocation().getLevel()){
            elevatorMechanism.moveDown();
        }
    }

    private void targetRequestCompleted(){
        requestManager.removeRequest(targetRequest);
        targetRequest=null;
    }

    @Override
    public Location getLocation() {
        return new Location(config.getPosition(),elevatorMechanism.getLevel());
    }

    @Override
    public IndoorTransportType getType() {
        return IndoorTransportType.ELEVATOR;
    }

    @Override
    public Iterator<Location> getUsedLocations() {
        return usedLocations.iterator();
    }
}
