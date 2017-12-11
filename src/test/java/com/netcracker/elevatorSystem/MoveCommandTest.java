package com.netcracker.elevatorSystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Класс для тестирования класса MoveCommand
 */
public class MoveCommandTest {
    Elevator elevator = new Elevator(1);
    MoveCommand command = new MoveCommand(elevator, 5);
    @Test
    public void execute() throws Exception {
        boolean actual = command.execute();
        boolean expected = true;
        assertEquals(actual, expected);
    }

}