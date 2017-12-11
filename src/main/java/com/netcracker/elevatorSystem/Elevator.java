package com.netcracker.elevatorSystem;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Класс служит для хранения объектов со свойствами currentFloor, elevatorType, loadCapacity, floors, target, passengersInElevator,
 * numberOfElevators, elevatorIndex, direction, maxFloor;
 */
public class Elevator {
    /** Свойство - текущий этаж */
    private int currentFloor;
    /** Свойство - грузоподъемность */
    private int loadCapacity;
    /** Свойство - текущий вес пассажиров в лифте */
    private int currentWeight;
    /** Свойство - список этажей, где будет останавливаться лифт*/
    private TreeSet<Integer> target;
    //private ArrayList<Integer> target;
    /** Свойство - список этажей, куда будет возвращаться лифт*/
    private TreeSet<Integer> postponedTarget;
    //private ArrayList<Integer> target;
    /** Свойство - список пассажиров в лифте */
    private ArrayList<Passenger> passengersInElevator;
    /** Свойство - список пассажиров, ожидающих лифт */
    private ArrayList<Passenger> passengers;
    /** Свойство - номер лифта */
    private int elevatorIndex;
    /** Свойство - направление лифта */
    private Direction direction;

    /**Конструктор вызывается при создании нового объекта и задает начальные значения текущего веса, этажа, направления и значение
     * @param index - номер лифта */
    public Elevator(int index) {
        this.currentWeight = 0;
        this.currentFloor = 1;
        this.direction = Direction.UP;
        this.elevatorIndex = index;
        this.target = new TreeSet<Integer>();
        this.postponedTarget = new TreeSet<Integer>();
        this.passengersInElevator = new ArrayList<Passenger>();
    }

    /** Метод для загрузки пассажира
     * @param passenger - пассажир, вызвавший лифт
     * Метод добавляет вес пассажира к текущему весу лифта, добавляет пассажира в список пассажиров в лифте, обновляет статус пассажира,
     * удаляет этаж отправления из набора target, добавляет новый этаж назначения в набор target, удаляет этаж отправления из набора
     * postponedTarget, если этаж этого пассажира является отложенным */
    public boolean load(Passenger passenger) {//пассажир на этаже
                    currentWeight += passenger.getWeight();
                    passengersInElevator.add(passenger);
                    passenger.setStatus(false);//isAwaiting = false
                    removeDestination(passenger.getDepartureFloor());
                    addNewDestination(passenger.getDestinationFloor());
                    if (!postponedTarget.isEmpty()) {
                        removePostponedDestination(passenger.getDepartureFloor());
                    }
        return true;
    }

    /** Метод для разгрузки лифта по одному пассажиру
     * @param passenger - пассажир, выходящий из лифта*/
    public boolean unload(Passenger passenger) {
        passengersInElevator.remove(passenger);
        removeDestination(passenger.getDestinationFloor());
        passenger.setIsArrived(true);
        currentWeight -= passenger.getWeight();
        return true;
    }

    /** Метод для добавления нового этажа назначения
     * @param floor - новый этаж назначения*/
    public boolean addNewDestination(int floor){
        target.add(floor);
        return true;
    }

    /** Метод для удаления этажа назначения
     * @param floor - этаж, который нужно удалить из набора этажей назначения*/
    public boolean removeDestination(int floor) { target.remove(floor); return true; }

    /** Метод для удаления этажа назначения из набора этажей отложенных пассажиров
     * @param floor - этаж, который нужно удалить из набора этажей назначения*/
    public boolean removePostponedDestination(int floor) { postponedTarget.remove(floor); return true; }

    /** Метод для заполнения набора этажей назначения пассажиров, за которыми лифт должен приехать на обратном пути
     * @param passenger - пассажир, за которым нужно приехать на обратном пути*/
    public boolean fillPostponedTarget(Passenger passenger) {
        postponedTarget.add(passenger.getDepartureFloor());
        return true;
    }

    /** Метод для вычисления направления движения лифта
     * @param target - этаж, на который должен приехать лифт*/
    public boolean makeDirection(Integer target){

                if (currentFloor < target) {
                    direction = Direction.UP;

                }
                if (currentFloor > target) {
                    direction = Direction.DOWN;
                }
        return true;
    }

    /** Метод для передвижения лифта на следующий этаж
     * @param t - этаж, на который должен приехать лифт*/
    public boolean moveToNextFloor(Integer t){ //not exact
        makeDirection(t);
        if (direction == Direction.UP){
                ++currentFloor;
        }

        if (direction == Direction.DOWN) {
                --currentFloor;
        }
        return true;
    }

    /** Метод для получения данных о лифте
     * @return Возвращает информацию о лифте в виде строки */
    @Override
    public String toString() {
        String str = "Elevator №" + String.valueOf(elevatorIndex)+"\n" + "Floor: "+ String.valueOf(currentFloor) + "\n" + "Load capacity: " + String.valueOf(loadCapacity)
                + "\n" + "Current weight: " + String.valueOf(currentWeight)
                + "\n" + "Number of passengers in the elevator: " + String.valueOf(getPassengersInElevator().size()) + " ( ";
        for (Passenger pasInEl: passengersInElevator) {
            str += String.valueOf(pasInEl.getPassengerIndex()) + " ";
        }
        str += ")";

        int count = 0;
        for (Passenger pas: passengers) {
            if (pas.isArrived()) {
                count++;
            }
        }
        str += "\n" + "Number of arrived passengers: " + String.valueOf(count) + "\n";
        return str;
    }

    /** Метод для получения значения поля {@link Elevator#currentFloor}
     * @return Возвращает текущий этаж
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /** Метод для установления текущего этажа
     * @param currentFloor - этаж */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /** Метод для получения значения поля {@link Elevator#loadCapacity}
     * @return Возвращает грузоподъемность лифта
     */
    public int getLoadCapacity() {
        return loadCapacity;
    }

    /** Метод для установления грузоподъемности лифта
     * @param loadCapacity - грузоподъемность */
    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    /** Метод для получения значения поля {@link Elevator#currentWeight}
     * @return Возвращает текущий вес лифта
     */
    public int getCurrentWeight() {
        return currentWeight;
    }

    /** Метод для получения набора {@link Elevator#target}
     * @return Возвращает набор этажей, на которые вызвн лифт
     */
    public TreeSet<Integer> getTarget() {
        return target;
    }

    /** Метод для получения набора {@link Elevator#postponedTarget}
     * @return Возвращает набор этажей, на которые лифт должен будет вернуться
     */
    public TreeSet<Integer> getPostponedTarget() {
        return postponedTarget;
    }

    /** Метод для получения списка {@link Elevator#passengersInElevator}
     * @return Возвращает список пассажиров, находящихся в лифте
     */
    public ArrayList<Passenger> getPassengersInElevator() {
        return passengersInElevator;
    }

    /** Метод для получения списка {@link Elevator#passengers}
     * @return Возвращает список всех пассажиров лифта
     */
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    /** Метод для установления списка пассажиров
     * @param passengers - пассажиры */
    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    /** Метод для получения значения поля {@link Elevator#elevatorIndex}
     * @return Возвращает номер лифта
     */
    public int getElevatorIndex() {
        return elevatorIndex;
    }

    /** Метод для получения значения поля {@link Elevator#direction}
     * @return Возвращает направление лифта
     */
    public Direction getDirection() {
        return direction;
    }

    /** Метод для установления направления лифта
     * @param direction - направление */
    public void setDirection(Direction direction) {

        this.direction = direction;
    }

    /** Метод для установления текущего веса лифта
     * @param currentWeight - текущий вес */
    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    /** Метод для установления набора target
     * @param target - набор */
    public void setTarget(TreeSet<Integer> target) {
        this.target = target;
    }

    /** Метод для установления списка пассажиров в лифте
     * @param passengersInElevator - список пассажиров в лифте */
    public void setPassengersInElevator(ArrayList<Passenger> passengersInElevator) {
        this.passengersInElevator = passengersInElevator;
    }

    /** Метод для установления набора postponedTarget
     * @param postponedTarget - набор */
    public void setPostponedTarget(TreeSet<Integer> postponedTarget) {
        this.postponedTarget = postponedTarget;
    }

    /** Метод для установления номера лифта
     * @param elevatorIndex - номер лифта */
    public void setElevatorIndex(int elevatorIndex) {
        this.elevatorIndex = elevatorIndex;
    }
}
