package com.eugene.liftProj;

import com.eugene.sys.Sys;

/**
 * Initial application class
 */
public class App {

    /**
     * entry point
     * @param args commad line values
     */
    public static void main(String[] args) {
        System.out.println("Hello! This is a lift project. Let's start!");

        Sys sys = new Sys();
        sys.work();
    }
}
