package ogkostin.elevator.controller;

import ogkostin.elevator.model.Logger;
import ogkostin.elevator.view.Drawer;
import ogkostin.elevator.view.StandartConsoleDrawer;

import java.util.Scanner;

/**
 * Prepare and starts the main cicle of the application
 *
 *  @author Oleg Kostin
 */
public class MainController implements Controller {

    private ConfigurationController cfg;
    private ElevatorController elevatorController;
    private ButtonController btnController;
    private PersonController personController;
    private Drawer drawer;
    private Logger logger;
    private Scanner in = new Scanner(System.in);


    public void start() throws InterruptedException {
        logger = new Logger();
        cfg = new ConfigurationController();
        cfg.configAll(logger);
        drawer = new StandartConsoleDrawer(cfg.getBuilding(), cfg.getPersons(), cfg.getElevators(), cfg.getFloors(), cfg.getShafts());
        elevatorController = new ElevatorController(cfg.getElevators(), cfg.getFloors());
        btnController = new ButtonController(elevatorController);
        personController = new PersonController(cfg.getPersons(), cfg.getElevators(), btnController, cfg.getFloors());
        elevatorController.register(personController);
        personController.call();
        while (!personController.isCompleted()) {
            action();
        }
        System.out.println("If u want to run it again, type '1', else type any digit");
        if (inputData()==1){
            start();
        }
    }

    private void action() throws InterruptedException {

        elevatorController.action();
        drawer.draw();
        logger.print();
        Thread.sleep(250);

    }

    protected int inputData() {
        String temp = in.nextLine();
        try {
            int var = Integer.parseInt(temp);
            return var;
        }
        catch (NumberFormatException ex){
            System.out.println("Input number, please");
            return inputData();
        }
    }

}
