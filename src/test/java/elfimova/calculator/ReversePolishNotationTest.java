package elfimova.calculator;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @autor elfimova Luda on 03.12.2017.
 * Tests a class ReversePolishNotation {@link Calculate}
 */
public class ReversePolishNotationTest {

    @Test
    public void getReversePolishNotation() throws Exception {
        String source = "6*(9+7)";
        String expected=" 6 9 7 + *";
        String actual = ReversePolishNotation.getReversePolishNotation(source);
        assertEquals("Строка в ОПН", expected, actual);
    }
}