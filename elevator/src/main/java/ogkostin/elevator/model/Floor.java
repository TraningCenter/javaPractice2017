package ogkostin.elevator.model;

/**
 * Provides data  of the floor
 *
 *  @author Oleg Kostin
 */

public class Floor {

    private int height;
    private int number;
    private int width;
    private int personsCount = 0;
    private int completedPersonsCount = 0;

    public int getCompletedPersonsCount() {
        return completedPersonsCount;
    }

    public void setCompletedPersonsCount(int completedPersonsCount) {
        this.completedPersonsCount = completedPersonsCount;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPersonsCount() {
        return personsCount;
    }

    public void setPersonsCount(int personsCount) {
        this.personsCount = personsCount;
    }

}
