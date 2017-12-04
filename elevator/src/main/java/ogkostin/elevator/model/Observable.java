package ogkostin.elevator.model;

/**
 * Register observer
 * for getting notifications
 *
 */
public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
