package entities;

import org.junit.Test;

import lift.Lift;
import lift.LiftDirection;

import org.junit.Assert;

public class LiftTest {
	@Test
	public void testLift() {
		Lift lift = new Lift();
		Assert.assertEquals(1, lift.getId());
		lift.setDirection(LiftDirection.UP);
		Assert.assertEquals(lift.getLiftDirection(), LiftDirection.UP);
		lift.switchDirection();
		Assert.assertEquals(lift.getLiftDirection(), LiftDirection.DOWN);
	}
}
