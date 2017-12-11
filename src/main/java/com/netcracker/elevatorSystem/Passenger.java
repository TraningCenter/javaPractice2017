package com.netcracker.elevatorSystem;

import java.util.Random;
import java.util.Scanner;

import static com.netcracker.elevatorSystem.Direction.DOWN;
import static com.netcracker.elevatorSystem.Direction.UP;

/**
 * Класс служит для хранения объектов со свойствами departureFloor, destinationFloor, isAwaiting, weight, passengerIndex, elevatorIndex,
  isArrived, floorButton, elevatorButton;
 */
public class Passenger {
    /** Свойство - этаж отправления */
    private int departureFloor;
    /** Свойство - этаж назначения */
    private int destinationFloor;
    /** Свойство - состояние ожидания лифта (true - пассажир ждет лифт, false - не ждет) */
    private boolean isAwaiting;
    /** Свойство - вероятность захода пассажира в лифт */
    private double probabilityIn;
    /** Свойство - вес пассажира */
    private int weight;
    /** Свойство - номер пассажира */
    private int passengerIndex;
    /** Свойство - номер лифта, который выбрал пассажир */
    private int elevatorIndex;
    /** Свойство - значение приезда пассажира на нужный этаж (true - пассажир доехал, false - не доехал) */
    private boolean isArrived;
    /** Свойство - кнопка на этаже */
    private FloorButton floorButton;


    /**Конструктор при создании нового объекта с заданным значением
     * @param index - номер пассажира */
    public Passenger(int index){
        this.isAwaiting = true;
        this.isArrived = false;
        this.floorButton = new FloorButton();
        this.passengerIndex = index;
    }

    /** Метод для вызова лифта */
    public boolean pushFloorButton(){
        Direction dir = (destinationFloor - departureFloor > 0) ? UP : DOWN;
        floorButton.setIsPressed(true);
        floorButton.setDirectionLabel(dir);
        floorButton.setFloorNumber(departureFloor);
        return true;
    }

    /** Метод для получения значения загрузки пассажира в лифт в соответствии с вероятностью */
    public boolean getInto(){
        return Math.random()<= probabilityIn ? true : false;
    }

    /** Метод для случайного заполнения полей (этажа отправления, назначения и веса пассажира) */
    public boolean randomFiling(){
        Random random  = new Random();
        int tmpDepartureFloor = random.nextInt(Shaft.numberOfFloors) + 1;
        setDepartureFloor(tmpDepartureFloor);
        int tmpDestinationFloor = random.nextInt(Shaft.numberOfFloors) + 1;
        if (tmpDestinationFloor!= tmpDepartureFloor) {
            setDestinationFloor(tmpDestinationFloor);
        } else {
            if (tmpDestinationFloor == Shaft.numberOfFloors) {

                setDestinationFloor(tmpDestinationFloor - 1);
            } else {
                setDestinationFloor(tmpDestinationFloor + 1);
            }
        }
        setProbabilityIn(0.5 + Math.random()*0.5);
        setWeight(random.nextInt(141)+15);
        return true;
    }

    /** Метод для заполенения полей (этажа отправления, назначения и номера лифта) вручную */
    public void manualFiling(){
        Random random  = new Random();
        Scanner in = new Scanner(System.in);
        int depFloor;
        do {
            System.out.println("Departure floor: ");
            while (!in.hasNextInt()) {
                System.out.println("Invalid input!");
                in.nextInt();
            }
            depFloor = in.nextInt();
        } while (depFloor <= 0);
        setDepartureFloor(depFloor);

        int destFloor;
        do {
            System.out.println("Destination floor: ");
            while (!in.hasNextInt()) {
                System.out.println("Invalid input!");
                in.nextInt();
            }
            destFloor = in.nextInt();
        } while (destFloor <= 0);

        setDestinationFloor(destFloor);

        double probIn;
        do {
            System.out.println("Probability of getting into the elevator: ");
            while (!in.hasNextDouble()) {
                System.out.println("Invalid input!");
                in.nextDouble();
            }
            probIn = in.nextDouble();
        } while (probIn <= 0 && probIn > 1);

        setProbabilityIn(probIn);

        setWeight(random.nextInt(141)+10);
    }

    /** Метод для получения данных о пассажире
     * @return Возвращает информацию о пассажире в виде строки */
    @Override
    public String toString(){
        return "Passenger №" + String.valueOf(passengerIndex) + ": " + String.valueOf(departureFloor) + " -> " + String.valueOf(destinationFloor) +
                " ( " + String.valueOf(weight)+ "kg " + ") " + String.format("%.2f", probabilityIn);
    }

    /** Метод для получения значения поля {@link Passenger#departureFloor}
     * @return Возвращает этаж отправления пассажира
     */
    public int getDepartureFloor() {
        return departureFloor;
    }

    /** Метод для установления этажа отправления пассажира
     * @param departureFloor - этаж отправления */
    public void setDepartureFloor(int departureFloor) {
        this.departureFloor = departureFloor;
    }

    /** Метод для получения значения поля {@link Passenger#destinationFloor}
     * @return Возвращает этаж назначения пассажира
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /** Метод для установления этажа назначения пассажира
     * @param destinationFloor - этаж назначения */
    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    /** Метод для получения значения поля {@link Passenger#isAwaiting}
     * @return Возвращает true, если пассажир ждет лифт; false, если не ждет (уже зашел в лифт)
     */
    public boolean getStatus() {
        return isAwaiting;
    }

    /** Метод для установления статуса пассажира
     * @param isAwaiting - статус пассажира */
    public void setStatus(boolean isAwaiting) {

        this.isAwaiting = isAwaiting;
    }

    /** Метод для получения значения поля {@link Passenger#weight}
     * @return Возвращает вес пассажира
     */
    public int getWeight() {
        return weight;
    }


    /** Метод для установления веса пассажира
     * @param weight - вес пассажира*/
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /** Метод для получения значения поля {@link Passenger#passengerIndex}
     * @return Возвращает номер пассажира
     */
    public int getPassengerIndex() {
        return passengerIndex;
    }

    /** Метод для установления номера лифта, который вызвал пассажир
     * @param elevatorIndex - номер лифта*/
    public void setElevatorIndex(int elevatorIndex) {
        this.elevatorIndex = elevatorIndex;
    }

    /** Метод для получения значения поля {@link Passenger#isArrived}
     * @return Возвращает true, если пассажир доехал до этажа назначения; false, если еще не доехал
     */
    public boolean isArrived() {
        return isArrived;
    }

    /** Метод для установления значения приезда пассажира на нужный этаж
     * @param isArrived - значение приезда */
    public void setIsArrived(boolean isArrived) {
        this.isArrived = isArrived;
    }

    /** Метод для получения поля {@link Passenger#floorButton}
     * @return Возвращает объект класса FloorButton, где хранится номер этажа отправления и направление движения
     */
    public FloorButton getFloorButton() {
        return floorButton;
    }

    /** Метод для установления номера пассажира
     * @param passengerIndex - номер пассажира */
    public void setPassengerIndex(int passengerIndex) {
        this.passengerIndex = passengerIndex;
    }

    /** Метод для установления кнопки на этаже
     * @param floorButton - кнопка */
    public void setFloorButton(FloorButton floorButton) {
        this.floorButton = floorButton;
    }

    /** Метод для получения поля {@link Passenger#elevatorIndex}
     * @return Возвращает номер лифта, который вызвал пассажир
     */
    public int getElevatorIndex() {
        return elevatorIndex;
    }

    /** Метод для получения поля {@link Passenger#probabilityIn}
     * @return Возвращает вероятность захода пассажира в лифт
     */
    public double getProbabilityIn() {
        return probabilityIn;
    }

    /** Метод для установления вероятности захода пассажира в лифт
     * @param probabilityIn - вероятность */
    public void setProbabilityIn(double probabilityIn) {
        this.probabilityIn = probabilityIn;
    }
}

