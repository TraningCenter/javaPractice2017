package com.netcracker.elevatorSystem;
import java.util.ArrayList;
/**
 * Класс для хранения методов, выполняющих отрисовку модели
 */
public class Render {
    /** Метод для получения Y-координаты ячейки в матрице, где будет располагаться лифт
     * @param floor - этаж, на котором находится лифт */
    public int getCellNumberY(int floor){//calculate distance to # on the y-axis
        return (floor == 1) ? 1 : 1 + 3*(floor - 1);
    }

    /** Метод для получения X-координаты ячейки в матрице, где будет располагаться лифт
     * @param elevatorIndex - номер лифта */
    public int getCellNumberX(int elevatorIndex) {//calculate distance to # on the x-axis
       // return (elevatorIndex == 1) ? 4 : 4 + 3*(elevatorIndex - 1);
        return (elevatorIndex == 1) ? 4 : 4 + 5*(elevatorIndex - 1);
    }
    /** Метод для построения лифта в матрице
     * @param matrix - матрица
     * @param shaft - шахта, лифт которой нужно отрисовать
     * @return возвращает матрицу с отрисованным лифтом */
    public boolean buildElevator(char[][] matrix, Shaft shaft){
        int cellNumberY = getCellNumberY(shaft.getElevator().getCurrentFloor());
        int i = matrix.length - 1 - cellNumberY;
        int cellNumberX = getCellNumberX(shaft.getElevator().getElevatorIndex());
        int j = cellNumberX;
        matrix[i][j] = '#';
        if (shaft.getElevator().getDirection() == Direction.DOWN) {
            matrix[i][j+1] = 'd';
        }
        if (shaft.getElevator().getDirection() == Direction.UP) {
            matrix[i][j+1] = 'u';
        }
        if (shaft.getElevator().getDirection() == Direction.NONE) {
            matrix[i][j+1] = 'f';
        }
        return true;
    }
    /** Метод для построения шахт в матрице
     * @param matrix - матрица */
    public boolean buildFrame(char[][] matrix){
        int height = matrix.length;
        int width = matrix[0].length;
        for (int i = 0; i < height; i += 3) { // < height - Shaft.numberOfFloors
            for (int j = 0; j < width - 2; j++) {
                matrix[i][j] = '-';
            }
        }

        for (int i = 1; i < height - 1; i++) {
            for (int j = 2; j < width - 4; j += 5){
                matrix[i][j] = '|';
            }
        }

        for (int i = 2; i < height - 1; i += 3) {
            matrix[i][0] = '*';
            matrix[i][width - 3] = '*';
        }

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (matrix[i][j] != '|' && matrix[i][j]!='-' && matrix[i][j]!='*'){
                    matrix[i][j] = ' ';
                }
            }
        }
        return true;
    }

    /** Метод для вывода матрицы на экран
     * @param matrix - матрица */
    public boolean draw(char[][] matrix) {
        int width = matrix.length;
        int height = matrix[0].length;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("*************************************");
        System.out.println();
        return true;
    }

    /** Метод для вывода информации о пассажирах на экран
     * @param passengers - пассажиры, которые вызвали лифт
     * @param index - номер лифта */
    public String showPassengersInfo(ArrayList<Passenger> passengers, int index){

        String str = "Passengers of elevator №" + index;
        for (Passenger p: passengers) {
            str += "\n" + p.toString();
        }
        str += "\n";
        return str;
    }

    /** Метод для вывода информации о лифте на экран
     * @param shaft - шахта, о лифте которой выводится информация */
    public String showElevatorInfo(Shaft shaft){
        return shaft.getElevator().toString() + "\n";
    }
}
