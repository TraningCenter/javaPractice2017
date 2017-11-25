package com.alegerd.model;

import com.alegerd.model.buttons.IButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Person
 */
public class Person {
    public Integer id;

    public Integer floorNumber;

    public Integer destinationFloor;

    public boolean inLift = false;

    public Integer weight;

    public Person(Integer id, Integer weight, Integer floorNumber, Integer destinationFloor){
        this.id = id;
        this.weight = weight;
        this.floorNumber = floorNumber;
        this.destinationFloor = destinationFloor;
    }

    private List<IButton> buttonsToCallLift = new ArrayList<>();
    private List<IButton> buttonsInLift = new ArrayList<>();

    @Override
    public String toString() {
        return "Person: id: " + id + ", weight: " + weight + ", destination: " + destinationFloor + " floor.\n";
    }

    /**
     * Chooses the right button from
     * buttonsToCallLift and invokes method push() of it
     */
    public void callLift(){

    }

    /**
     * Chooses floor button from buttonsInLift
     * It depends on the floor he wants to go to.
     */
    public void chooseDestinationFloor(){

    }
}
