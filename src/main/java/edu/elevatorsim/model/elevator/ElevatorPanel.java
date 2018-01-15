/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.elevator;

import edu.elevatorsim.model.interfaces.IButton;
import edu.elevatorsim.model.interfaces.IButtonPanel;
import edu.elevatorsim.model.interfaces.Observer;
import java.util.ArrayList;


public class ElevatorPanel implements IButtonPanel{

    private Request request;
    private ArrayList<IButton> buttons;
    private ArrayList<Observer> observers;
    
    public ElevatorPanel(int floorCount){
        observers = new ArrayList<>();
        buttons = new ArrayList<>();
        addButtonsOnPanel(floorCount);
    }
    
    private void addButtonsOnPanel(int count){
        for(int i = 0; i < count; i++){
            buttons.add(new Button(i + 1));
        }
    }
    
    @Override
    public boolean isActive(int number) {
        return buttons.get(number - 1).isActive();
    }

    @Override
    public void pushButton(int number) {
        buttons.get(number - 1).pushButton();
        request = new Request(number);
        notifyObserver();
    }

    @Override
    public void offButton(int number) {
        buttons.get(number - 1).offButton();
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer != null){
            observers.add(observer);
        }
    }

    @Override
    public void deleteObserver(Observer observer) {
        if (observer != null){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers){
            observer.update(request);
        }
    }
    
}
