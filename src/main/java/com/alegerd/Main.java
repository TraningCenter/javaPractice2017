package com.alegerd;

public class Main{

    static Application application;

    public static void main(String[] args) {
        String fileName = null;
        if(args.length > 0) fileName = args[0];
        application = new Application();
        application.start(fileName);
    }

}
