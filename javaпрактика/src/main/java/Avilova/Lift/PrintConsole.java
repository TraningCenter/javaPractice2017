package Avilova.Lift;

import java.io.Console;
import java.util.ArrayList;


public class PrintConsole {

    /**список лифтов*/
    private ArrayList<Lift> lifts;

    /**матрица отрисовки*/
    private String [][] matr;

    /**этажность*/
    private int numberOfFloors;

    /**инфомационная строка*/
    private String strinfo;

    /**панель*/
    private Panel panel;

    /**консоль*/
    private Console console;

    /**
     * конструктор
     * @param lifts-список лифтов
     * @param numberOfFloors-этажность
     * @param console-консоль
     */
    PrintConsole(ArrayList<Lift> lifts, int numberOfFloors, Console console){
        this.lifts = lifts;
        this.numberOfFloors = numberOfFloors;
        strinfo = "";
        matr = new String[2 * numberOfFloors + 1][7 + (lifts.size() - 1) * 4];
        createMatr();
        this.console = console;
        panel = new Panel(matr, numberOfFloors, lifts, this.console);
    }

    /**
     * заполнение матрицы отрисовки
     */
    private void createMatr() {

        for (int i = 0; i <= 2 * numberOfFloors; i++)
            for (int j = 0; j <= 6 + (lifts.size() - 1) * 4; j++)
                matr[i][j] = "   ";

        // рисуем "-"
        for (int i = 0; i <= 2 * numberOfFloors; i += 2)
            for (int j = 3; j < 6 + (lifts.size() - 1) * 4; j++)
                matr[i][j] = "---";

        // рисуем "|"
        for (int i = 1; i < 2 * numberOfFloors; i++) {
            matr[i][0] = " | ";
            matr[i][2] = " | ";
            matr[i][6 + (lifts.size() - 1) * 4] = " | ";
        }

        // рисуем номер лифта
        for (int i = 1, j = 0; i < 2 * numberOfFloors; i += 2, j++)
            if (numberOfFloors - j <= 9)
                matr[i][1] = Integer.toString(numberOfFloors - j) + "  ";
            else
                matr[i][1] = Integer.toString(numberOfFloors - j)+ " ";

        for (int i = 1; i <= 2 * numberOfFloors - 1; i += 2)
            for (int j = 0; j < lifts.size(); j++)
                matr[i][6 + 4 * j] = " | ";
        ;
    }


    /**
     * вызов методов отображение матрицы отрисовки, информации о лифтах и пассажирах
     */
    public void show(){

        panel.showGraphics();
        printMatr();
        panel.showInfo();
        ShowInfoPassange();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return;
        }
    }

    /**
     * отображение матрицы в консоль
     */
    private void printMatr(){
        for (int i = 0; i <= 2 * numberOfFloors; i ++) {
            for (int j = 0; j <= 6 + (lifts.size() - 1) * 4; j++)
                console.printf(matr[i][j]);
            console.printf("\n");
        }
    }

    /**
     *  отображение информации о пассажирах в консоль
     */
    public void ShowInfoPassange(){
        panel.showInfoPassange();
    }
}
