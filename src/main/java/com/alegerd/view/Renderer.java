package com.alegerd.view;

import com.alegerd.mainData.ViewController;
import com.alegerd.utils.Config;

import java.io.*;
import java.io.Console;

/**
 * Builds House in console out of JSON
 */
public class Renderer {

    Integer sectionSize = 10;
    /**
     * Draws house to console
     * @param floorsToDraw twodimentional array of floors and people
     */
    public void drawHouse(String[][] floorsToDraw, String[][] liftsToDraw, Integer numberOfSections){

        try {
            Console console = System.console();

            if (!Config.isStopDrawing()) {
                Integer additionalCells = countAdditionalCells(floorsToDraw);

                if (floorsToDraw == null)
                    throw new IllegalArgumentException("floors array is null");
                else {
                    for (int row = floorsToDraw.length - 1; row >= 0; row--) {

                        for (int section = 0; section < numberOfSections; section++) {

                            console.printf("   ");

                            for (int j = 0; j < (sectionSize + additionalCells); j++) {
                                console.printf("=");
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
                                console.printf("   ");
                            else
                                console.printf("|" + lift + "|");

                            Integer peopleCount = 0;
                            for (String col :
                                    floorsToDraw[row]) {
                                String[] line = col.split(":");
                                Integer sect = Integer.parseInt(line[0]);
                                if (sect == section) {
                                    peopleCount++;
                                    console.printf("%d", (Integer.parseInt(line[1]) + 1));
                                }
                            }
                            Integer count = sectionSize + additionalCells - peopleCount;
                            for (int i = 0; i < count; i++) {
                                console.printf(" ");
                            }
                        }
                        console.printf("\n");
                    }
                }
            }
        }catch (Exception e){

        }
    }

    public void clear(){
        try {
            Console console = System.console();

            if (!Config.isStopDrawing()) {
                Integer num = ViewController.getNumberOfFloors() * 2 + 10;
                for (int i = 0; i < num; i++) {
                    console.printf("\n");
                }
            }
        }catch (Exception e){

        }
    }

    public void writeMessage(String message){
        try {
            Console console = System.console();

            if (!Config.isStopDrawing()) {
                console.printf(message);
            }
        }catch (Exception e){

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
