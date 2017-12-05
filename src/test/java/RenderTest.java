import com.alegerd.model.Floor;
import com.alegerd.model.House;
import com.alegerd.model.Lift;
import com.alegerd.model.Person;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.ILift;
import com.alegerd.model.interfaces.IPerson;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class RenderTest extends Assert{

    private House makeHouse(List<IFloor> floors, List<ILift> lifts){
        int floorNum = 10;
        int liftNum = 1;
        List<IPerson> people = new ArrayList<>();

        for (int i = 0; i < floorNum; i++) {
            floors.add(new Floor(i));
        }
        for (int i = 0; i < liftNum; i++) {
            lifts.add(new Lift(i,floors,1000));
        }

        people.add(new Person(1,45,2,3));
        people.add(new Person(2,45,6,2));

        for (IFloor floor : floors) {
            for (IPerson person : people) {
                if (floor.getNumber().equals(person.getFloorNumber())){
                    floor.addWaitingPerson(person);
                }
            }
        }

        return new House(floors, lifts);
    }

}
