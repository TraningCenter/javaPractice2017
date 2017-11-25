package com.alegerd;

import com.alegerd.commands.interfaces.ICommand;
import com.alegerd.model.Floor;
import com.alegerd.model.House;
import com.alegerd.model.Lift;
import com.alegerd.model.Person;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.IHouse;
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

    private Integer[][] floorsToDraw;

    public Application(){

    }

    public void start(){
        parser = new Parser();
        view = new Renderer();
        model = parser.parseInputFile("src/main/resources/input");
        floorsToDraw = makeDrawableModel();
        view.drawHouse(floorsToDraw);
    }

    /**
     * Creates twodimensional array of floors and people on them
     * out of model
     * @return array of floors and people
     */
    private Integer[][] makeDrawableModel(){
        Integer[][] floorsToDraw;

        if(model == null)
            throw new NullPointerException("The model is null");
        else{
            Integer numberOfFloors = model.getNumberOfFloors();
            floorsToDraw = new Integer[numberOfFloors][];

            int i = 0;
            Iterator<IFloor> iter = model.floorIterator();

            while (iter.hasNext()){
                IFloor next = iter.next();
                floorsToDraw[i] = new Integer[next.getNumberOfPeople()];

                    int num = 0;
                    Iterator<IPerson> personIterator = next.getPersonIterator();
                    while (personIterator.hasNext()){
                        IPerson nextPerson = personIterator.next();
                        floorsToDraw[i][num] = nextPerson.getDestinationFloor();
                        num++;
                    }

                i++;
            }
        }

        return floorsToDraw;
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
