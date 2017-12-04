package Avilova.Lift;
import java.util.ArrayList;

public class Lift {
    /**направление движения лифта true-вверх false-вниз*/
    private boolean rout;

    /**этаж местонахождения лифта*/
    private int location;

    /**вместительность лифта*/
    private int vol;

    /**возможный начальный этаж лифта*/
    private int possibleInitialFloor;

    /**возможный конечный этаж лифта*/
    private int possibleFinaleFloor;

    /**список пассажиров лифта*/
    public ArrayList<Passenger> passengerList;

    /**номер лифта*/
    private int number;

    /**контроллер лифта*/
    public ControllerOfLift controller;

    /**
     * конструктор лифта
     * @param rout-направление движения лифта true-вверх false-вниз
     * @param location-этаж местонахождения лифта
     * @param vol-вместительность лифта
     * @param possibleInitialFloor-возможный начальный этаж лифта
     * @param possibleFinaleFloor-*возможный конечный этаж лифта
     * @param numberOfPassenger-колличество пассажиров, которые хотят воспользоваться данным лифтом
     * @param number-номер лифта
     */
    Lift(boolean rout, int location,int vol,int possibleInitialFloor,int possibleFinaleFloor, int numberOfPassenger, int number){
        this.rout = rout;
        this.location = location;
        this.vol = vol;
        this.possibleInitialFloor = possibleInitialFloor;
        this.possibleFinaleFloor = possibleFinaleFloor;
        this.passengerList = createPassanger(numberOfPassenger);
        this.number = number;
        controller = new ControllerOfLift(this);
    }

    public boolean getRout() {
        return rout;
    }

    public void setRout(boolean rout) {
        this.rout  = rout;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location  = location;
    }

    public int getVol() {
        return vol;
    }

    public int getPossibleInitialFloor() {
        return possibleInitialFloor;
    }

    public int getPossibleFinaleFloor() {
        return possibleFinaleFloor;
    }

    public int getNumber() {
        return number;
    }

    /**
     * создания списка пассажиров
     * @param numberOfPassenger-колличество пассажиров, которые хотят воспользоваться данным лифтом
     * @return список пассажиров
     */
    public ArrayList<Passenger> createPassanger(int numberOfPassenger){
        ArrayList<Passenger> passengerList= new ArrayList<Passenger>();
        for (int i = 0; i < numberOfPassenger; i++)
            passengerList.add(new Passenger( this.possibleInitialFloor, this.possibleFinaleFloor));
        return  passengerList;
    }

    /**
     * нахождения числа пассажиров в лифте
     * @return кол-во пассажиров
     */
    public int colOfPassangeInLift(){
        int col = 0;
        for (int i = 0; i < passengerList.size(); i++)
            if (passengerList.get(i).getIsInLift())
                col++;
        return col;
    }
}
