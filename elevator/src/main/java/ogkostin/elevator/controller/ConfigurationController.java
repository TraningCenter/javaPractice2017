package ogkostin.elevator.controller;

import ogkostin.elevator.config.*;
import ogkostin.elevator.model.*;

import java.util.List;

/**
 * Creates all necessary data for executing application,
 * using configurators
 *
 *  @author Oleg Kostin
 */
public class ConfigurationController implements Controller {


    private BuildingConfigurator buildingConfigurator;
    private ElevatorConfigurator elevatorsConfigurator;
    private ShaftConfigrator shaftConfigrator;
    private FloorConfigurator floorConfigurator;
    private PersonsConfigurator personsConfigurator;

    private Building building;
    private List<Shaft> shafts;
    private List<Floor> floors;
    private List<Elevator> elevators;

    public Building getBuilding() {
        return building;
    }

    public List<Shaft> getShafts() {
        return shafts;
    }

    public List<Floor> getFloors() {
        return floors;
    }


    public List<Elevator> getElevators() {
        return elevators;
    }

    public List<Person> getPersons() {
        return persons;
    }

    private List<Person> persons;

    public void configAll(Logger logger) {

        buildingConfigurator = new BuildingConfigurator();

        building = buildingConfigurator.configure().get(0);

        floorConfigurator = new FloorConfigurator(building);

        shaftConfigrator = new ShaftConfigrator(building);

        personsConfigurator = new PersonsConfigurator(building);

        floors = floorConfigurator.configure();

        shafts = shaftConfigrator.configure();

        elevatorsConfigurator = new ElevatorConfigurator(shafts, building);

        elevators = elevatorsConfigurator.configure();

        persons = personsConfigurator.configure();

        for (Person person : persons) {
            person.setLogger(logger);
        }

        for (Elevator elevator : elevators) {
            elevator.setLogger(logger);
        }

        floorConfigurator.addPersons(persons);

    }
}
