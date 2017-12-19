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
        realizationGetInputTest("1 10 2 1 1 3 1 10 3 2 1 5 8 7");
        realizationGetInputTest("abcd abcd 2 10 1 1 1 3 1 3 1");
        realizationGetInputTest("-100 3 2.5 10 1 1 bnbnbn -1 1 2 3 3 9 5 8 6 8");
        realizationGetInputTest("4 10 1 1 5 2 5 1 10 2 1 3 10 9 7 6 8");
        realizationGetInputTest("5 10 3 1 1 2 1 9 2 1 6 2 5 1 10 2 1 3 10 9 7 6 8");
        realizationGetInputTest("6 10 3 1 1 2 1 9 2 1 4 2 5 10 7 1 5 10 5 1 5 1 5");
        realizationGetInputTest("7 7 2 1 1 5 1 6 4 abcd 2 1 2 3 7");
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
        Integer[] sourceData = {10, 2, 1, 1, 5, 1, 10, 5, 2, 1, 2, 8, 7};
        List<Integer> input = new ArrayList<>(Arrays.asList(sourceData));
        House h = dataHandlerUserInput.convertDataToHouse(input);
        //i generating house
        House house = staticGenerateHouse();

        assertTrue(house.getNumFloors() == h.getNumFloors());
        assertTrue(house.getLifts().equals(h.getLifts()));
        assertTrue(house.getPassengers().equals(h.getPassengers()));
        assertTrue(house.getButtons().equals(h.getButtons()));
        assertTrue(house.equals(h));
        assertEquals(house, h);
    }

    public static House staticGenerateHouse() {
        int numFloors = 10;
        List<Button> buttons = new ArrayList<>();
        for (int i = 0; i < numFloors; i++) {
            if (i != 0)
                buttons.add(new Button(i + 1, -1));
            if (i != numFloors - 1)
                buttons.add(new Button(i + 1, 1));
        }

        List<Lift> lifts = new ArrayList<>();
        lifts.add(new Lift(true, 1, 5));
        lifts.add(new Lift(true, 10, 5));

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(1, 2));
        passengers.add(new Passenger(8, 7));

        return new House(passengers, lifts, buttons, numFloors);
    }

    public static House smartGenerateHouse(List<Integer> correctData) {
        int num = 0;
        int numFloors = correctData.get(num++);
        List<Button> buttons=new ArrayList<>();
        for (int i = 0; i < numFloors; i++) {
            if (i != 0)
                buttons.add(new Button(i + 1, -1));
            if (i != numFloors - 1)
                buttons.add(new Button(i + 1, 1));
        }
        int numLifts = correctData.get(num++);
        List<Lift> lifts = new ArrayList<>();
        for (int i = 0; i < numLifts; i++) {
            lifts.add(new Lift((correctData.get(num++) != 0), correctData.get(num++), correctData.get(num++)));
        }
        int numPassengers = correctData.get(num++);
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < numPassengers; i++) {
            passengers.add(new Passenger(correctData.get(num++), correctData.get(num++)));
        }
        return new House(passengers, lifts, buttons, numFloors);
    }

    public static List<Integer> stringToListInt(String str) {
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
