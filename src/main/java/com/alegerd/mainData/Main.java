package com.alegerd.mainData;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.ArrayList;
import java.util.List;

public class Main{

    static Application application;

    /**
     * Entry point of this program
     * @param args Path to the input file
     */
    public static void main(String[] args) {
        String fileName = null;
        if(args.length > 0) fileName = args[0];
        try {
            application = new Application(fileName);
            application.start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
