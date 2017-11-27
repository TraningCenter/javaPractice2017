package com.alegerd;

import com.alegerd.commands.interfaces.ICommand;
import com.alegerd.model.House;

public class CommandReceiver {

    private static Application app;

    public static void addModel(Application model){
        app = model;
    }

    public static void addNewCommand(ICommand command) throws NullPointerException{
        if(app == null)
            throw new NullPointerException("Model is not set");
        else {
            app.addNewCommand(command);
        }
    }
}
