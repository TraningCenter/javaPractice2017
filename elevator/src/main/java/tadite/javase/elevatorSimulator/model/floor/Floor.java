package tadite.javase.elevatorSimulator.model.floor;

public interface Floor {
    int getLevel();
    int getSlotsCount();
    Slot getSlot(int position);
}
