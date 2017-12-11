package com.netcracker.elevatorSystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Класс для тестирования класса UnloadCommand
 */
public class UnloadCommandTest {

    Elevator elevator = new Elevator(1);
    Passenger passenger = new Passenger(1);
    UnloadCommand command = new UnloadCommand(elevator, passenger);
    @Test
    public void execute() throws Exception {
        boolean actual = command.execute();
        boolean expected = true;
        assertEquals(actual, expected);
    }

}