/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.output;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class FrameBuffer {
    
    private final String FILE = "Temp";
    
    private File file;
    private BufferedWriter writer;
    private Writer out;
    private Scanner scanner;
    
    public FrameBuffer(){
        file = new File(FILE);
    }
    
    public void write(char[][] frame){
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            out = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8);
            writer = new BufferedWriter(out);
            for (int i = 0; i < frame.length; i++){
                String tmp = "";
                for (int j = 0; j < frame[0].length; j++){
                    tmp += frame[i][j];
                }
                writer.write(tmp);
                writer.newLine();
            }
            writer.write('_');
            writer.newLine();
            writer.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    public ArrayList<String> read(){
        ArrayList<String> frames = new ArrayList<>();
        try{
            scanner = new Scanner(file, "UTF-8");
            while (scanner.hasNext()){
                frames.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        file.delete();
        return frames;
    }
}
