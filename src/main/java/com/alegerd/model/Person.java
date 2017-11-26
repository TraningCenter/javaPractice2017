package com.alegerd.model;

import com.alegerd.model.buttons.IButton;
import com.alegerd.model.interfaces.IPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * Person
 */
public class Person implements IPerson{
    private Integer id;

    private Integer floorNumber;

    private Integer waitsForLiftNumber;

    private Integer destinationFloor;

    public boolean inLift = false;

    private Integer weight;

    public Person(Integer id, Integer weight, Integer floorNumber,
                  Integer destinationFloor){
        this.id = id;
        this.weight = weight;
        this.floorNumber = floorNumber;
        this.destinationFloor = destinationFloor;
        this.waitsForLiftNumber = 0;
    }

    public Person(Integer id, Integer weight, Integer floorNumber,
                  Integer destinationFloor, Integer waitsForLiftNumber){
        this.id = id;
        this.weight = weight;
        this.floorNumber = floorNumber;
        this.destinationFloor = destinationFloor;
        this.waitsForLiftNumber = waitsForLiftNumber;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(Integer destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWaitsForLiftNumber() {
        return waitsForLiftNumber;
    }

    public void setWaitsForLiftNumber(Integer waitsForLiftNumber) {
        this.waitsForLiftNumber = waitsForLiftNumber;
    }
}
