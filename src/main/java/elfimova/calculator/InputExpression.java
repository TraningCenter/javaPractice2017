package elfimova.calculator;

import java.util.Scanner;

/**
 * Class to enter an arithmetic expression
 * @autor elfimova Luda on 02.12.2017.
 */

public class InputExpression {

    /**
     * Offers the user to enter an arithmetic expression
     * Read the string from the standard input stream
     * @return string
     *
     */

    public static String input() {
        Scanner s=new Scanner(System.in);
        System.out.println("Arithmetic expression calculator.");
        System.out.println("Numbers are supported, when writing real numbers, the integer part is separated from the fractional point,");
        System.out.println("operations are supported +,-,*,/, function min, max, cos, sin, tg, ctg and priorities in the form of parentheses( ).");
        System.out.println("Enter an arithmetic expression to calculate: ");
        String string=s.nextLine();
        System.out.println();
        return string;

    }
}
