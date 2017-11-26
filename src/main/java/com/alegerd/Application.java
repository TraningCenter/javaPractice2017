package com.alegerd;

import com.alegerd.commands.interfaces.ICommand;
import com.alegerd.model.Floor;
import com.alegerd.model.House;
import com.alegerd.model.Lift;
import com.alegerd.model.Person;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class Application {

    private IHouse model;
    private Renderer view;
    private Parser parser;
    private Queue<ICommand> commandQueue;

    private String[][] floorsToDraw;
    private String[][] liftsToDraw;

    public Application(){

    }

    public void start(){
        parser = new Parser();
        view = new Renderer();
        try {
            model = parser.parseInputFile("src/main/resources/input");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            floorsToDraw = makeDrawableModel();
            liftsToDraw = makeDrawableLiftModel();
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        view.drawHouse(floorsToDraw, liftsToDraw);
        //view.outputData(floorsToDraw, liftsToDraw);
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

    private void test(){
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(FloorTest.class);
        System.out.println("run tests:" + result.getRunCount());
        System.out.println("failed:" + result.getFailureCount());
        System.out.println("ignored:" + result.getIgnoreCount());
        System.out.println("success:" + result.wasSuccessful());
    }
}
