/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.interfaces;


public interface IButton {
    
    public int getNumber();
    public boolean isActive();
    public void pushButton();
    public void offButton();
}
