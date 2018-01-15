/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.output;


public class ElevatorTemplate {
    
    private final char[][] ELEVATOR = {{'└', '-', '┘'},
                                       {'|', ' ', '|'},
                                       {'┌', '-', '┐'}};
    
    private int id;
    private Coordinates coordinates;
    private int passengersCount;
    
    public ElevatorTemplate(int id, int x, int y){
        this.id = id;
        int coordX = x + ELEVATOR[0].length * id;
        this.coordinates = new Coordinates(coordX, y, ELEVATOR.length, ELEVATOR[0].length);
        this.passengersCount = 0;
    }

    public int getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    public void setYCoordinate(int y){
        coordinates.setY(y);
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }
    
    public char[][] draw(){
        char[][] elevator = ELEVATOR;
        elevator[elevator.length - 2][elevator[0].length - 2] = (char)(passengersCount + '0');
        return elevator;
    }
}
