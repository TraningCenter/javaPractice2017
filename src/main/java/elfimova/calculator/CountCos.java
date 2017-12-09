package elfimova.calculator;

/**
 * Class for counting cos
 * @autor elfimova Luda on 02.12.2017.
 */
public class CountCos implements ICount{
    private double x;

    /**
     * Constructor - creating a new object with certain value
     * @param x double
     */

    public CountCos(double x){
        this.x=x;
    }

    /**
     * Counts cos
     * @return double result
     */

    @Override
    public double count() {
        return Math.cos(x);
    }
}

