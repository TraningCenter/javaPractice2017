package com.netcracker.elevatorSystem;


import java.io.Console;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, в котором содержится точка входа в программу, обеъекты со свойствами numberOfShafts, shafts, mainMatrix,
 */
public class SystemController {
    /** Свойство - число шахт */
    private int numberOfShafts;
    /** Свойство - массив шахт */
    private Shaft[] shafts;
    /** Свойство - главная матрица для отрисовки */
    private char[][] mainMatrix;

    /** Методя для ввода информации и заполения полей системы */
    public void fillSystemInfo(){
        Scanner in = new Scanner(System.in);
        int numFloor;
        do {
            System.out.println("Number of floors: ");

            while (!in.hasNextInt()) {
                System.out.println("Invalid input!");
                in.next();
            }
            numFloor = in.nextInt();
        } while (numFloor <= 0);
        Shaft.numberOfFloors = numFloor;

        int numEl;
        do {
            System.out.println("Number of elevators: ");
            while (!in.hasNextInt()){
                System.out.println("Invalid input!");
                in.next();
            }
            numEl = in.nextInt();
        } while (numEl <= 0);
        numberOfShafts = numEl;

        shafts = new Shaft[numberOfShafts];
        for (int i = 0; i < numberOfShafts; i++) {
            shafts[i] = new Shaft(i+1);
        }
        for (int i = 0; i < numberOfShafts; i++){
            int numPas;
            do {
                System.out.println("Number of passengers for "+(i + 1)+" elevator");
                while (!in.hasNextInt()) {
                    System.out.println("Invalid input!");
                    in.next();
                }
                numPas = in.nextInt();
            } while (numPas <= 0);

            ArrayList<Passenger> newPassengers = new ArrayList<Passenger>();
            for (int j = 0; j < numPas; j++){
                newPassengers.add(new Passenger(j+1));
            }
            shafts[i].getElevator().setPassengers(newPassengers);

            int loadCap;
            do {
                System.out.println("Load capacity of "+ (i+1)+ " elevator");
                while (!in.hasNextInt()) {
                    System.out.println("Invalid input!");
                    in.nextInt();
                }
                loadCap = in.nextInt();
            } while (loadCap <= 0);
            shafts[i].getElevator().setLoadCapacity(loadCap);

            for (Passenger pas: shafts[i].getElevator().getPassengers()) {
                System.out.println("Random filling of departure and destination floor for passenger №" + pas.getPassengerIndex()+ " - 1");
                System.out.println("Manual filling of departure and destination floor for passenger №" + pas.getPassengerIndex()+ " - 2");
                int input = in.nextInt();
                switch (input) {
                    case 1:
                        pas.randomFiling();
                        break;
                    case 2:
                        pas.manualFiling();
                        break;
                    default:
                        System.out.println("Invalid input!");
                }
                int elevatorIndex = shafts[i].getElevator().getElevatorIndex();
                pas.setElevatorIndex(elevatorIndex);
            }
        }
    }

    /** Метод для начального заполнения набора этажей отправления, на которые должен приехать каждый лифт за своими пассажирами*/
    public boolean fillInitialTarget(){
        for (int i = 0; i < numberOfShafts; i++) {
            for (Passenger pas : shafts[i].getElevator().getPassengers()) {
                RemoteControl control = new RemoteControl();
                ICommand callElevator = new CallElevatorCommand(pas);
                control.setCommand(callElevator);
                control.pushButton();
                shafts[i].getElevator().addNewDestination(pas.getFloorButton().getFloorNumber());

            }
        }
        return true;
    }

    /** Метод для вычисления этажа, на который должен ехать лифт в данный момент
     * @param i - номер текущей шахты */
    public int createCurrentTarget(int i){
        int currentFloor = shafts[i].getElevator().getCurrentFloor();
        Integer currentTarget;
        int maxTarget;
        int minTarget;
        try {
            maxTarget = shafts[i].getElevator().getTarget().last();//максимальный элемент из набора target
            minTarget = shafts[i].getElevator().getTarget().first();//минимальный элемент из набора target
        } catch (Exception ex) {
            if (shafts[i].getElevator().getPostponedTarget().isEmpty()) {//если target пустой и postponedTarget пустой
                shafts[i].getElevator().setDirection(Direction.NONE);
                maxTarget = 0;
                minTarget = 0;
            } else {
                //если набор postponedTarget не пустой, то сливаем его с target
                for (Integer pt: shafts[i].getElevator().getPostponedTarget()) {
                    shafts[i].getElevator().getTarget().add(pt);
                }
                //снова вычисляем maxTarget и minTarget
                maxTarget = shafts[i].getElevator().getTarget().last();
                minTarget = shafts[i].getElevator().getTarget().first();
            }
        }
        Direction currentDirection = shafts[i].getElevator().getDirection();
        try {
            if (currentDirection == Direction.UP) { // если лифт едет вверх
                if (currentFloor < maxTarget) { //и если он находится не на последнем вызванном этаже
                    currentTarget = shafts[i].getElevator().getTarget().first(); //то берем минимальный этаж из набора target
                    while (currentFloor > currentTarget) {
                        currentTarget = shafts[i].getElevator().getTarget().higher(currentTarget);//пропускаем отложенные этажи
                    }
                } else {
                    // иначе (лифт находится на последнем вызванном этаже), то
                    boolean changeDirection = true; //проверяем, можем ли менять направление
                    for (Passenger p: shafts[i].getElevator().getPassengers()) {
                        if (shafts[i].getElevator().getCurrentFloor() == p.getDepartureFloor() && p.getDestinationFloor() - p.getDepartureFloor() > 0) {
                            changeDirection = false;
                        }
                    }
                    if (changeDirection) {//если на этом этаже нет пассажиров, которые хотят ехать вверх
                        shafts[i].getElevator().setDirection(Direction.DOWN);//меняем направление движения лифта
                        currentTarget = shafts[i].getElevator().getTarget().last(); //и берем максимальный этаж из набора target
                    } else { //иначе (есть пассажиры, которые хотят ехать вверх)
                        currentTarget = shafts[i].getElevator().getCurrentFloor();//берем текущий этаж, чтобы перейти к загрузке этих пассажиров
                    }
                    //добавляем в набор target элементы из набора postponedTarget
                    if (!shafts[i].getElevator().getPostponedTarget().isEmpty()) {
                        for (Integer pt : shafts[i].getElevator().getPostponedTarget()) {
                            shafts[i].getElevator().getTarget().add(pt);
                        }
                    }
                }
            } else { //иначе (лифт едет вниз)
                if (currentFloor > minTarget) { //и если он находится не на первом вызванном этаже,
                    //то берем максимальный элемент из набора target
                    currentTarget = shafts[i].getElevator().getTarget().last();
                    while (currentFloor < currentTarget) {
                        currentTarget = shafts[i].getElevator().getTarget().lower(currentTarget);
                    }
                } else {//иначе (лифт находится на первом вызванном этаже),
                    boolean changeDirection = true;//проверяем, можем ли менять направление
                    for (Passenger p: shafts[i].getElevator().getPassengers()) {
                        if (shafts[i].getElevator().getCurrentFloor() == p.getDepartureFloor() && p.getDestinationFloor() - p.getDepartureFloor() < 0) {
                            changeDirection = false;
                        }
                    }
                    if (changeDirection) {//если на этом этаже нет пассажиров, которые хотят ехать вниз
                        shafts[i].getElevator().setDirection(Direction.UP);//меняем направление движения лифта
                        currentTarget = shafts[i].getElevator().getTarget().first();//и берем минимальный этаж из набора target
                    } else {// иначе (есть пассажиры которые хотят ехать вниз)
                        currentTarget = shafts[i].getElevator().getCurrentFloor();// берем текущий этаж, чтобы перейти к загрузке этих пассажиров
                    }
                    if (!shafts[i].getElevator().getPostponedTarget().isEmpty()) {
                        //добавляем в набор target элементы из набора postponedTarget
                        for (Integer pt : shafts[i].getElevator().getPostponedTarget()) {
                            shafts[i].getElevator().getTarget().add(pt);
                        }
                    }
                }
            }
        } catch(Exception ex) {
            shafts[i].getElevator().setDirection(Direction.NONE);
            currentTarget = shafts[i].getElevator().getCurrentFloor();
        }
        return currentTarget;
    }



    /**Метод для запуска и работы системы */
    public boolean startSystem(int millis) throws InterruptedException {

       fillInitialTarget();

       MainVisualizer mainVisualizer = new MainVisualizer(shafts);
       int height = mainVisualizer.getHeight();
       int width = mainVisualizer.getWidth();
       mainMatrix = new char[height][width];

       boolean areAllPassengersArrived = false;
       boolean existsPassengerToGetInto; // есть хотя бы один пассажир для загрузки
       boolean existsPassengerToGetOut; //есть хотя бы один пассажир для разгрузки

       RemoteControl control = new RemoteControl();
       int arrivedPassengersNumber = 0; //количество пассажиров, которые доехали до нужных этажей
       int totalPassengersNumber = 0;
       String passengersInfo = mainVisualizer.showPassengersInfo();
       System.out.println(passengersInfo);

       for (int i = 0; i < numberOfShafts; i++) {
            totalPassengersNumber += shafts[i].getElevator().getPassengers().size();
       }
       while (!areAllPassengersArrived) { //пока все пассажиры не доехали до нужных этажей
            //отрисовка

            System.out.print("\033[H\033[2J");
            mainVisualizer.drawSystem(mainMatrix);
            String elevatorsInfo = mainVisualizer.showElevatorsInfo();
            System.out.println(elevatorsInfo);
            Thread.sleep(millis);

            for (int i = 0; i < numberOfShafts; i++) {
                existsPassengerToGetInto = false;
                existsPassengerToGetOut = false;

                int currentTarget = createCurrentTarget(i);

                for (Passenger pas : shafts[i].getElevator().getPassengers()) {
                    //если isAwaiting = false и isArrived = false (то есть этот пассажир в лифте),
                    //и текущий этаж лифта совпадает с этажом назначения пассажира, то разгрузка
                    boolean unloading = !pas.getStatus() && !pas.isArrived() && pas.getDestinationFloor() == shafts[i].getElevator().getCurrentFloor();

                    //если isAwaiting = true и isArrived = false (то есть этот пассажир на этаже),
                    //текущий этаж лифта совпадает с этажом, на котором стоит пассажир, направление движения лифта совпадает с направлением движения пассажира
                    // и его вес не вызовет перегруз, и пассажир точно заходит в лифт, то загрузка
                    boolean loading = pas.getStatus() && !pas.isArrived() && pas.getInto() && pas.getDepartureFloor() == shafts[i].getElevator().getCurrentFloor()
                            && ((pas.getDestinationFloor() - pas.getDepartureFloor() > 0 && shafts[i].getElevator().getDirection() == Direction.UP) ||
                            (pas.getDestinationFloor() - pas.getDepartureFloor() < 0 && shafts[i].getElevator().getDirection() == Direction.DOWN)) &&
                            (shafts[i].getElevator().getCurrentWeight() + pas.getWeight() <= shafts[i].getElevator().getLoadCapacity());

                    //если isAwaiting = true и isArrived = false (то есть этот пассажир на этаже), текущий этаж лифта совпадает с этажом, на котором
                    //стоит пассажир, направления движения лифта не совпадает с направлением движения пассажира, или его вес вызовет перегруз, или он передумал заходить, то
                    //откладываем загрузку
                    boolean postponedLoading = pas.getStatus() && !pas.isArrived() && (pas.getDepartureFloor() == shafts[i].getElevator().getCurrentFloor()) &&
                            (((pas.getDestinationFloor() - pas.getDepartureFloor() > 0 && shafts[i].getElevator().getDirection() == Direction.DOWN) ||
                                    (pas.getDestinationFloor() - pas.getDepartureFloor() < 0 && shafts[i].getElevator().getDirection() == Direction.UP))
                                    || (shafts[i].getElevator().getCurrentWeight() + pas.getWeight() > shafts[i].getElevator().getLoadCapacity()) || !pas.getInto());
                    if (unloading){
                        existsPassengerToGetOut = true;
                        ICommand unloadCommand = new UnloadCommand(shafts[i].getElevator(), pas);
                        control.setCommand(unloadCommand);
                        control.unload();
                        arrivedPassengersNumber++;
                    }
                    if (arrivedPassengersNumber == totalPassengersNumber) {//все пассажиры доехали, условие выхода из цикла - true
                        areAllPassengersArrived = true;
                    }
                    if (loading){
                        existsPassengerToGetInto = true;
                        ICommand loadCommand = new LoadCommand(shafts[i].getElevator(), pas);
                        control.setCommand(loadCommand);
                        control.load();
                    }
                    if (postponedLoading) {
                        shafts[i].getElevator().fillPostponedTarget(pas);//добавили этаж отправления пассажира в postponedTarget
                    }
                }

                //если некому заходить в лифт и выходить из него, и лифту есть куда ехать, то продолжаем движение
                if (!existsPassengerToGetInto && !existsPassengerToGetOut && shafts[i].getElevator().getDirection() != Direction.NONE) {
                        ICommand moveCommand = new MoveCommand(shafts[i].getElevator(), currentTarget);
                        control.setCommand(moveCommand);
                        control.move();
                        System.out.print("\033[H\033[2J");
                        System.out.println("Moving to next target ");
                        mainVisualizer.drawSystem(mainMatrix);
                        elevatorsInfo = mainVisualizer.showElevatorsInfo();
                        System.out.println(elevatorsInfo);
                        Thread.sleep(millis);
                }
            }
        }

        for (int i = 0; i < numberOfShafts; i++){
           if (areAllPassengersArrived) {
               shafts[i].getElevator().setDirection(Direction.NONE);
           }
        }

        System.out.print("\033[H\033[2J");
        mainVisualizer.drawSystem(mainMatrix);
        String elevatorsInfo = mainVisualizer.showElevatorsInfo();
        System.out.println(elevatorsInfo);
        Thread.sleep(millis);
        return true;
    }

    /** Метод для установления массива шахт
     * @param shafts - массив шахт */
    public void setShafts(Shaft[] shafts) {
        this.shafts = shafts;
    }

    /** Метод для установления количества шахт
     * @param numberOfShafts - количество шахт */
    public void setNumberOfShafts(int numberOfShafts) {
        this.numberOfShafts = numberOfShafts;
    }

    /** Точка входа в программу
    @param args - массив значений, передающихся в метод с помощью командной строки */
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Start system - 1");
            System.out.println("Exit - 0");
            int button = in.nextInt();
            switch (button) {
                case 1:
                    SystemController systemController = new SystemController();
                    systemController.fillSystemInfo();
                    systemController.startSystem(1500);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
}
