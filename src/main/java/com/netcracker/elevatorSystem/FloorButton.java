package com.netcracker.elevatorSystem;

/**
 * Класс для хранения объектов со свойствами isPressed, directionLabel, floorNumber
 */
public class FloorButton {
    /** Свойство - состояние нажатия кнопки */
    private boolean isPressed;
    /** Свойство - направление на кнопке */
    private Direction directionLabel;
    /** Свойство - этаж, на котором находится кнопка */
    private int floorNumber;

    /**Конструктор вызывается при создании нового объекта и изначально задает состояние кнопки (не нажата) */
    public FloorButton(){
        this.isPressed = false;
    }

    /** Метод для установления состояния кнопки
     * @param isPressed - состояние кнопки */
    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    /** Метод для установления направления на кнопке
     * @param directionLabel - направление */
    public void setDirectionLabel(Direction directionLabel) {
        this.directionLabel = directionLabel;
    }

    /** Метод для получения этажа {@link FloorButton#floorNumber}
     * @return Возвращает номер этажа, на которые вызвн лифт
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /** Метод для установления номера этажа
     * @param floorNumber - номер этажа */
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
