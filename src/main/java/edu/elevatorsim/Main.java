/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim;

import edu.elevatorsim.input.InputMenu;
import java.io.Console;

public class Main {
    static Console console;
    
    public static void main(String[] args) throws Exception{
        console = System.console();
        InputMenu in = new InputMenu(console);
        in.start();
    }
}
