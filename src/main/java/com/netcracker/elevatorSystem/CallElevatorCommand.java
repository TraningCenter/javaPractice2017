package com.netcracker.elevatorSystem;

/**
 * Класс с полем passenger для хранения объектов, которые выполненяют команду вызова лифта
 */
public class CallElevatorCommand implements ICommand {
    private Passenger passenger;
    /**Конструктор вызывается при создании нового объекта с заданным значением
     * @param passenger - пассажир, который вызывает лифт */
    public CallElevatorCommand(Passenger passenger){
        this.passenger = passenger;
    }

    /** Метод для вызова лифта */
    public boolean execute() {
        passenger.pushFloorButton();
        return true;
    }


}
