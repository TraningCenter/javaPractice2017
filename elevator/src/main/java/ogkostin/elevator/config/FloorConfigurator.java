package ogkostin.elevator.config;

import ogkostin.elevator.model.Building;
import ogkostin.elevator.model.Floor;
import ogkostin.elevator.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 *  Creates a list of floors with parameters,
 * which correspondents to the building.
 *
 *  @author Oleg Kostin
 */
public class FloorConfigurator implements Configurator{

    private List<Floor> floors;
    private Building building;

    public FloorConfigurator(Building building) {
        this.building = building;
    }

    private void addFloor(int number) {
        Floor floor = new Floor();
        floor.setWidth(building.getWidth() - 2);
        floor.setNumber(number);
        floor.setHeight((building.getHeight() - 3) - (number - 1) * (building.getHeight() - 4) / building.getFloorCount());
        floors.add(floor);
    }

    @Override
    public List<Floor> configure() {
        this.building = building;
        floors = new ArrayList<>();
        int count = building.getFloorCount();
        for (int i = 1; i <= count; i++) {
            addFloor(i);
        }
        return floors;
    }

    public void addPersons(List<Person> persons) {
        for (Floor floor : floors) {
            for (Person person : persons) {
                if (person.getCurrentFloor() == floor.getNumber())
                    floor.setPersonsCount(floor.getPersonsCount() + 1);
            }
        }
    }
}
