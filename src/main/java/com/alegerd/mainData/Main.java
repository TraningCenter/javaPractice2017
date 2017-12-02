package com.alegerd.mainData;

public class Main{

    static Application application;

    /**
     * Entry point of this program
     * @param args Path to the input file
     */
    public static void main(String[] args) {
        String fileName = null;
        if(args.length > 0) fileName = args[0];
        application = new Application();
        application.start(fileName);
    }

}
