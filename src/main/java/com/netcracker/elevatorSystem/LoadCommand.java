package com.netcracker.elevatorSystem;


/**
 * Класс для хранения объектов со свойствами elevator, passenger, реализующих команду загрузки лифта
 */
public class LoadCommand implements ICommand {
    /** Свойство - лифт */
    private Elevator elevator;
    /** Свойство - пассажир */
    private Passenger passenger;

    /**Конструктор вызывается при создании нового объекта с заданными значениями
     * @param elevator - лифт, в который происходит загрузка
     * @param passenger - пассажир, который заходит в лифт */
    public LoadCommand(Elevator elevator, Passenger passenger){
        this.elevator = elevator;
        this.passenger = passenger;
    }

    /** Метод для выполнения команды загрузки лифта */
    public boolean execute() {
        elevator.load(passenger);
        return true;
    }
}
