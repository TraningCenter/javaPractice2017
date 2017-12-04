package Avilova.Lift;
import java.util.ArrayList;
import  java.io.Console;

public class Panel{
    /**список объектов отрисовки*/
    private ArrayList<ObjectOfGraphics> objects;

    /**список лифтов*/
    private ArrayList<Lift> lifts;

    /**матрица отрисовки*/
    private String [][] matr;

    /**этажность*/
    private int numberOfFloors;
    private Console console;

    /**
     * конструктор панели
     * @param matr-матрица отрисовки
     * @param numberOfFloors-этажность
     * @param lifts-лифт
     * @param console-консоль
     */
    Panel(String [][] matr , int numberOfFloors, ArrayList<Lift> lifts, Console console){
        this.matr = matr;
        this.numberOfFloors = numberOfFloors;
        this.lifts = lifts;
        this.console = console;
        this.objects = CreateObjects(lifts);
    }

    /**
     * создает и заполняет список объектов отрисовки
     * @param lifts-список лифтов
     * @return список объектов отрисовки
     */
    private ArrayList<ObjectOfGraphics> CreateObjects(ArrayList<Lift>lifts){
        ArrayList<ObjectOfGraphics> objects= new ArrayList<ObjectOfGraphics>();
        for (Lift lift: lifts)
            objects.add(new ObjectOfGraphics(lift, numberOfFloors, matr, console));
        return  objects;
    }

    /**
     * отображение движения пассажиров и местонахождения лифтов
     */
    public void showGraphics() {
        for (ObjectOfGraphics object: objects)
            object.showGraphics();
    }

    /**
     * отображение информации о лифтах
     */
    public void showInfo() {
        for (ObjectOfGraphics object: objects)
            object.showInfo();
    }

    /**
     * отображение информации о пассажирах лифтов
     */
    public  void showInfoPassange(){
        for (ObjectOfGraphics object: objects)
            object.showInfoPassange();
    }
}
