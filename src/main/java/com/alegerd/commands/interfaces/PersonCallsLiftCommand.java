package com.alegerd.commands.interfaces;

import com.alegerd.model.Person;
import com.alegerd.model.interfaces.IPerson;

public class PersonCallsLiftCommand implements IPersonCommand{

    IPerson person;

    public PersonCallsLiftCommand(IPerson person) {
        this.person = person;
    }

    @Override
    public void setPerson(IPerson person) {
        this.person = person;
    }

    @Override
    public void execute() {
        person.callLift();
    }
}
