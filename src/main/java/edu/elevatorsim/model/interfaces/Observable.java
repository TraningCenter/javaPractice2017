/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.interfaces;


public interface Observable {
    
    public void addObserver(Observer observer);
    public void deleteObserver(Observer observer);
    public void notifyObserver();
}
