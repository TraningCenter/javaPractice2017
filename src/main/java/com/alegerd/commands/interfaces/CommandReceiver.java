package com.alegerd.commands.interfaces;

import com.alegerd.mainData.Application;

public class CommandReceiver {

    private static Application app;

    /**
     *
     * @param model Inject Application class to CommandReceiver
     */
    public static void addModel(Application model){
        app = model;
    }

    /**
     * To add new command to Command Queue in the Application class.
     * @param command New command
     * @throws NullPointerException If there is no model injected in CommandReceiver
     */
    public static void addNewCommand(ICommand command) throws NullPointerException{
        if(app == null)
            throw new NullPointerException("Model is not set");
        else {
            app.addNewCommand(command);
        }
    }
}
