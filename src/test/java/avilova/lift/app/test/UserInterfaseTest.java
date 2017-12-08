package avilova.lift.app.test;
import avilova.lift.app.ui.UserInterfase;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserInterfaseTest {
    @Test
    public void isDigital() {
        boolean actual = UserInterfase.isDigital("12");
        boolean expected = true;
        assertEquals(actual,expected);
    }

    @Test
    public void isDigital2() {
        boolean actual = UserInterfase.isDigital("fd");
        boolean expected = false;
        assertEquals(actual,expected);
    }

    @Test
    public void isDigital3() {
        boolean actual = UserInterfase.isDigital("0");
        boolean expected = false;
        assertEquals(actual,expected);
    }

    @Test
    public void isDigital4() {
        boolean actual = UserInterfase.isDigital("8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
        boolean expected = false;
        assertEquals(actual,expected);
    }
}