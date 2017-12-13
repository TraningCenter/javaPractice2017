package com.eugene.сontroller;

import com.eugene.сontroller.Actions.Action;
import com.eugene.сontroller.Entities.House;

/**
 * Class that keeps the state of the whole house
 */
public class Snapshot {

    private House house;
    private String description;

    public Snapshot(House house, Action lastAction) {
        this.house = new House(house);
        this.description = (lastAction == null ? "" : lastAction.toString());
    }

    public House getHouse() {
        return house;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Snapshot snapshot = (Snapshot) o;

        if (!house.equals(snapshot.house)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = house.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
