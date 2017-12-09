package elfimova.calculator;

/**
 * Class for counting ctg
 * @autor elfimova Luda on 02.12.2017.
 */

public class CountCtg implements ICount{
    private double x;

    /**
     * Constructor - creating a new object with certain value
     * @param x double
     */

    public CountCtg(double x){
        this.x=x;
    }

    /**
     * Counts ctg
     * @return double result
     */

    @Override
    public double count() {
        return 1/Math.tan(x);
    }
}