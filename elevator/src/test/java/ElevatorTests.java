import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import tadite.javase.elevatorSimulator.model.misc.Button;
import tadite.javase.elevatorSimulator.model.misc.DefaultButton;
import tadite.javase.elevatorSimulator.model.misc.Location;
import tadite.javase.elevatorSimulator.model.misc.Observer;
import tadite.javase.elevatorSimulator.model.elevator.*;

import java.util.HashMap;
import java.util.Map;

public class ElevatorTests {

    private ElevatorController elevatorController;
    private RequestManager requestManager;
    private DefaultElevator elevator;
    private Map<Integer, ElevatorDoorMechanism> doorMechanisms;

    /*
    ___|E|___ 4
    ___| |___ 3
    ___| |___ 2
    ___| |___ 1
     0  1  2
     */
    @Before
    public void init() {

        Location startElevatorLocation = new Location(1,4);
        ElevatorConfig elevatorConfig = new ElevatorConfig(1,4,1);

        requestManager = new DefaultRequestManager();
        Button insideElevatorButton = new DefaultButton(requestManager);
        elevator = new DefaultElevator(startElevatorLocation,insideElevatorButton);

        ElevatorTargetCalculationStrategy targetCalculationStrategy = new LastJobFirstElevatorTargetCalculationStrategy();

        doorMechanisms = new HashMap<>();
        doorMechanisms.put(1,new DefaultElevatorDoor(
                new Location(1,1),
                elevatorConfig,
                new DefaultButton(requestManager),
                elevator));
        doorMechanisms.put(2,new DefaultElevatorDoor(
                new Location(1,2),
                elevatorConfig,
                new DefaultButton(requestManager),
                elevator));
        doorMechanisms.put(3,new DefaultElevatorDoor(
                new Location(1,3),
                elevatorConfig,
                new DefaultButton(requestManager),
                elevator));
        doorMechanisms.put(4,new DefaultElevatorDoor(
                new Location(1,4),
                elevatorConfig,
                new DefaultButton(requestManager),
                elevator));

        elevatorController = new ElevatorController(
                elevatorConfig,
                elevator,
                targetCalculationStrategy,
                doorMechanisms,
                requestManager);

    }

    @Test
    public void canChooseTargetRequestAndMoveToIt(){
        //Array
        requestManager.addRequest(new ElevatorRequest(1));

        //Act
        elevatorController.updateState();
        elevatorController.updateState();
        elevatorController.updateState();

        //Assert
        Assert.assertEquals(1,elevatorController.getLocation().getLevel());
    }

    class mockObserver implements Observer{
        private int updatedTimes;
        private int updatedOnObservableLevel;
        private boolean doorWasOpened;
        private DefaultElevatorDoor elevatorDoor;
        private DefaultElevator elevator;
        private int observableLevel;

        public mockObserver(DefaultElevatorDoor elevatorDoor,DefaultElevator elevator, int observableLevel){

            this.elevatorDoor = elevatorDoor;
            this.elevator = elevator;
            this.observableLevel = observableLevel;
        }

        @Override
        public void update() {
            updatedTimes++;
            if (observableLevel == elevator.getLocation().getLevel()){
                updatedOnObservableLevel++;
                doorWasOpened = elevatorDoor.getDoorStatus()==ElevatorDoorStatus.DOOR_OPENED;
            }
        }

        public int getUpdatedTimes() {
            return updatedTimes;
        }

        public int getUpdatedOnObservableLevel() {
            return updatedOnObservableLevel;
        }

        public boolean isDoorWasOpened() {
            return doorWasOpened;
        }
    }

    @Test
    public void canOpenDoorAndNotifyObservers(){
        //Array
        requestManager.addRequest(new ElevatorRequest(1));

        DefaultElevatorDoor elevatorDoor = (DefaultElevatorDoor)doorMechanisms.get(1);

        mockObserver mockObserver = new mockObserver(elevatorDoor,elevator,1);
        elevator.addObserver(mockObserver);

        //Act
        elevatorController.updateState();
        elevatorController.updateState();
        elevatorController.updateState();
        elevatorController.updateState();

        //Assert
        Assert.assertEquals(ElevatorDoorStatus.DOOR_CLOSED, elevatorDoor.getDoorStatus());
        Assert.assertEquals(true, mockObserver.isDoorWasOpened());
        Assert.assertEquals(1, mockObserver.getUpdatedOnObservableLevel());
        Assert.assertEquals(1, mockObserver.getUpdatedTimes());
    }

    @Test
    public void canServeMultipleRequests(){
        //Array
        requestManager.addRequest(new ElevatorRequest(1));
        requestManager.addRequest(new ElevatorRequest(2));
        requestManager.addRequest(new ElevatorRequest(4));

        DefaultElevatorDoor elevatorDoorAtLevel1 = (DefaultElevatorDoor)doorMechanisms.get(1);
        DefaultElevatorDoor elevatorDoorAtLevel2 = (DefaultElevatorDoor)doorMechanisms.get(2);
        DefaultElevatorDoor elevatorDoorAtLevel4 = (DefaultElevatorDoor)doorMechanisms.get(4);

        mockObserver mockObserverAtLevel1 = new mockObserver(elevatorDoorAtLevel1,elevator,1);
        mockObserver mockObserverAtLevel2 = new mockObserver(elevatorDoorAtLevel2,elevator,2);
        mockObserver mockObserverAtLevel4 = new mockObserver(elevatorDoorAtLevel4,elevator,4);

        elevator.addObserver(mockObserverAtLevel1);
        elevator.addObserver(mockObserverAtLevel2);
        elevator.addObserver(mockObserverAtLevel4);

        //Act
        for (int i=0;i<10;i++)
            elevatorController.updateState();

        //Assert
        Assert.assertEquals(true, mockObserverAtLevel4.isDoorWasOpened());
        Assert.assertEquals(1, mockObserverAtLevel4.getUpdatedOnObservableLevel());

        Assert.assertEquals(true, mockObserverAtLevel2.isDoorWasOpened());
        Assert.assertEquals(1, mockObserverAtLevel2.getUpdatedOnObservableLevel());

        Assert.assertEquals(true, mockObserverAtLevel1.isDoorWasOpened());
        Assert.assertEquals(1, mockObserverAtLevel1.getUpdatedOnObservableLevel());

        Assert.assertEquals(1,elevator.getLocation().getLevel());

        //Array
        mockObserver mockObserverAtLevel4SecondTime = new mockObserver(elevatorDoorAtLevel4,elevator,4);
        requestManager.addRequest(new ElevatorRequest(4));

        elevator.addObserver(mockObserverAtLevel4SecondTime);

        //Act
        for (int i=0;i<10;i++)
            elevatorController.updateState();

        //Assert
        Assert.assertEquals(true, mockObserverAtLevel4SecondTime.isDoorWasOpened());
        Assert.assertEquals(1, mockObserverAtLevel4SecondTime.getUpdatedOnObservableLevel());

        Assert.assertEquals(4,elevator.getLocation().getLevel());
    }


}
