package com.netcracker.elevatorSystem;


/**
 * Класс для хранения объектов со свойствами elevator, target, реализующих команду движения лифта
 */
public class MoveCommand implements ICommand {
    /** Свойство - лифт */
    private Elevator elevator;
    /** Свойство - этаж, куда едет лифт */
    private Integer target;

    /**Конструктор вызывается при создании нового объекта с заданными значениями
     * @param elevator - лифт, который должен начать движение
     * @param target - этаж, на который поедет лифт */
    public MoveCommand(Elevator elevator, Integer target){
        this.elevator = elevator;
        this.target = target;
    }

    /** Метод для выполнения команды движения лифта */
    public boolean execute() {
        elevator.moveToNextFloor(target);
        return true;
    }
}
