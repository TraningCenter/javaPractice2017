package tadite.javase.elevatorSimulator.view;

import tadite.javase.elevatorSimulator.controller.BuildingPrintStrategy;
import tadite.javase.elevatorSimulator.model.building.Building;
import tadite.javase.elevatorSimulator.model.elevator.IndoorTransport;
import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.passenger.Passenger;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class StreamBuildingPrintStrategy implements BuildingPrintStrategy {

    private static final int SLOTSIZE = 4;
    private static final char WHITESPACE = ' ';
    private static final char[][] FLOOR_NORMAL={
            {'-',' ',' ',' '},
            {'-',' ',' ',' '},
            {'-',' ',' ',' '},
            {'-',' ',' ',' '}};
    private static final char[][] FLOOR_LEFT= {
            {'|','|','|','|'},
            {'-',' ',' ',' '},
            {'-',' ',' ',' '},
            {'-',' ',' ',' '}};
    private static final char[][] FLOOR_RIGHT= {
            {'-',' ',' ',' '},
            {'-',' ',' ',' '},
            {'-',' ',' ',' '},
            {'|','|','|','|'}};
    private static final char[][] ELEVATOR_SHAFT= {
            {'|','|','|','|'},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {'|','|','|','|'}};
    private static final char[][] ELEVATOR= {
            {'|','|','|','|'},
            {' ','#','#','#'},
            {' ','#','#','#'},
            {'|','|','|','|'}};
    private static final char[][] PERSON= {
            {'\0','\0','\0','\0'},
            {'\0','+','+','\0'},
            {'\0','+','+','\0'},
            {'\0','\0','\0','\0'}};
    private BuildingPrintGrid grid;
    private PrintStream stream;

    private List<Integer> passengersAtSlots = new LinkedList<>();

    public StreamBuildingPrintStrategy(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void print(Building building) {
        clearOrCreateGrid(building);
        addFloorsOnGrid(building.getFloorIterator());
        addTransportOnGrid(building.getTransportIterator());
        addPersonsOnGrid(building.getPassengerIterator());



        printInConsole();
    }

    private void addPersonsOnGrid(Iterator<Passenger> passengerIterator) {
        while (passengerIterator.hasNext()){
            Passenger passenger = passengerIterator.next();
            grid.addCharsToSlot(passenger.getCurrentLocation().getPosition(),passenger.getCurrentLocation().getLevel(),PERSON);

        }
    }

    private void addTransportOnGrid(Iterator<IndoorTransport> transportIterator) {
        while (transportIterator.hasNext()){
            IndoorTransport transport = transportIterator.next();
            transport.getUsedLocations().forEachRemaining(location -> grid.addCharsToSlot(location.getPosition(),location.getLevel(),ELEVATOR_SHAFT));

            grid.addCharsToSlot(transport.getLocation().getPosition(),transport.getLocation().getLevel(),ELEVATOR);
        }
    }

    private void printInConsole() {
        for (int j = 0; j < grid.getHeight(); j++) {
            for (int i = 0; i < grid.getWidth(); i++) {
                stream.print(grid.getCharGrid()[i][grid.getHeight()-j-1]);
            }
            stream.print("\n");
        }

        for (int i=0;i<grid.getWidth();i+=SLOTSIZE){
            int count=0;

        }
    }

    private void clearOrCreateGrid(Building building) {
        if (grid==null)
            grid = new BuildingPrintGrid(building.getSlotsCount(), building.getFloorsCount(),SLOTSIZE,WHITESPACE);
    }

    private void addFloorsOnGrid(Iterator<Floor> floorIterator){
        while (floorIterator.hasNext()){
            Floor floor = floorIterator.next();
            int slot = 0;
            grid.addCharsToSlot(slot,floor.getLevel(),FLOOR_LEFT);
            while (slot++<floor.getSlotsCount()-2)
                grid.addCharsToSlot(slot,floor.getLevel(),FLOOR_NORMAL);
            grid.addCharsToSlot(slot,floor.getLevel(),FLOOR_RIGHT);
        }
    }
}
