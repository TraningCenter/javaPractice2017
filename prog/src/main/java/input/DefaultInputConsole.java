package input;

import java.io.Console;

import models.HouseInfoModel;
import models.PassengerInfoModel;

public class DefaultInputConsole implements InputConsole {

	public HouseInfoModel getHouseInfo() {
		Console console = System.console();
		HouseInfoModel houseInfo = null;
		if (console != null) {
			int floorNum = 0;
			int liftNum = 0;
			PassengerInfoModel[] passengers = null;
			int[] capacities = null;
			Boolean isRight = false;
			while (!isRight) {
				console.printf("Enter data about house with format usage: \"Floor:[floor_num]:[...capacity_for_each_lift...]:"
						+ ":Lift:[lift_num]:Pass:[pass_number]:[start:dest]:[...as_much_passengers_as_pass_number...]:[start:dest]\"");
				String fullInput = console.readLine();
				String[] inputDivided = fullInput.split(":");
				if (!checkRightInput(inputDivided)) continue;
				isRight = true;
				floorNum = Integer.parseInt(inputDivided[1]);
				liftNum = Integer.parseInt(inputDivided[3]);
				passengers = new PassengerInfoModel[Integer.parseInt(inputDivided[5])];
				for (int i = 6 + liftNum; i < inputDivided.length; i+=2) {
					int j = 0;
					passengers[j++] = new PassengerInfoModel(Integer.parseInt(inputDivided[i]), Integer.parseInt(inputDivided[i+1]));
				}
				capacities = new int[liftNum];
				for (int i = 4; i < 4 + liftNum; i++) {
					capacities[i-4] = Integer.parseInt(inputDivided[i]);
				}
			}
			houseInfo = new HouseInfoModel(floorNum, liftNum, passengers, capacities);
		}
		return houseInfo;
	}
	
	private boolean checkRightInput(String[] inputData) {
		if (inputData[0].toLowerCase() != "floor" || 
				inputData[2].toLowerCase() != "lift" ||
				inputData[4+Integer.parseInt(inputData[3])].toLowerCase() != "pass")
			return false;
		if (Integer.parseInt(inputData[5+Integer.parseInt(inputData[3])])*2 != inputData.length - 6 - Integer.parseInt(inputData[3]))
			return false;
		return true;
	}
}
