package ogkostin.elevator.model;

/**
 * Do action when observable send notification
 */
public interface Observer {
    void update (Integer floorNumber, Direction direction,Elevator elevator);
}
