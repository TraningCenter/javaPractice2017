/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.floor;

import edu.elevatorsim.model.elevator.Dispatcher;
import edu.elevatorsim.model.elevator.Request;
import edu.elevatorsim.model.interfaces.IButton;


public class CallButton implements IButton{

    private int level;
    private boolean buttonIsActive;
    private Request request;
    
    public CallButton(int level){
        this.level = level;
    }
    
    @Override
    public int getNumber() {
        return level;
    }

    @Override
    public boolean isActive() {
        return buttonIsActive;
    }

    @Override
    public void pushButton() {
        buttonIsActive = true;
        request = new Request(level);
        Dispatcher.addToCallList(this.request);
    }

    @Override
    public void offButton() {
        buttonIsActive = false;
        Dispatcher.removeFromCallList(request);
    }
    
}
