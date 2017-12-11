package com.netcracker.elevatorSystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Класс для тестирования класса CallElevatorCommand
 */
public class CallElevatorCommandTest {
    Passenger passenger = new Passenger(1);
    CallElevatorCommand command = new CallElevatorCommand(passenger);
    @Test
    public void execute() throws Exception {
        boolean actual = command.execute();
        boolean expected = true;
        assertEquals(actual, expected);
    }

}