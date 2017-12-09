package elfimova.calculator;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * @autor elfimova Luda on 03.12.2017.
 * Tests a class Calculate {@link Calculate}
 */
public class CalculateTest {
    @Test
    public void calculate() throws Exception {
        String source = " 6 9 7 + *";
        double expected=96.0;
        Calculate calc= new Calculate();
        double actual = calc.calculate(source);
        assertEquals("Результат", expected, actual, 0.00001);
    }
    @Test
    public void calculateWithFunc() throws Exception {
        String source = " 6 1 sin 7 + *";
        double expected=47.04882590884738;
        Calculate calc= new Calculate();
        double actual = calc.calculate(source);
        assertEquals("Результат", expected, actual, 0.00001);
    }

    @Test(expected = Exception.class)
    public void calculateInvalidExpression() throws Exception {
        String source = " cas";
        Calculate calc= new Calculate();
        double actual = calc.calculate(source);
    }

    @Test(expected = Exception.class)
    public void calculateWithDivisionByZero() throws Exception {
        String source = " 7 3 3 - /";
        Calculate calc= new Calculate();
        double actual = calc.calculate(source);
    }
}