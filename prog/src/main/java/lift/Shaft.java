package lift;

import java.util.*;

public class Shaft {
	private Lift lift;
	
	public Shaft() {
		lift = new Lift();
	}
	public Shaft(Lift lift) {
		this.lift = lift;
	}
	public Lift getLift() {
		return lift;
	}
}
