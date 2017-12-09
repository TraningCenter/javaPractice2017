package elfimova.calculator;

/**
 * Class for counting max
 * @autor elfimova Luda on 02.12.2017.
 */

public class CountMax implements ICount{
    private double x;
    private double y;

    /**
     * Constructor - creating a new object with certain values
     * @param x double
     * @param y double
     */

    public CountMax(double x, double y){
        this.x=x;
        this.y=y;
    }

    /**
     * Counts max
     * @return double result
     */

    @Override
    public double count() {
        return Math.max(x,y);
    }
}
