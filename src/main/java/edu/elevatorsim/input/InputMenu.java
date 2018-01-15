/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.input;

import edu.elevatorsim.model.elevator.Dispatcher;
import edu.elevatorsim.output.OutputDraw;
import java.io.Console;
import java.io.IOException;
import java.util.Random;


public class InputMenu {
    
    private Console console;
    private Constructor constructor;
    private OutputDraw output;
    
    private int floorCount;
    private int elevatorCount;
    
    public InputMenu(Console console){
        this.console = console;
        output = new OutputDraw(console);
    }
    
    public Constructor getConstructor(){
        return constructor;
    }
    
    private boolean addBuildingParameters(){
        int choice;
        clrscr();
        boolean flag = true;
        do {
            console.printf("House generation!\n");
            console.printf("1.Random enter;\n");
            console.printf("2.Manual enter.\n");
            choice = Integer.parseInt(console.readLine().trim());
            if (choice != 1 && choice != 2){
                clrscr();
            } else{
                flag = false;
            }    
        } while (flag);
        constructor = new Constructor();
        clrscr();
        switch(choice){
            case 1: 
                randomBuilding();
                break;
            case 2: 
                console.printf("Enter floor count: ");
                int fCount = Integer.parseInt(console.readLine().trim());
                this.floorCount = fCount;
                console.printf("Enter elevator count: ");
                int eCount = Integer.parseInt(console.readLine().trim());
                this.elevatorCount = eCount;
                constructor.build(fCount, eCount);
                break;
        }
        return true;
    }
    
    private boolean addPassengersParameters(){
        int choice;
        boolean flag = true;
        clrscr();
        do {
            console.printf("Add passengers to house!\n");
            console.printf("1.Random enter;\n");
            console.printf("2.Manual enter.\n");
            choice = Integer.parseInt(console.readLine().trim());
            if (choice != 1 && choice != 2){
                clrscr();
            } else{
                flag = false;
            }  
        } while (flag);
        clrscr();
        addPassengers(choice);
        return true;
    }
    
    private boolean inputEnd(){
        int choice;
        boolean flag = true;
        clrscr();
        do {
            console.printf("1.Add new passengers;\n");
            console.printf("2.Start simulation.\n");
            choice = Integer.parseInt(console.readLine());
            if (choice != 1 && choice != 2){
                clrscr();
            } else{
                flag = false;
            }
        } while (flag);
        clrscr();
        switch (choice){
            case 1:
                while (true){
                    boolean b = addPassengers(2); 
                    if (b){
                        break;
                    }
                }
                break;
            case 2:
                return false;
        }
        return true;
    }
    
    private boolean addPassengers(int choice){
        switch(choice){
            case 1:
                randomPassengers();
                break;
            case 2:
                int fNum;
                while(true){
                    console.printf("Enter floor number (1-" + String.valueOf(floorCount) + "): ");
                    fNum = Integer.parseInt(console.readLine().trim());
                    if (fNum <= 0 || fNum > floorCount){
                        console.printf("This floor not exist!\n");
                    } else{
                        break;
                    }
                }
                console.printf("Enter passenger count: ");
                int pCount = Integer.parseInt(console.readLine().trim());
                console.printf("\n");
                int[] destinations = new int[pCount];
                for (int i = 0; i < pCount; i++){
                    console.printf("Enter destination floor for " + (i + 1) + " passenger: ");
                    int dest = Integer.parseInt(console.readLine().trim());
                    if (dest <= 0 || dest > floorCount){
                        console.printf("This floor not exist!\n");
                        i--;
                    } else{
                        destinations[i] = dest;
                    }
                }
                constructor.addPassengers(fNum, destinations);
                break;
        }
        return true;
    }
    
    private void randomBuilding(){
        int min = 7;
        int max = 14;
        Random randFloor = new Random();
        Random randElevator = new Random();
        this.floorCount = randFloor.nextInt(((max - min) + 1) + min);
        this.elevatorCount = randElevator.nextInt(2) + 2;
        constructor.build(this.floorCount, this.elevatorCount);
    }
    
    private void randomPassengers(){
        Random rand = new Random();
        for (int i = 0; i < floorCount; i++){
            int passengers = rand.nextInt(10);
            int[] destinations = new int[passengers];
            for (int j = 0; j < destinations.length; j++){
                int dest = rand.nextInt(floorCount) + 1;
                if (dest != i + 1){
                    destinations[j] = dest;
                } else{
                    j--;
                }
            }
            if (passengers != 0){
                constructor.addPassengers(i + 1, destinations);
            }
        }
    }
    
    public void start(){
        addBuildingParameters();
        addPassengersParameters();
        boolean input = true;
        while (input){
            input = inputEnd();
        }
        constructor.getExecutor().startBuilding();
        Dispatcher.startSimulation();
        output.start();
    }
    
    public void clrscr(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}
