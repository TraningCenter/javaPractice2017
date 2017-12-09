package elfimova.calculator;

/**
 * Class for descriptions of arithmetic functions
 * @autor elfimova Luda on 02.12.2017.
 */

public class ArithmeticFunction {
    public static final String[] FUNCTIONS = {"sin", "cos", "tg", "ctg", "max", "min"};

    /**
     * Checks if the input token is function (e.g. "sin")
     *
     * @param token input String
     * @return boolean output
     *
     */

    static boolean isFunction(String token) {
        for (String item : FUNCTIONS) {
            if (item.equals(token)) {
                return true;
            }
        }
        return false;
    }
}
