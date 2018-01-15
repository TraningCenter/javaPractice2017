/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.interfaces;


public interface IButtonPanel extends Observable{
    
    public boolean isActive(int number);
    public void pushButton(int number);
    public void offButton(int number);
}
