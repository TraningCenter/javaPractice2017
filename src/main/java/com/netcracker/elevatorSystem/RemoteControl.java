package com.netcracker.elevatorSystem;

/**
 * Класс для хранения объектов со свойством command, выступает в роли вызывающего команды
 */
public class RemoteControl {
    /** Свойство - команда */
    private ICommand command;

    /** Метод для установления команды
     * @param command - этаж отправления */
    public void setCommand(ICommand command){
        this.command = command;
    }

    /** Метод для нажатия на кнопку */
    public void pushButton(){
        command.execute();
    }

    /** Метод для загрузки лифта */
    public void load(){
        command.execute();
    }

    /** Метод для движения лифта */
    public void move(){
        command.execute();
    }

    /** Метод для разгрузки лифта */
    public void unload(){
        command.execute();
    }
}
