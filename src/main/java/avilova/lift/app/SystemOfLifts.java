package avilova.lift.app;

import avilova.lift.app.ui.PrintConsole;
import java.io.Console;
import java.util.ArrayList;

public class SystemOfLifts {

    /**
     * список лифтов
     */
    private ArrayList<Lift> lifts;

    /**
     * этажность
     */
    private int numberOfFloors;

    /**
     * консоль для отрисовки
     */
    private PrintConsole printConsole;

    /**
     * консоль
     */
    Console console;

    /**
     * конструкто
     *
     * @param numberOfFloors-этажность
     * @param lifts-список лифтов
     * @param console-консоль
     */
    public SystemOfLifts(int numberOfFloors, ArrayList<Lift> lifts, Console console) {
        this.numberOfFloors = numberOfFloors;
        this.lifts = lifts;
        printConsole = new PrintConsole(lifts, numberOfFloors, console);
        this.console = console;
    }

    /**
     * запуск движения всех лифтов до тех пор пока все пассажиры не будут
     * доставлены к месту
     */
    public void run() {
        System.out.println("\033[H\033[2J");
        printConsole.show();
        while (!isEnd()) {
            for (Lift lift : lifts)
                if (lift.passengerList.size() != 0) {
                    lift.controller.run();
                }
            printConsole.show();
            System.out.println("\033[H\033[2J");
        }
    }

    /**
     * проверяет конец работы лифта
     *
     * @return true-пассажиров нет, false-пассажиры есть
     */
    public boolean isEnd() {
        for (Lift lift : lifts) {
            if (lift.passengerList.size() != 0) {
                return false;
            }
        }
        return true;
    }
}
