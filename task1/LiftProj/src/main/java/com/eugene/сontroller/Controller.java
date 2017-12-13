package com.eugene.сontroller;

import com.eugene.сontroller.Actions.ActionsHandler;
import com.eugene.сontroller.Entities.*;

import java.util.*;

/**
 * Class for entities control
 * class stores the states of all entities
 */
public class Controller {

    private List<Snapshot> snapshots;
    private ActionsHandler actionsHandler;

    public Controller(House house) {
        snapshots = new LinkedList<>();
        actionsHandler = new ActionsHandler(house, snapshots);
    }

    public List<Snapshot> getHouseStates() {
        return snapshots;
    }

    //Fix all changes in houseStates
    public void buildStates() {
        //add start actions
        actionsHandler.startActions();
        //we process everything
        actionsHandler.handleActions();
    }

}
