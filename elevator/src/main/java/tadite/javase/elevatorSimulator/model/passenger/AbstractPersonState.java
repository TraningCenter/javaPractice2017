package tadite.javase.elevatorSimulator.model.passenger;

/**
 * Abstract class for person state pattern implementation
 */
public abstract class AbstractPersonState implements PersonState{
    private Person person;

    public AbstractPersonState(Person person){
        this.person = person;
    }

    protected Person getPerson(){
        return this.person;
    }

    public abstract void action();
}
