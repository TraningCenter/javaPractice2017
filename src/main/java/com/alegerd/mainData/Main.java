package com.alegerd.mainData;
import com.alegerd.utils.Config;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.Console;
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
        Console console = Config.getOutputDevice();
        String newInput = "first";
        boolean exit = false;

        if(args.length > 0) fileName = args[0];

            while (!exit){
                try {
                    switch (newInput) {
                        case "exit":
                            exit = true;
                            break;
                        case "newInput": {
                            console.printf("\nEnter path to input file\n");
                            fileName = console.readLine();
                            fileName = fileName.split("\n")[0];
                            application = new Application(fileName);
                            application.start();                            
                            break;
                        }
                        case "default": {
                            String input = null;
                            application = new Application(input);
                            application.start();
                            break;
                        }
                        case "first":{

                            application = new Application(fileName);
                            application.start();
                            break;
                        }
                        default: {
                            console.printf("\nWrong command\n");
                            fileName = null;
                        }
                    }

                    if(!exit) {
                        console.printf("\nEnter command " +
                                       "\n exit" +
                                       "\n newInput - enter new Input data" +
                                       "\n default - start with default parameters\n");
                        newInput = console.readLine();
                    }

                    }catch(Exception e){
                        console.printf("\nException: " + e.getMessage());
                    }
            }
    }
}
