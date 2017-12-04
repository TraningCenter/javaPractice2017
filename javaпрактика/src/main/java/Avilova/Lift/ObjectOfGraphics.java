package Avilova.Lift;
import java.io.Console;

public class ObjectOfGraphics implements IDisplayGraphics, IDisplayInfo {

    /**лифт*/
    private Lift lift;

    /**матрица отрисовки*/
    private String  [][] matr;

    /**этажность*/
    private int numberOfFloors;

    /**консоль*/
    private Console console;

    /**
     * конструктор объекта отрисовки
     * @param lift-лифт
     * @param numberOfFloors-этажность
     * @param matr-матрица отрисовки
     * @param console-консоль
     */
    ObjectOfGraphics(Lift lift, int numberOfFloors, String [][] matr, Console console){
        this.lift = lift;
        this.numberOfFloors = numberOfFloors;
        this.matr = matr;
        this.console = console;
    }

    /**
     * проходим по всем пассажирам и отображаем их направление на каждом этаже
     * отображаем сам лифт
     */
    public void showGraphics(){
        clearMatrix();

        //проходим по всем пассажирам и отображаем их направление на каждом этаже
        for (int i = lift.getPossibleInitialFloor(); i <= lift.getPossibleFinaleFloor(); i++)
            for (int j = 0; j < lift.passengerList.size(); j++)
                if (lift.passengerList.get(j).getFloorOfDeparture() == i)
                    if (lift.passengerList.get(j).getFloorOfDeparture() - lift.passengerList.get(j).getFloorOfDestination() < 0) //едет вверх
                        matr[2 * numberOfFloors - 2 * i + 1][lift.getNumber() + 3 * (lift.getNumber() + 1)] = " < ";
                    else //едет вниз
                        matr[2 * numberOfFloors - 2 * i + 1][lift.getNumber() + 3 * (lift.getNumber() + 1) +1] = " > ";
        //отображаем сам лифт
        matr[2 * numberOfFloors - 2 * lift.getLocation() + 1][lift.getNumber() + 3 * (lift.getNumber() + 1) + 2] = " # ";
    }

    /**
     * выводит на консоль всю информацию о лифте
     */
    public void showInfo() {

        if (lift.passengerList.size() != 0) {
            String strInfo = " Лифт №" + Integer.toString(lift.getNumber() + 1) + " находится на этаже №"
                    + Integer.toString(lift.getLocation()) + " в нем:";


            if (lift.colOfPassangeInLift() == 0)
                strInfo += " нет пассажиров ";
            else
                for (int i = 0; i < lift.passengerList.size(); i++)
                    if (lift.passengerList.get(i).getIsInLift())  //если пассажир в лифте
                        strInfo += " Пассажир едет с этажа №" + Integer.toString(lift.passengerList.get(i).getFloorOfDeparture())
                                + " на этаж №" + Integer.toString(lift.passengerList.get(i).getFloorOfDestination()) + "  ";


            strInfo += ("вместительность:" + Integer.toString(lift.getVol()));


            if (lift.getRout())
                strInfo += " подходит для движения вверх";
            else
                strInfo += " подходит для движения вниз";
            console.printf(strInfo + "\n");
        } else {
            String strInfo = " Лифт №" + Integer.toString(lift.getNumber() + 1) + " закончил работу";
            console.printf(strInfo + "\n");
        }
    }

    /**
     * очищает матрицу отрисовки от записей местонахождения лифта и движения пассажиров
     */
    public void clearMatrix(){
        for (int i = lift.getPossibleInitialFloor(); i <= lift.getPossibleFinaleFloor(); i++) {
            matr[2 * numberOfFloors - 2 * i + 1][lift.getNumber() + 3 * (lift.getNumber() + 1)] = "   "; //направление вверх
            matr[2 * numberOfFloors - 2 * i + 1][lift.getNumber() + 3 * (lift.getNumber() + 1) + 1] = "   "; //направление вниз
            matr[2 * numberOfFloors - 2 * i + 1][lift.getNumber() + 3 * (lift.getNumber() + 1) + 2] = "   "; //сам лифт
        }
    }

    /**
     * выводит на консоль всю информацию о пассажирах лифта
     */
    public void showInfoPassange(){

        for (int i = 0; i < lift.passengerList.size(); i++){
            String strinfo = "";
            strinfo += (" Пассажир лифта №" + Integer.toString(lift.getNumber()+1) + " хочет уехать с "
                    + Integer.toString(lift.passengerList.get(i).getFloorOfDeparture())
                    + " на этаж " + Integer.toString(lift.passengerList.get(i).getFloorOfDestination())  );
            if (lift.passengerList.get(i).getIsLater())
                strinfo += " поедет, но не в этот раз :(";
            console.printf(strinfo + "\n");
        }
    }
}
