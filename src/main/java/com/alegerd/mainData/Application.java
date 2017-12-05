package com.alegerd.mainData;

import com.alegerd.commands.interfaces.CommandReceiver;
import com.alegerd.commands.interfaces.ICommand;
import com.alegerd.commands.interfaces.PersonCallsLiftCommand;
import com.alegerd.exceptions.ParserException;
import com.alegerd.model.House;
import com.alegerd.model.buttons.CallLiftButton;
import com.alegerd.model.buttons.ICallLiftButton;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.IHouse;
import com.alegerd.model.interfaces.ILift;
import com.alegerd.model.interfaces.IPerson;
import com.alegerd.utils.Config;
import com.alegerd.view.Parser;
import com.alegerd.view.Renderer;

import java.util.*;

public class Application {

    private boolean modelIsBuilt = false;
    private IHouse model;
    private Renderer view;
    private Parser parser;
    private Queue<ICommand> commandQueue;
    private List<IPerson> people;
    private List<IFloor> floors;
    private List<ILift> lifts;

    private String[][] floorsToDraw;
    private String[][] liftsToDraw;

    public Application(String input) throws Exception{
        view = new Renderer();
        try {
            buildModel(input);
        }
        catch (ParserException e){
            view.writeMessage("Error with parsing input file, system will be using local one.");
            model = parser.parseInputFile(null);
        }
        catch (Exception e){
            view.writeMessage(e.getMessage());
            throw e;
        }
    }

    public Application(House input) throws Exception{
        view = new Renderer();
        try {
            buildModel(input);
        }catch (Exception e){
            view.writeMessage(e.getMessage());
            throw e;
        }
    }

    /**
     * Main method of the program
     */
    public void start(){
        if(!modelIsBuilt) throw new NullPointerException("System is not set up");
        try {
            while (!commandQueue.isEmpty()){
                ICommand command = commandQueue.poll();
                command.execute();
                updateView();
                Thread.sleep(Config.getTimeInterval());
                view.clear();
            }
            view.writeMessage(Config.getFinalMessage());
        }
        catch (Exception e){
            view.writeMessage(e.getMessage());
        }
    }

    /**
     * Builds system from input file or from resource file
     * @param input path to input file
     */
    private void buildModel(String input) throws Exception{
        parser = new Parser();
        commandQueue = new LinkedList<>();
        model = parser.parseInputFile(input);

        if(model == null) throw new Exception("Model is not set");
        people = getListOfPeople(model);
        floors = getListOfFloors(model);
        lifts = getListOfLifts(model);

        injectLiftButtonsToFloors(floors, lifts);
        injectLiftButtonsToPeople(floors);
        createCommandReceiver();
        pushFirstCommands();
        modelIsBuilt = true;
    }

    /**
     * Builds system from house
     * @param house house to build system from
     */
    private void buildModel(House house) throws Exception{
        commandQueue = new LinkedList<>();

        model = house;
        if(model == null) throw new Exception("Model is not set");
        people = getListOfPeople(model);
        floors = getListOfFloors(model);
        lifts = getListOfLifts(model);

        injectLiftButtonsToFloors(floors, lifts);
        injectLiftButtonsToPeople(floors);
        createCommandReceiver();
        pushFirstCommands();
        modelIsBuilt = true;
    }

    /**
     * Updates the view
     */
    private void updateView(){
        try{
            floorsToDraw = makeDrawableModel();
            liftsToDraw = makeDrawableLiftModel();
            view.drawHouse(floorsToDraw, liftsToDraw, lifts.size());
        }
        catch (Exception e){
            view.writeMessage(e.getMessage());
        }
    }
    /**
     * Pushes new command ICommand to the command queue
     * @param newCommand new command to push
     */
    public void addNewCommand(ICommand newCommand){
        if(commandQueue==null) throw new NullPointerException("Command queue is not set");
        else {
            this.commandQueue.add(newCommand);
        }
    }
    /**
     * Creates twodimensional array of floors and people on them
     * out of model
     * @return array of floors and people
     */
    private String[][] makeDrawableModel(){
        String[][] floorsToDraw;

        if(model == null)
            throw new NullPointerException("Input exception");
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
            throw new NullPointerException("Input exception");
        else{
            Integer modelNumberOfFloors = model.getNumberOfFloors();
            liftsToDraw = new String[modelNumberOfFloors][];

            Iterator<ILift> iter = model.liftIterator();

            ArrayList<ArrayList<ILift>> floorList = new ArrayList<>(modelNumberOfFloors);
            for(int i = 0; i < modelNumberOfFloors; i++){
                floorList.add(i, new ArrayList<>());
            }

            while (iter.hasNext()){
                ILift next = iter.next();
                floorList.get(next.getFloorLiftOn()).add(next);
            }

            for (int i = 0; i < modelNumberOfFloors; i++){
                Integer numberOfLifts = floorList.get(i).size();
                liftsToDraw[i] = new String[numberOfLifts];
                for (int j = 0; j < numberOfLifts; j++){
                    liftsToDraw[i][j] = floorList.get(i).get(j).getNumber() + ":"
                            + floorList.get(i).get(j).getNumberOfPeople();
                }
            }
        }

        return liftsToDraw;
    }

    private List<IPerson> getListOfPeople(IHouse model){
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

    private List<ILift> getListOfLifts(IHouse model){
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

    private List<IFloor> getListOfFloors(IHouse model){
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

    private void injectLiftButtonsToFloors(List<IFloor> floors, List<ILift> lifts){

        for (IFloor floor : floors) {
            ArrayList<ICallLiftButton> buttons = new ArrayList<>();
            for (ILift lift : lifts) {
                ICallLiftButton button = new CallLiftButton(floor.getNumber(), lift);
                buttons.add(button);
            }
            floor.acceptLiftButtons(buttons);
        }
    }

    private void injectLiftButtonsToPeople(List<IFloor> floors){
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

    private void createCommandReceiver(){
        CommandReceiver.addModel(this);
    }
}
