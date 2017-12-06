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
				try {
					console.printf("Enter data about house with format usage: \"Floor:[floor_num]:[...capacity_for_each_lift...]:"
							+ ":Lift:[lift_num]:Pass:[pass_number]:[start:dest]:[...as_much_passengers_as_pass_number...]:[start:dest]\"\n"
							+ "Here Passengers are not necessary. ");
					String fullInput = console.readLine();
					String[] inputDivided = fullInput.split(":");
					if (!checkRightTotalInput(inputDivided)) continue;
					isRight = true;
					floorNum = Integer.parseInt(inputDivided[1]);
					liftNum = Integer.parseInt(inputDivided[3]);
					passengers = new PassengerInfoModel[Integer.parseInt(inputDivided[5])+liftNum];
					int j = 0;
					for (int i = 6 + liftNum; i < inputDivided.length; i+=2) {
						passengers[j++] = new PassengerInfoModel(Integer.parseInt(inputDivided[i]), Integer.parseInt(inputDivided[i+1]));
					}
					capacities = new int[liftNum];
					for (int i = 4; i < 4 + liftNum; i++) {
						capacities[i-4] = Integer.parseInt(inputDivided[i]);
					}
				}
				catch(Exception e) {
					console.printf("Congrats! You have an exception. Now you are exceptional person.\n"
							+ "Details: %s", e);
					isRight = false;
				}
			}
			houseInfo = new HouseInfoModel(floorNum, liftNum, passengers, capacities);
		}
		return houseInfo;
	}
	
	public PassengerInfoModel[] getPassengersInfo() {
		Console console = System.console();
		PassengerInfoModel[] passengers = null;
		if (console != null) {
			boolean isRight = false;
			while(!isRight) {
				try {
					String passInput = console.readLine("Enter data about new passengers, like: \"Pass:[pass_number]:[start:dest]...\"\n"
							+ "PAssengers now are necessary. ");
					String[] passInputDivided = passInput.split(":");
					if (!checkPassInput(passInputDivided)) continue;
					isRight = true;
					int passNum = Integer.parseInt(passInputDivided[1]);
					passengers = new PassengerInfoModel[passNum];
					int j = 0;
					for (int i = 2; i < passInputDivided.length; i += 2) {
						passengers[j++] = new PassengerInfoModel(Integer.parseInt(passInputDivided[i]), Integer.parseInt(passInputDivided[i+1]));
					}
				}
				catch(Exception e) {
					console.printf("Congrats! You have an exception. Now you are exceptional person.\n"
							+ "Details: %s", e);
					isRight = false;
				}
			}
		}
		return passengers;
	}
	
	private boolean checkRightTotalInput(String[] inputData) {
		if (inputData == null) return false;
		if (inputData.length == 0) return false;
		if (inputData[0].toLowerCase() != "floor" || 
				inputData[2].toLowerCase() != "lift")
			return false;
		if (inputData.length == 3 + Integer.parseInt(inputData[2]))
			return true;
		if (inputData[4+Integer.parseInt(inputData[3])].toLowerCase() != "pass")
			return false;
		if (Integer.parseInt(inputData[5+Integer.parseInt(inputData[3])])*2 != inputData.length - 6 - Integer.parseInt(inputData[3]))
			return false;
		return true;
	}
	private boolean checkPassInput(String[] inputData) {
		if (inputData == null) return false;
		if (inputData.length == 0) return false;
		if (inputData[0].toLowerCase() != "pass") return false;
		if (Integer.parseInt(inputData[1])*2 != inputData.length - 2) return false;
		return true;
	}
}
