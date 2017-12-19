package com.eugene.сontroller;

import com.eugene.сontroller.Entities.House;

/**
 * Class that keeps the state of the whole house
 */
public class Snapshot {

    private House house;

    public Snapshot(House house) {
        this.house = new House(house);
    }

    public House getHouse() {
        return house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Snapshot snapshot = (Snapshot) o;

        return house.equals(snapshot.house);
    }

    @Override
    public int hashCode() {
        return house.hashCode();
    }
}
