package com.alegerd.model;

import com.alegerd.Direction;
import com.alegerd.model.buttons.IButton;
import com.alegerd.model.buttons.ICallLiftButton;
import com.alegerd.model.buttons.LiftButton;
import com.alegerd.model.interfaces.IPerson;

import javax.print.attribute.standard.Destination;
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

    private List<ICallLiftButton> buttonsToCallLift = new ArrayList<>();
    private List<LiftButton> buttonsInLift = new ArrayList<>();

    @Override
    public String toString() {
        return "Person: id: " + id + ", weight: " + weight + ", destination: " + destinationFloor + " floor.\n";
    }

    /**
     * Chooses the right button from
     * buttonsToCallLift and invokes method push() of it
     */
    public void callLift(){
        Direction dir = floorNumber > destinationFloor? Direction.DOWN : Direction.UP;

        buttonsToCallLift.get(waitsForLiftNumber).push(dir);
    }

    /**
     * Chooses floor button from buttonsInLift
     * It depends on the floor they wants to go to.
     */
    public void chooseDestinationFloor(){
        for (IButton button:
             buttonsInLift) {
            if(button.getFloorNumber().equals(destinationFloor)){
                button.push();
                return;
            }
        }
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

    @Override
    public void acceptLiftButtons(List<ICallLiftButton> buttons) {
        this.buttonsToCallLift = buttons;
    }

    @Override
    public void acceptFloorButtons(List<LiftButton> buttons) {
        this.buttonsInLift = buttons;
    }
}
