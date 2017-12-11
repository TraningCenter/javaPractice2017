package com.netcracker.elevatorSystem;

/**
 * Класс для хранения объектов со свойствами render, shafts
 */
public class MainVisualizer {
    /** Свойство - отрисовка */
    private Render render;
    /** Свойство - массив шахт */
    private Shaft[] shafts;

    /**Конструктор при создании нового объекта с заданным значением
     * @param shafts - массив шахт */
   public MainVisualizer(Shaft[] shafts){
       this.render = new Render();
       this.shafts = shafts;
   }

    /** Метод для получения значения высоты матрицы */
   public int getHeight(){
       return Shaft.numberOfFloors * 3 + 1;
   }

    /** Метод для получения значения ширины матрицы */
   public int getWidth(){
       return shafts.length * 5 + 7;
   }

   /** Метод для заполнения и отрисовки матрицы
    * @param matrix - матрица */
   public boolean drawSystem(char[][] matrix){
        render.buildFrame(matrix);
       // render.draw(matrix);
        for (Shaft s: shafts) {
            render.buildElevator(matrix, s);
        }
        render.draw(matrix);
        return true;
   }

   /** Метод для отображения информации о пассажирах */
   public String showPassengersInfo(){
       String str = "";
        for (Shaft s: shafts) {
            str += render.showPassengersInfo(s.getElevator().getPassengers(), s.getShaftIndex());
        }
       return str;
   }

    /** Метод для отображения информации о лифтах */
   public String showElevatorsInfo() {
       String str = "";
        for (Shaft s: shafts) {
            str += render.showElevatorInfo(s);
        }
        return str;
   }

    /** Метод для установления массива шахт
     * @param shafts - массив шахт */
   public void setShafts(Shaft[] shafts) {
        this.shafts = shafts;
    }
}
