package com.netcracker.unc.visualization.elements;


/**
 * Class which visualizes elevator with passengers, open and close doors
 */
public class ElevatorPicture implements IPicture {

    private int id;
    private int yCoordinate;
    private int countOfPassengers;
    private boolean isOpened;

    public ElevatorPicture(int id, int yCoordinate, int countOfPassengers, boolean isOpened) {
        this.id = id;
        this.yCoordinate = yCoordinate;
        this.countOfPassengers = countOfPassengers;
        this.isOpened = isOpened;
    }

    public String draw(int line) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| ");
        switch (line - yCoordinate) {
            case 0:
                stringBuilder.append("|¯¯¯|");
                break;
            case 1:
                if (isOpened) {
                    stringBuilder.append("|<").append(countOfPassengers).append(">|");
                } else {
                    stringBuilder.append("| ").append(countOfPassengers).append(" |");
                }
                break;
            case 2:
                stringBuilder.append("|___|");
                break;
            default:
                stringBuilder.append("     ");
        }
        stringBuilder.append(" |");
        return stringBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getCountOfPassengers() {
        return countOfPassengers;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setCountOfPassengers(int countOfPassengers) {
        this.countOfPassengers = countOfPassengers;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }


}
