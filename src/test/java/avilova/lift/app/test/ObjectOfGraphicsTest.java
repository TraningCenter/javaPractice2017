package avilova.lift.app.test;
import avilova.lift.app.Lift;
import avilova.lift.app.ui.ObjectOfGraphics;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ObjectOfGraphicsTest {

    @Test
    public void showGraphics() {
        Lift lift = new Lift(false,1,2,1,12,0,1);
        int numberOfFloors = 12;
        String [][] matr = new String[2 * numberOfFloors + 1][14];
        ObjectOfGraphics objectOfGraphics = new ObjectOfGraphics(lift,numberOfFloors, matr,System.console());
        boolean actual = objectOfGraphics.showGraphics();
        boolean expected = true;
        assertEquals(actual,expected);
    }


    @Test
    public void showInfo() {
        Lift lift = new Lift(true, 1, 2, 1, 12, 1, 0);
        int numberOfFloors = 12;
        String[][] matr = new String[2 * numberOfFloors + 1][14];
        ObjectOfGraphics objectOfGraphics = new ObjectOfGraphics(lift, numberOfFloors, matr, System.console());
        String actual = objectOfGraphics.showInfo();

        String expected = " Лифт №1 находится на этаже №1 в нем: нет пассажиров вместительность:2 подходит для движения вверх";

        assertEquals(actual, expected);
    }

    @Test
    public void showInfo2() {
        Lift lift = new Lift(true, 1, 2, 1, 12, 1, 0);
        int numberOfFloors = 12;
        String[][] matr = new String[2 * numberOfFloors + 1][14];
        ObjectOfGraphics objectOfGraphics = new ObjectOfGraphics(lift, numberOfFloors, matr, System.console());
        lift.passengerList.get(0).setIsInLift(true);
        String actual = objectOfGraphics.showInfo();
        String expected = " Лифт №1 находится на этаже №1 в нем: Пассажир едет с этажа №" + Integer.toString(lift.passengerList.get(0).getFloorOfDeparture())+
                " на этаж №" + Integer.toString(lift.passengerList.get(0).getFloorOfDestination()) + "  " + "вместительность:2 подходит для движения вверх";

        assertEquals(actual, expected);
    }

    @Test
    public void showInfo3() {
        Lift lift = new Lift(false, 1, 2, 1, 12, 1, 0);
        int numberOfFloors = 12;
        String[][] matr = new String[2 * numberOfFloors + 1][14];
        ObjectOfGraphics objectOfGraphics = new ObjectOfGraphics(lift, numberOfFloors, matr, System.console());
        String actual = objectOfGraphics.showInfo();

        String expected = " Лифт №1 находится на этаже №1 в нем: нет пассажиров вместительность:2 подходит для движения вниз";

        assertEquals(actual, expected);
    }

    @Test
    public void showInfo4() {
        Lift lift = new Lift(false, 1, 2, 1, 12, 0, 0);
        int numberOfFloors = 12;
        String[][] matr = new String[2 * numberOfFloors + 1][14];
        ObjectOfGraphics objectOfGraphics = new ObjectOfGraphics(lift, numberOfFloors, matr, System.console());
        String actual = objectOfGraphics.showInfo();

        String expected = " Лифт №1 закончил работу";

        assertEquals(actual, expected);
    }

    @Test
    public void showInfoPassange() {

        Lift lift = new Lift(true, 1, 2, 1, 12, 1, 0);
        int numberOfFloors = 12;
        String[][] matr = new String[2 * numberOfFloors + 1][14];
        ObjectOfGraphics objectOfGraphics = new ObjectOfGraphics(lift, numberOfFloors, matr, System.console());
        String actual = objectOfGraphics.showInfoPassange();

        String expected = " Пассажир лифта №1 хочет уехать с "+ Integer.toString(lift.passengerList.get(0).getFloorOfDeparture())
                           + " на этаж " + Integer.toString(lift.passengerList.get(0).getFloorOfDestination());
        if (lift.passengerList.get(0).getIsLater())
            expected += " поедет, но не в этот раз :(";
        expected += "\n";

        assertEquals(actual, expected);
    }
}