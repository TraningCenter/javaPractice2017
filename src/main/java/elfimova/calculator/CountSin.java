package elfimova.calculator;

/**
 * Class for counting sin
 * @autor elfimova Luda on 02.12.2017.
 */

public class CountSin implements ICount{
    private double x;

    /**
     * Constructor - creating a new object with certain value
     * @param x double
     */

    public CountSin(double x){
        this.x=x;
    }

    /**
     * Counts sin
     * @return double result
     */

    @Override
    public double count() {
        return Math.sin(x);
    }
}
