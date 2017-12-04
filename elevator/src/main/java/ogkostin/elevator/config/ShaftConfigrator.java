package ogkostin.elevator.config;

import ogkostin.elevator.model.Building;
import ogkostin.elevator.model.Shaft;

import java.util.ArrayList;
import java.util.List;

/**
 *  Creates a list of shafts with parameters,
 * which correspondents to the building.
 *
 *  @author Oleg Kostin
 */
public class ShaftConfigrator implements Configurator{

    private List<Shaft> shafts;
    private Building building;

    public ShaftConfigrator(Building building) {
        this.building = building;
    }

    private void addShaft(int number) {
        Shaft shaft = new Shaft();
        shaft.setLeftPadding(4 + (number - 1) * (building.getWidth() - 4) / building.getShaftCount());
        shaft.setNumber(number);
        shaft.setHeight(building.getHeight() - 2);
        shafts.add(shaft);
    }

    @Override
    public List<Shaft> configure() {
        this.building = building;
        shafts = new ArrayList<>();
        int count = building.getShaftCount();
        for (int i = 1; i <= count; i++) {
            addShaft(i);
        }
        return shafts;
    }
}
