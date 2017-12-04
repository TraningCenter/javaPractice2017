package Avilova.Lift;

import java.util.ArrayList;
import java.io.Console;

public class SystemOfLifts {
    /**список лифтов*/
    private ArrayList<Lift> lifts;

    /**этажность*/
    private int numberOfFloors;

    /**консоль для отрисовки*/
    private PrintConsole printConsole;

    /**консоль*/
    Console console;

    /**
     * конструкто
     * @param numberOfFloors-этажность
     * @param lifts-список лифтов
     * @param console-консоль
     */
    SystemOfLifts(int numberOfFloors, ArrayList<Lift> lifts, Console console){
       this.numberOfFloors = numberOfFloors;
       this.lifts = lifts;
        printConsole = new PrintConsole(lifts, numberOfFloors, console);
        this.console = console;
    }

    /**
     * запуск движения всех лифтов до тех пор пока все пассажиры не будут доставлены к месту
     */
    public void run(){


        printConsole.show();
        while (!isEnd()) {
            System.out.print("\033[H\033[2J");
            for (Lift lift : lifts)
                if (lift.passengerList.size() != 0)
                    lift.controller.run();
            printConsole.show();
        }

    }

    /**
     * проверяет конец работы лифта
     * @return true-пассажиров нет, false-пассажиры есть
     */
    public boolean isEnd (){
        for (Lift lift : lifts)
            if (lift.passengerList.size() != 0)
                return  false;
        return  true;
    }

}
