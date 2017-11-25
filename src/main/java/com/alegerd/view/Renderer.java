package com.alegerd.view;

/**
 * Builds House in console out of JSON
 */
public class Renderer {


    /**
     * Draws house to console
     * @param floorsToDraw twodimentional array of floors and people
     */
    public void drawHouse(Integer[][] floorsToDraw){
        if(floorsToDraw == null)
            throw new IllegalArgumentException("floors array is null");
        else {
            for(int row = floorsToDraw.length-1; row >= 0; row--){
                System.out.println("=======================================");
                for (Integer col :
                        floorsToDraw[row]) {
                    System.out.print(col + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Prints floors array to the console
     */
    public void outputData(Integer[][] floorsToDraw){
        if(floorsToDraw == null)
            throw new IllegalArgumentException("floors array is null");
        else {
            for (int row = 0; row < floorsToDraw.length; row++) {
                System.out.print(row + " floor: ");
                for (Integer col : floorsToDraw[row])
                    System.out.print(col.toString() + ", ");
                System.out.println();
            }
        }
    }
}
