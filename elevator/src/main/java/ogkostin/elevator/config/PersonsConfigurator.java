package ogkostin.elevator.config;

import ogkostin.elevator.model.Building;
import ogkostin.elevator.model.Direction;
import ogkostin.elevator.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Creates a list of persons with parameters, which you
 * input in the command line.
 *
 * @author Oleg Kostin
 */
public class PersonsConfigurator implements Configurator {


    private Building building;
    private List<Person> persons;
    private Scanner in = new Scanner(System.in);

    public PersonsConfigurator(Building building) {
        this.building = building;
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

    private void configPerson(Building building) {
        Person person = new Person();
        configWeight(person);
        configCurrentFloor(person);
        configTargetFloor(person);
        if (person.getCurrentFloor() < person.getTargetFloor()) {
            person.setDirection(Direction.up);
        } else {
            person.setDirection(Direction.down);
        }
        persons.add(person);
    }

    @Override
    public List<Person> configure() {
        persons = new ArrayList<>();
        System.out.println("If you want to add a person print '1' else print any digit");
        int temp = inputData();
        while (temp == 1) {
            configPerson(building);
            System.out.println("If you want to add a person print '1' else print any digit");
            temp = inputData();
        }
        return persons;
    }

    private void configCurrentFloor(Person person) {
        System.out.println("Input person's current floor");
        int temp = inputData();
        if (temp > 0 && temp <= building.getFloorCount())
            person.setCurrentFloor(temp);
        else {
            System.out.println("Incorrect input");
            configCurrentFloor(person);
        }
    }

    private void configTargetFloor(Person person) {
        System.out.println("Input person's target floor");
        int temp = inputData();
        if (temp > 0 && temp <= building.getFloorCount() && temp != person.getCurrentFloor()) {
            person.setTargetFloor(temp);
            if (person.getTargetFloor() < person.getCurrentFloor()) {
                person.setDirection(Direction.down);
            } else {
                person.setDirection(Direction.up);
            }
        } else {
            System.out.println("Incorrect input");
            configTargetFloor(person);
        }
    }

    private void configWeight(Person person) {

        System.out.println("Input person's weight");
        int temp = inputData();
        if (temp > 0 && temp <= building.getElevatorMinWeight())
            person.setWeight(temp);

        else {
            System.out.println("Incorrect input");
            configWeight(person);
        }

    }
}




