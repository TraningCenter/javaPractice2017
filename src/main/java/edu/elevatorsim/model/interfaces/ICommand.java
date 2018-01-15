/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.interfaces;

import edu.elevatorsim.model.elevator.Elevator;
import edu.elevatorsim.model.floor.Floor;


public interface ICommand {
    public void execute();
    public Elevator getElevator();
    public Floor getFloor();
}
