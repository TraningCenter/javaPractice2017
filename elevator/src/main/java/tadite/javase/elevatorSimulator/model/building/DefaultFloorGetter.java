package tadite.javase.elevatorSimulator.model.building;

import tadite.javase.elevatorSimulator.model.floor.Floor;
import tadite.javase.elevatorSimulator.model.passenger.FloorGetter;
import tadite.javase.elevatorSimulator.model.passenger.Passenger;

import java.util.List;
import java.util.Optional;

public class DefaultFloorGetter implements FloorGetter {
    private List<Floor> floors;

    public DefaultFloorGetter(List<Floor> floors){
        this.floors = floors;
    }

    @Override
    public Floor getCurrentFloor(Passenger passenger) {
        Optional<Floor> floorOptional = floors.stream().filter(floor -> floor.getLevel() == passenger.getCurrentLocation().getLevel()).findFirst();

        return floorOptional.get();
    }
}
