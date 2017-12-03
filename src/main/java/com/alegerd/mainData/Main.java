package com.alegerd.mainData;

import com.alegerd.model.Floor;
import com.alegerd.tests.FloorTest;
import com.alegerd.tests.HouseTest;
import com.alegerd.tests.LiftTest;
import com.alegerd.tests.PersonTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Main{

    static Application application;

    /**
     * Entry point of this program
     * @param args Path to the input file
     */
    public static void main(String[] args) {
        String fileName = null;
        if(args.length > 0) fileName = args[0];
        test();
        application = new Application();
        application.start(fileName);
    }

    private static void test(){
        JUnitCore runner = new JUnitCore();

        System.out.println();
        Result result = runner.run(PersonTest.class);
        System.out.println("    Person's tests");
        System.out.println("run tests:" + result.getRunCount());
        System.out.println("failed:" + result.getFailureCount());
        System.out.println("ignored:" + result.getIgnoreCount());
        System.out.println("success:" + result.wasSuccessful());

        System.out.println();
        Result floor = runner.run(FloorTest.class);
        System.out.println("    Floor's tests");
        System.out.println("run tests:" + floor.getRunCount());
        System.out.println("failed:" + floor.getFailureCount());
        System.out.println("ignored:" + floor.getIgnoreCount());
        System.out.println("success:" + floor.wasSuccessful());

        System.out.println();
        Result lift = runner.run(LiftTest.class);
        System.out.println("    Lift's tests");
        System.out.println("run tests:" + lift.getRunCount());
        System.out.println("failed:" + lift.getFailureCount());
        System.out.println("ignored:" + lift.getIgnoreCount());
        System.out.println("success:" + lift.wasSuccessful());

        System.out.println();
        Result house = runner.run(HouseTest.class);
        System.out.println("    House tests");
        System.out.println("run tests:" + house.getRunCount());
        System.out.println("failed:" + house.getFailureCount());
        System.out.println("ignored:" + house.getIgnoreCount());
        System.out.println("success:" + house.wasSuccessful());
    }
}
