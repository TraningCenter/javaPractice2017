/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.elevator;

import edu.elevatorsim.model.interfaces.IButton;


public class Button implements IButton{

    private int number;
    private boolean buttonIsActive;
    
    public Button(int number){
        this.number = number;
    }
    
    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public boolean isActive() {
        return buttonIsActive;
    }

    @Override
    public void pushButton() {
        this.buttonIsActive = true;
    }

    @Override
    public void offButton() {
        this.buttonIsActive = false;
    }
    
}
