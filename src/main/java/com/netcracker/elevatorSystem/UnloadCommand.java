package com.netcracker.elevatorSystem;


/**
 * Класс для хранения объектов со свойствами elevator, passenger, реализующих команду разгрузки лифта
 */
public class UnloadCommand implements ICommand {
    /** Свойство - лифт */
    private Elevator elevator;
    /** Свойство - пассажир */
    private Passenger passenger;

    /**Конструктор вызывается при создании нового объекта с заданными значениями
     * @param elevator - лифт, в который происходит разгрузка
     * @param passenger - пассажир, который выходит из лифта */
    public UnloadCommand(Elevator elevator, Passenger passenger){
        this.elevator = elevator;
        this.passenger = passenger;
    }

    /** Метод для выполнения команды разгрузки лифта */
    public boolean execute() {
        elevator.unload(passenger);
        return true;
    }
}
