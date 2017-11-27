package com.alegerd.view;

import com.alegerd.ViewController;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds House in console out of JSON
 */
public class Renderer {


    /**
     * Draws house to console
     * @param floorsToDraw twodimentional array of floors and people
     */
    public void drawHouse(String[][] floorsToDraw, String[][] liftsToDraw) throws Exception{

        Integer numberOfLifts = ViewController.getNumberOfLifts();
        Integer numberOfSections = ViewController.getNumberOfSections();
        Integer sectionSize = ViewController.getSizeOfSection();
        Integer maxPeople = ViewController.getNumberOfPeopleInSection();

        if(floorsToDraw == null)
            throw new IllegalArgumentException("floors array is null");
        else {
            for(int row = floorsToDraw.length-1; row >= 0; row--){

                for(int section = 0; section < numberOfSections; section++) {

                    Integer lift = null;

                    for (String s :
                            liftsToDraw[row]) {
                        String[] line = s.split(":");
                        Integer sect = Integer.parseInt(line[0]);
                        if(sect == section) lift = Integer.parseInt(line[1]);
                    }
                        System.out.print("   ");

                    for (int j = 0; j < sectionSize; j++) {
                        System.out.print("=");
                    }
                }

                System.out.println();

                for(int section = 0; section < numberOfSections; section++) {

                    Integer lift = null;

                    for (String s :
                            liftsToDraw[row]) {
                        String[] line = s.split(":");
                        Integer sect = Integer.parseInt(line[0]);
                        if(sect == section) lift = Integer.parseInt(line[1]);
                    }

                    if(lift == null)
                        System.out.print("   ");
                    else
                        System.out.print("|" + lift + "|");

                        Integer peopleCount = 0;
                        for (String col :
                                floorsToDraw[row]) {
                            String[] line = col.split(":");
                            Integer sect = Integer.parseInt(line[0]);
                            if(sect == section){
                                peopleCount++;
                                if(peopleCount > maxPeople) throw new Exception("Too many people in one section");
                                System.out.print(Integer.parseInt(line[1]) + 1);
                            }
                        }
                        Integer count = sectionSize - peopleCount;
                        for (int i = 0; i < count; i++){
                            System.out.print(" ");
                        }
                }
                System.out.println();
            }
        }
    }

    public void clear(){
        Integer num = ViewController.getNumberOfFloors()*2 + 10;
        for (int i = 0; i < num; i++){
            System.out.println();
        }
    }

    public void writeMessage(String message){
        System.out.println(message);
    }
    /**
     * Prints floors array to the console
     */
    public void outputData(String[][] floorsToDraw, String[][] liftsToDraw){
        if(floorsToDraw == null || liftsToDraw == null)
            throw new IllegalArgumentException("argument is null");
        else {
            for (int row = 0; row < floorsToDraw.length; row++) {
                System.out.print(row + " floor: ");
                for (String col : floorsToDraw[row])
                    System.out.print(col.toString() + ", ");
                System.out.println();
            }

            for (int row = 0; row < liftsToDraw.length; row++) {
                System.out.print(row + " lift: ");
                for (String col : liftsToDraw[row])
                    System.out.print(col.toString() + ", ");
                System.out.println();
            }
        }
    }
}
