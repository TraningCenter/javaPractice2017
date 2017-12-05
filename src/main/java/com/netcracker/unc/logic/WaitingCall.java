package com.netcracker.unc.logic;


/**
 * Helper class, which describes delayed call of elevator,
 * when the passenger refuses to go (or overload of elevator)
 */
public class WaitingCall {
    private Floor startFloor;
    private Floor destFloor;

    public WaitingCall(Floor startFloor, Floor destFloor) {
        this.startFloor = startFloor;
        this.destFloor = destFloor;
    }

    public Floor getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(Floor startFloor) {
        this.startFloor = startFloor;
    }

    public Floor getDestFloor() {
        return destFloor;
    }

    public void setDestFloor(Floor destFloor) {
        this.destFloor = destFloor;
    }

    public State getDirection(){
        return (startFloor.getId() - destFloor.getId()) > 0 ? State.DOWN : State.UP;
    }
}
