package edu.elevatorsim.output;

public class RightFloorTemplate {

    private int level;
    private Coordinates coordinates;
    private int passengersCount;

    public RightFloorTemplate(int level, int passengersCount, int x, int y, int heght, int width){
        this.level = level;
        this.coordinates = new Coordinates(x, y, heght, width);
        this.passengersCount = passengersCount;
    }

    public int getLevel() {
        return level;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public char[][] draw(){
        char[][] floor = new char[coordinates.getHeight()][coordinates.getWidth()];
        String passengers = "["
                + (passengersCount < 10 ? "0" + String.valueOf(passengersCount) : String.valueOf(passengersCount))
                + "]";
        for (int i = 0; i < floor.length; i++){
            for (int j = 0; j < floor[0].length; j++){
                if (i == (floor.length - 2)){
                    if (j < 4){
                        floor[i][j] = passengers.toCharArray()[j];
                    }
                }
                if (i == floor.length - 1){
                    floor[i][j] = '▓';
                }
            }
        }
        return floor;
    }
}
