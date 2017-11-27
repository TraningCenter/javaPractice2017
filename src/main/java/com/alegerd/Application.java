package com.alegerd;

import com.alegerd.commands.interfaces.ICommand;
import com.alegerd.commands.interfaces.PersonCallsLiftCommand;
import com.alegerd.model.Floor;
import com.alegerd.model.House;
import com.alegerd.model.Lift;
import com.alegerd.model.Person;
import com.alegerd.model.buttons.CallLiftButton;
import com.alegerd.model.buttons.ICallLiftButton;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.IHouse;
import com.alegerd.model.interfaces.ILift;
import com.alegerd.model.interfaces.IPerson;
import com.alegerd.tests.FloorTest;
import com.alegerd.view.Parser;
import com.alegerd.view.Renderer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.Console;
import java.util.*;

public class Application {

    private IHouse model;
    private Renderer view;
    private Parser parser;
    private Queue<ICommand> commandQueue;
    private List<IPerson> people;
    private List<IFloor> floors;
    private List<ILift> lifts;

    private String[][] floorsToDraw;
    private String[][] liftsToDraw;

    public Application(){

    }

    public void start(){
        parser = new Parser();
        view = new Renderer();
        commandQueue = new LinkedList<>();

        try {
            model = parser.parseInputFile("src/main/resources/input");

            people = getListOfPeople();
            floors = getListOfFloors();
            lifts = getListOfLifts();

            injectLiftButtonsToFloors();
            injectLiftButtonsToPeople();
            pushFirstCommands();

            while (!commandQueue.isEmpty()){
                ICommand command = commandQueue.poll();
                command.execute();
                updateView();
                Thread.sleep(2000);
                view.clear();
            }
            view.writeMessage("FIN.");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the view
     */
    public void updateView(){
        try{
            floorsToDraw = makeDrawableModel();
            liftsToDraw = makeDrawableLiftModel();
            view.drawHouse(floorsToDraw, liftsToDraw);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Pushes new command ICommand to the command queue
     * @param newCommand new command to push
     */
    public void addNewCommand(ICommand newCommand){
        this.commandQueue.add(newCommand);
    }
    /**
     * Creates twodimensional array of floors and people on them
     * out of model
     * @return array of floors and people
     */
    private String[][] makeDrawableModel(){
        String[][] floorsToDraw;

        if(model == null)
            throw new NullPointerException("The model is null");
        else{
            Integer numberOfFloors = model.getNumberOfFloors();
            floorsToDraw = new String[numberOfFloors][];

            int i = 0;
            Iterator<IFloor> iter = model.floorIterator();

            while (iter.hasNext()){
                IFloor next = iter.next();
                floorsToDraw[i] = new String[next.getNumberOfPeople()];

                    int num = 0;
                    Iterator<IPerson> personIterator = next.getPersonIterator();
                    while (personIterator.hasNext()){
                        IPerson nextPerson = personIterator.next();
                        floorsToDraw[i][num] = nextPerson.getWaitsForLiftNumber() + ":" + nextPerson.getDestinationFloor();
                        num++;
                    }
                i++;
            }
        }

        return floorsToDraw;
    }

    /**
     * Creates twodimensional array of floors and number of people in lifts
     * on them out of model
     * @return array of lift and people
     */
    private String[][] makeDrawableLiftModel(){
        String[][] liftsToDraw;

        if(model == null)
            throw new NullPointerException("The model is null");
        else{
            Integer modelNumberOfFloors = model.getNumberOfFloors();
            liftsToDraw = new String[modelNumberOfFloors][];

            Iterator<ILift> iter = model.liftIterator();

            ArrayList<ArrayList<Integer>> floorList = new ArrayList<>(modelNumberOfFloors);
            for(int i = 0; i < modelNumberOfFloors; i++){
                floorList.add(i, new ArrayList<>());
            }

            while (iter.hasNext()){
                ILift next = iter.next();
                floorList.get(next.getFloorLiftOn()).add(next.getNumberOfPeople());
            }

            for (int i = 0; i < modelNumberOfFloors; i++){
                Integer numberOfLifts = floorList.get(i).size();
                liftsToDraw[i] = new String[numberOfLifts];
                for (int j = 0; j < numberOfLifts; j++){
                    liftsToDraw[i][j] = j + ":" + floorList.get(i).get(j);
                }
            }
        }

        return liftsToDraw;
    }

    private List<IPerson> getListOfPeople(){
        if(model == null) throw new NullPointerException("Model is null");
        else {
            List<IPerson> people = new ArrayList<>();
            Iterator<IFloor> iter = model.floorIterator();
            while (iter.hasNext()) {
                IFloor floor = iter.next();
                Iterator<IPerson> personIterator = floor.getPersonIterator();
                while (personIterator.hasNext()) {
                    people.add(personIterator.next());
                }
            }

            return people;
        }
    }

    private List<ILift> getListOfLifts(){
        if(model == null) throw new NullPointerException("Model is null");
        else {
            List<ILift> lifts = new ArrayList<>();
            Iterator<ILift> iter = model.liftIterator();
            while (iter.hasNext()) {
                lifts.add(iter.next());
            }

            return lifts;
        }
    }

    private List<IFloor> getListOfFloors(){
        if(model == null) throw new NullPointerException("Model is null");
        else {
            List<IFloor> floors = new ArrayList<>();
            Iterator<IFloor> iter = model.floorIterator();
            while (iter.hasNext()) {
                floors.add(iter.next());
            }

            return floors;
        }
    }

    private void injectLiftButtonsToFloors(){

        for (IFloor floor : floors) {
            ArrayList<ICallLiftButton> buttons = new ArrayList<>();
            for (ILift lift : lifts) {
                ICallLiftButton button = new CallLiftButton(lift);
                buttons.add(button);
            }
            floor.acceptLiftButtons(buttons);
        }
    }

    private void injectLiftButtonsToPeople(){
        for (IFloor floor :
                floors) {
            floor.injectLiftButtonsToPeople();
        }
    }

    private void pushFirstCommands(){
        for (IPerson person :
                people) {
            ICommand command = new PersonCallsLiftCommand(person);
            commandQueue.add(command);
        }
    }

    private void test(){
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(FloorTest.class);
        System.out.println("run tests:" + result.getRunCount());
        System.out.println("failed:" + result.getFailureCount());
        System.out.println("ignored:" + result.getIgnoreCount());
        System.out.println("success:" + result.wasSuccessful());
    }
}
