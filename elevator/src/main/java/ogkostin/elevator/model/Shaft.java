package ogkostin.elevator.model;
/**
 * Provides data  of the shaft
 *
 *  @author Oleg Kostin
 */
public class Shaft {

    private int leftPadding;
    private int height;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLeftPadding() {
        return leftPadding;
    }

    public void setLeftPadding(int leftPadding) {
        this.leftPadding = leftPadding;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
