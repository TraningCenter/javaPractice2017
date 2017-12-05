package com.alegerd.view;

import com.alegerd.mainData.ViewController;
import com.alegerd.utils.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Builds House in console out of JSON
 */
public class Renderer {

    Integer sectionSize = 10;
    /**
     * Draws house to console
     * @param floorsToDraw twodimentional array of floors and people
     */
    public void drawHouse(String[][] floorsToDraw, String[][] liftsToDraw, Integer numberOfSections) throws Exception{

        if(!Config.isStopDrawing()) {
            Integer additionalCells = countAdditionalCells(floorsToDraw);

            if (floorsToDraw == null)
                throw new IllegalArgumentException("floors array is null");
            else {
                for (int row = floorsToDraw.length - 1; row >= 0; row--) {

                    for (int section = 0; section < numberOfSections; section++) {

                        System.out.print("   ");

                        for (int j = 0; j < (sectionSize + additionalCells); j++) {
                            System.out.print("=");
                        }
                    }

                    System.out.println();

                    for (int section = 0; section < numberOfSections; section++) {

                        Integer lift = null;

                        for (String s :
                                liftsToDraw[row]) {
                            String[] line = s.split(":");
                            Integer sect = Integer.parseInt(line[0]);
                            if (sect == section) lift = Integer.parseInt(line[1]);
                        }

                        if (lift == null)
                            System.out.print("   ");
                        else
                            System.out.print("|" + lift + "|");

                        Integer peopleCount = 0;
                        for (String col :
                                floorsToDraw[row]) {
                            String[] line = col.split(":");
                            Integer sect = Integer.parseInt(line[0]);
                            if (sect == section) {
                                peopleCount++;
                                System.out.print(Integer.parseInt(line[1]) + 1);
                            }
                        }
                        Integer count = sectionSize + additionalCells - peopleCount;
                        for (int i = 0; i < count; i++) {
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    public void clear(){
        if(!Config.isStopDrawing()) {
            Integer num = ViewController.getNumberOfFloors() * 2 + 10;
            for (int i = 0; i < num; i++) {
                System.out.println();
            }
        }
    }

    public void writeMessage(String message){
        if(!Config.isStopDrawing()) {
            System.out.println(message);
        }
    }

    private Integer countAdditionalCells(String[][] floors){
        Integer maxValue = sectionSize;
        for (String[] floor : floors) {
            Integer value = 0;
            for(int i = 0; i < floor.length; i++){
                value ++;
            }
            if(maxValue < value) maxValue = value;
        }
        return (maxValue - sectionSize) > 0?maxValue-sectionSize:0;
    }
}
