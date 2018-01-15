/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import edu.elevatorsim.model.building.Building;
import edu.elevatorsim.model.elevator.Direction;
import edu.elevatorsim.model.elevator.Elevator;
import edu.elevatorsim.model.floor.Floor;
import org.junit.Assert;
import org.junit.Test;


public class BuildingTest{
    
    @Test
    public void testLift(){
        int floorCount = 10;
        int elevatorCount = 3;
        Building building = new Building(floorCount, elevatorCount);
        int i = 0;
        for (Floor floor : building.getFloors()){
            i++;
            Assert.assertEquals(i, floor.getLevel());
        }
        i = 0;
        for (Elevator elevator : building.getElevators()){
            elevator.setCurrentFloor(i + 1);
            elevator.setNextFloor(i + 3);
            Assert.assertEquals(i, elevator.getId());
            Assert.assertEquals(i + 1, elevator.getCurrentFloor());
            Assert.assertEquals(i + 3, elevator.getNextFloor());
            i++;
        }
        Assert.assertEquals(floorCount, building.getFloorCount());
        Assert.assertEquals(elevatorCount, building.getElevatorCount());
        Assert.assertEquals(floorCount, building.getFloors().size());
        Assert.assertEquals(elevatorCount, building.getElevators().size());
    }
}
