package ogkostin.elevator.controller;

import ogkostin.elevator.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage conduct of peoople(passengers)
 *
 *  @author Oleg Kostin
 */
public class PersonController implements Controller, Observer {

    private ButtonController btn;
    private List<Person> persons;
    private List<Person> personsInWay = new ArrayList<>();
    private List<Person> toRemove = new ArrayList<>();
    private List<Person> toRemoveWay = new ArrayList<>();
    private List<Elevator> elevators;
    private List<Floor> floors;
    private int personsNumber;
    private int completePersonsNumber = 0;

    public PersonController(List<Person> persons, List<Elevator> elevators, ButtonController btn, List<Floor> floors) {
        this.floors = floors;
        this.persons = persons;
        this.elevators = elevators;
        this.btn = btn;
        personsNumber = persons.size();
    }

    @Override
    public void update(Integer floor, Direction direction, Elevator elevator) {
        for (Person person : personsInWay) {
            if (person.getTargetFloor() == floor && elevator.getPersons().contains(person)) {
                person.goOut(elevator, floors.get(floor - 1));
                toRemoveWay.add(person);
                completePersonsNumber++;
            }
        }
        for (Person person : persons) {
            if ((person.getDirection() == direction || direction == Direction.none) && person.getCurrentFloor() == floor && !elevator.getPersons().contains(person)) {
                if (person.enter(elevator, floors.get(floor - 1))) {
                    cancelCall(person);
                    toRemove.add(person);
                    personsInWay.add(person);
                }
            }
        }
        persons.removeAll(toRemove);
        personsInWay.removeAll(toRemoveWay);
    }


    public void call() {
        for (Person person : persons) {
            btn.callFromFloor(person.getCurrentFloor());
        }
    }

    public void cancelCall(Person person) {

        btn.cancelCall(person.getCurrentFloor());

    }

    public boolean isCompleted() {
        if (completePersonsNumber == personsNumber)
            return true;
        else
            return false;
    }


    public void setPersonsNumber(int personsNumber) {
        this.personsNumber = personsNumber;
    }

    public void setCompletePersonsNumber(int completePersonsNumber) {
        this.completePersonsNumber = completePersonsNumber;
    }
}