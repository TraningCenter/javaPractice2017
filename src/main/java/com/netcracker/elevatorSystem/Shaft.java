package com.netcracker.elevatorSystem;


/**
 * Класс для хранения объектов со свойствами numberOfFloors, elevator, shaftIndex
 */
public class Shaft {
    public static int numberOfFloors;
    private Elevator elevator;
    private int shaftIndex;

    /**Конструктор вызывается при создании нового объекта и задает начальные значения
     * @param shaftIndex - номер шахты */
    public Shaft(int shaftIndex){
        this.elevator = new Elevator(shaftIndex);
        this.shaftIndex = shaftIndex;
    }

    /** Метод для получения значения поля {@link Shaft#elevator}
     * @return Возвращает лифт, расположенный в шахте
     */
    public Elevator getElevator() {
        return elevator;
    }

    /** Метод для получения значения поля {@link Shaft#shaftIndex}
     * @return Возвращает индекс шахты
     */
    public int getShaftIndex() {
        return shaftIndex;
    }

    /** Метод для установления лифта
     * @param elevator - лифт*/
    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }
}
