package lift;

import java.util.Comparator;

public class FloorComparator implements Comparator<Floor> {

	public int compare(Floor f1, Floor f2) {
		return f1.getNumber() - f2.getNumber();
	}

}
