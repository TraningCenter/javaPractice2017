package Avilova.Lift;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class UserInterfaseTest {
    @Test
    void isDigital() {
        boolean actual = UserInterfase.isDigital("12");
        boolean expected = true;
        assertEquals(actual,expected);
    }

    @Test
    void isDigital2() {
        boolean actual = UserInterfase.isDigital("fd");
        boolean expected = false;
        assertEquals(actual,expected);
    }

    @Test
    void isDigital3() {
        boolean actual = UserInterfase.isDigital("0");
        boolean expected = false;
        assertEquals(actual,expected);
    }

    @Test
    void isDigital4() {
        boolean actual = UserInterfase.isDigital("8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
        boolean expected = false;
        assertEquals(actual,expected);
    }
}