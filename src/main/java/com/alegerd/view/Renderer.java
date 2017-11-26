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
    public void outputData(Integer[][] floorsToDraw, Integer[][] liftsToDraw){
        if(floorsToDraw == null || liftsToDraw == null)
            throw new IllegalArgumentException("argument is null");
        else {
            for (int row = 0; row < floorsToDraw.length; row++) {
                System.out.print(row + " floor: ");
                for (Integer col : floorsToDraw[row])
                    System.out.print(col.toString() + ", ");
                System.out.println();
            }

            for (int row = 0; row < liftsToDraw.length; row++) {
                System.out.print(row + " lift: ");
                for (Integer col : liftsToDraw[row])
                    System.out.print(col.toString() + ", ");
                System.out.println();
            }
        }
    }
}
