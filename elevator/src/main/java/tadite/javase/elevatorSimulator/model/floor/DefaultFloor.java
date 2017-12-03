package tadite.javase.elevatorSimulator.model.floor;

import java.util.List;

/**
 * Implements floor logic, have list of slots and level
 */
public class DefaultFloor implements Floor{
    private int level;
    private List<Slot> slots;

    public DefaultFloor(int level, List<Slot> slots) {
        this.level = level;
        this.slots = slots;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getSlotsCount() {
        return slots.size();
    }

    @Override
    public Slot getSlot(int position) {
        return slots.get(position);
    }
}
