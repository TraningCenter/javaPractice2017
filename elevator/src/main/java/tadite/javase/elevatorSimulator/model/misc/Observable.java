package tadite.javase.elevatorSimulator.model.misc;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
