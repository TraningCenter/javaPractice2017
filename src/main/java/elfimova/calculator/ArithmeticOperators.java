package elfimova.calculator;

/**
 * Class for descriptions of arithmetic operations
 * @autor elfimova Luda on 02.12.2017.
 */

public class ArithmeticOperators {
    public static final String OPERATORS = "+-*/";

    /**
     * Checks if the input string is operation (e.g. "-")
     *
     * @param c String
     * @return boolean output
     */

    public static boolean isOperators(String c) {
        switch (c) {
            case "-":
            case "+":
            case "*":
            case "/":
                return true;
        }
        return false;
    }

    /**
     * Returns the priority of operations
     *
     * @param op String operation
     * @return byte
     */

    public static  byte opPrior(String op) {
        switch (op) {

            case "*":
            case "/":
                return 2;
        }
        return 1; // Here there are + and -
    }

    /**
     * Counts the result of the operation (e.g. "+")
     *
     * @param st String operation
     * @param x double first operand
     * @param y duble second operand
     * @return double result
     *
     */

    public static double countOrerat(String st, double y, double x)throws Exception {
        switch (st) {
            case "+":
                return x += y;

            case "-":
                return x -= y;

            case "/":
                if (y==0) {
                    throw new Exception("Invalid operation: division by 0");
                }
                return x /= y;
            case "*":
                return x *= y;

            default:
                throw new Exception("Invalid operation " + st);
        }

    }
}
