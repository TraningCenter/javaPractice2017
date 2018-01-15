/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.output;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class OutputDraw {
    
    private Console console;
    private FrameBuffer buffer;
    private ArrayList<String> frames;
    
    public OutputDraw(Console console){
        this.console = console;
        buffer = new FrameBuffer();
    }
    
    public void start(){
        frames = buffer.read();
        Iterator<String> iterator = frames.iterator();
        while (iterator.hasNext()){
            String frame = iterator.next();
            if (frame.equals("_") && iterator.hasNext()){
                try{
                    Thread.sleep(800);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
                clrscr();
            } else{
                console.printf(frame + "\n");
            }
        }
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
