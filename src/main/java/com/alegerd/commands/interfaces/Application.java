package com.alegerd.commands.interfaces;

import com.alegerd.model.House;
import com.alegerd.tests.FloorTest;
import com.alegerd.view.Parser;
import com.alegerd.view.Renderer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.Queue;

public class Application {

    private House model;
    private Renderer view;
    private Parser parser;
    private Queue<ICommand> commandQueue;

    public Application(){

    }

    public void start(){

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
