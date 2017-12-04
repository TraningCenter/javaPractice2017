package ogkostin.elevator;

import ogkostin.elevator.controller.MainController;

/**
 * Entry point of the application
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        MainController mainController = new MainController();
        System.out.print("\033[H\033[2J");
        mainController.start();
    }
}