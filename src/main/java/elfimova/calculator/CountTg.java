package elfimova.calculator;

/**
 * Class for counting tg
 * @autor elfimova Luda on 02.12.2017.
 */

public class CountTg implements ICount {
    private double x;

    /**
     * Constructor - creating a new object with certain value
     * @param x double
     */

    public CountTg(double x) {
        this.x = x;
    }

    /**
     * Counts tg
     * @return double result
     */

    @Override
    public double count() {
        return Math.tan(x);
    }
}
