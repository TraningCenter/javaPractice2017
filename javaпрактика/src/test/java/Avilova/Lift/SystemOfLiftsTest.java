package Avilova.Lift;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

class SystemOfLiftsTest {
    @Test
    void isEnd() {
        ArrayList<Lift> lifts = new ArrayList<Lift>();
        lifts.add(new Lift(false,1,2,1,12,0,1));
        lifts.add(new Lift(false,1,2,1,12,0,1));
        SystemOfLifts systemOfLifts = new SystemOfLifts(12,lifts, System.console());
        boolean actual = systemOfLifts.isEnd();
        boolean expected = true;
        assertEquals(actual,expected);
    }

    @Test
    void isEnd2() {
        ArrayList<Lift> lifts = new ArrayList<Lift>();
        lifts.add(new Lift(true,1,2,1,12,12,1));
        lifts.add(new Lift(true,1,2,1,12,12,2));
        SystemOfLifts systemOfLifts = new SystemOfLifts(12,lifts, System.console());
        boolean actual = systemOfLifts.isEnd();
        boolean expected = false;
        assertEquals(actual,expected);
    }


}