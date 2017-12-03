package tadite.javase.elevatorSimulator.model.floor;

/**
 * Contains Slots
 */
public interface Floor {
    int getLevel();
    int getSlotsCount();
    Slot getSlot(int position);
}
