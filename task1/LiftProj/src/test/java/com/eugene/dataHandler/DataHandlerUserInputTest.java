package com.eugene.dataHandler;

import com.eugene.сontroller.Entities.Button;
import com.eugene.сontroller.Entities.House;
import com.eugene.сontroller.Entities.Lift;
import com.eugene.сontroller.Entities.Passenger;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataHandlerUserInputTest {

    private DataHandlerUserInput dataHandlerUserInput;

    @Before
    public void init() {
        dataHandlerUserInput = new DataHandlerUserInput();
    }

    @Test
    public void verifyInputTest() {
        assertTrue(dataHandlerUserInput.verifyInput(10, 1, 20));
        assertTrue(dataHandlerUserInput.verifyInput(1, 1, 20));
        assertTrue(dataHandlerUserInput.verifyInput(16, 1, 16));
        assertFalse(dataHandlerUserInput.verifyInput(10, 1, 5));
        assertFalse(dataHandlerUserInput.verifyInput(0, 1, 10));
    }

    @Test
    public void getInputTest() throws Exception {
        realizationGetInputTest("1 10 2 1 1 500 1 10 700 2 1 5 8 7");
        realizationGetInputTest("abcd abcd 2 10 1 1 1 500 1 3 1");
        realizationGetInputTest("-100 3 2.5 10 1 1 bnbnbn -1 1 500 2 3 5 6 8");
        realizationGetInputTest("4 7 2 1 1 500 1 6 700 abcd 2 1 2 3 7");
    }

    private void realizationGetInputTest(String s) {
        InputStream stream = new ByteArrayInputStream(s.getBytes());
        dataHandlerUserInput = new DataHandlerUserInput(stream);
        List<Integer> input = stringToListInt(s);
        input = input.stream().filter(i -> (i.compareTo(0) >= 0)).collect(Collectors.toList());
        input.remove(0);
        List<Integer> program = dataHandlerUserInput.getInput();
        assertEquals(input, program);
    }

    @Test
    public void convertDataToHouseTest() {
        //program generating house
        Integer[] sourceData = {10, 2, 1, 1, 500, 1, 10, 700, 2, 1, 2, 8, 7};
        List<Integer> input = new ArrayList<>(Arrays.asList(sourceData));
        House h = dataHandlerUserInput.convertDataToHouse(input);
        //i generating house
        House house = generateHouse();

        assertTrue(house.getNumFloors() == h.getNumFloors());
        assertTrue(house.getLifts().equals(h.getLifts()));
        assertTrue(house.getPassengers().equals(h.getPassengers()));
        assertTrue(house.getButtons().equals(h.getButtons()));
        assertTrue(house.equals(h));
        assertEquals(house, h);
    }

    public static House generateHouse() {
        int numFloors = 10;
        List<Button> buttons = new ArrayList<>();
        for (int i = 0; i < numFloors; i++) {
            if (i != 0)
                buttons.add(new Button(i + 1, -1));
            if (i != numFloors - 1)
                buttons.add(new Button(i + 1, 1));
        }

        List<Lift> lifts = new ArrayList<>();
        lifts.add(new Lift(true, 1, 500));
        lifts.add(new Lift(true, 10, 700));

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(1, 2));
        passengers.add(new Passenger(8, 7));

        return new House(passengers, lifts, buttons, numFloors);
    }

    private List<Integer> stringToListInt(String str) {
        List<Integer> result = new ArrayList<>();
        String[] strArr = str.split(" ");
        for (String s : strArr) {
            Integer i;
            try {
                i = Integer.parseInt(s);
            } catch (Exception e) {
                continue;
            }
            result.add(i);
        }
        return result;
    }

}
