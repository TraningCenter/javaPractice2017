package elfimova.calculator;

/**
 * Class for counting min
 * @autor elfimova Luda on 02.12.2017.
 */

public class CountMin implements ICount{
    private double x;
    private double y;

    /**
     * Constructor - creating a new object with certain values
     * @param x double
     * @param y double
     */

    public CountMin(double x, double y){
        this.x=x;
        this.y=y;
    }

    /**
     * Counts min
     * @return double result
     */

    @Override
    public double count() {
        return Math.min(x,y);
    }
}
