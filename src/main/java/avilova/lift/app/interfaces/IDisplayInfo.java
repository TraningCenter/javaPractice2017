package avilova.lift.app.interfaces;

public interface IDisplayInfo {

    /**
     * формирует информацию о лифте
     *
     * @return состояние лифта
     */
    public String showInfo();

    /**
     * выводит на консоль всю информацию о пассажирах лифта
     *
     * @return вся информация о пассажирах
     */
    public String showInfoPassange();
}
