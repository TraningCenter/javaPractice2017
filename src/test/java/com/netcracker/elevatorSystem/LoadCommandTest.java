package com.netcracker.elevatorSystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Класс для тестирования класса LoadCommand
 */
public class LoadCommandTest {

    Elevator elevator = new Elevator(1);
    Passenger passenger = new Passenger(1);
    LoadCommand command = new LoadCommand(elevator, passenger);
    @Test
    public void execute() throws Exception {
        boolean actual = command.execute();
        boolean expected = true;
        assertEquals(actual, expected);
    }

}