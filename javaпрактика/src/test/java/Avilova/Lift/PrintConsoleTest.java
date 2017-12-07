package Avilova.Lift;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrintConsoleTest {

    @Test
    void createMatr() {
        ArrayList<Lift> lifts = new ArrayList<Lift>();
        lifts.add(new Lift(false,1,2,1,12,0,1));
        lifts.add(new Lift(false,1,2,1,12,0,1));
        PrintConsole printConsole = new PrintConsole(lifts,12, System.console());
        boolean actual = printConsole.createMatr();
        boolean expected = true;
        assertEquals(actual,expected);
    }
}