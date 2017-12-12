package input;

import java.io.Console;
import java.util.Scanner;

import lift.Controller;
import models.HouseInfoModel;
import models.PassengerInfoModel;

public class DefaultInputConsole implements InputConsole {

	public HouseInfoModel getHouseInfo() {
		HouseInfoModel houseInfo = null;
		int floorNum = 0;
		int liftNum = 0;
		PassengerInfoModel[] passengers = null;
		int[] capacities = null;
		Boolean isRight = false;
		while (!isRight) {
			try {
				Controller.clrscreen();
				System.out.print("Enter data about house with format usage: \n\"Floor:[floor_num]"
						+ ":Lift:[lift_num]:[...capacity_for_each_lift...]:\nPass:[pass_number]:[start:dest]:[...as_much_passengers_as_pass_number...]:[start:dest]\"\n"
						+ "\nHere Passengers are not necessary. \n"
						+ "Here some samples for you to copy. Enjoy!\n"
						+ "Floor:3:Lift:3:8:8:8:Pass:2:0:1:0:2\n"
						+ "Floor:5:Lift:3:8:8:8:Pass:7:0:1:0:2:4:3:4:2:3:4:3:4:2:0\n"
						+ "Hope, you like it! :-)\n"
						+ "And now you may even just type \"1\" or \"2\" to set'em by default.\n"
						+ "P.S. I've hardcoded them specially for you\n");
				Scanner scanIn = new Scanner(System.in);
				String fullInput = scanIn.nextLine();
				if (fullInput.equals("1")) {
					fullInput = "Floor:3:Lift:3:8:8:8:Pass:2:0:1:0:2";
				}
				if (fullInput.equals("2")) {
					fullInput = "Floor:5:Lift:3:8:8:8:Pass:7:0:1:0:2:4:3:4:2:3:4:3:4:2:0";
				}
				String[] inputDivided = fullInput.split(":");
				if (!checkRightTotalInput(inputDivided)) continue;
				isRight = true;
				floorNum = Integer.parseInt(inputDivided[1]);
				liftNum = Integer.parseInt(inputDivided[3]);
				passengers = new PassengerInfoModel[Integer.parseInt(inputDivided[5+liftNum])];
				int j = 0;
				for (int i = 6 + liftNum; i < inputDivided.length; i+=2) {
					passengers[j++] = new PassengerInfoModel(Integer.parseInt(inputDivided[i]), Integer.parseInt(inputDivided[i+1]));
				}
				capacities = new int[liftNum];
				for (int i = 4; i < 4 + liftNum; i++) {
					capacities[i-4] = Integer.parseInt(inputDivided[i]);
				}
				//scanIn.close();
			}
			catch(Exception e) {
				System.out.printf("Congrats! You have an exception. Now you are exceptional person.\n"
						+ "Details: %s", e);
				isRight = false;
			}
		}
		houseInfo = new HouseInfoModel(floorNum, liftNum, passengers, capacities);
		return houseInfo;
	}
	
	public PassengerInfoModel[] getPassengersInfo() {
		PassengerInfoModel[] passengers = null;
		boolean isRight = false;
		while(!isRight) {
			try {
				System.out.print("Enter data about new passengers, like: \"Pass:[pass_number]:[start:dest]...\"\n"
						+ "Passengers now are necessary. But you may type \"Back\" to return without changes\n");
				Scanner scanIn = new Scanner(System.in);
				String passInput = scanIn.nextLine();
				if (passInput.toLowerCase().equals("back")) {
					isRight = true;
					continue;
				}
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
				System.out.printf("Congrats! You have an exception. Now you are exceptional person.\n"
						+ "Details: %s", e);
				e.printStackTrace();
				isRight = false;
			}
		}
		return passengers;
	}
	
	private boolean checkRightTotalInput(String[] inputData) {
		if (inputData == null) return false;
		if (inputData.length == 0) return false;
		if (!inputData[0].toLowerCase().equals("floor") || 
				!inputData[2].toLowerCase().equals("lift")) 
			return false;
		if (Integer.parseInt(inputData[1]) < 1 || Integer.parseInt(inputData[1]) >= 1000) return false;
		if (inputData.length == (4 + Integer.parseInt(inputData[3]))) 
			return true;
		if (Integer.parseInt(inputData[3]) <= 0) return false;
		for (int i = 4; i < 4 + Integer.parseInt(inputData[3]); i++) {
			if (Integer.parseInt(inputData[i]) <= 0) return false;
		}
		if (!inputData[4+Integer.parseInt(inputData[3])].toLowerCase().equals("pass"))
			return false;
		if (Integer.parseInt(inputData[5+Integer.parseInt(inputData[3])])*2 != inputData.length - 6 - Integer.parseInt(inputData[3]))
			return false;
		for (int i = 6 + Integer.parseInt(inputData[3]); i < (inputData.length - 1); i++) {
			if (Integer.parseInt(inputData[i]) < 0 || Integer.parseInt(inputData[i]) > (Integer.parseInt(inputData[1]) - 1))
				return false;
		}
		return true;
	}
	private boolean checkPassInput(String[] inputData) {
		if (inputData == null) return false;
		if (inputData.length == 0) return false;
		if (!inputData[0].toLowerCase().equals("pass")) return false;
		if (Integer.parseInt(inputData[1])*2 != (inputData.length - 2)) 
			return false;
		return true;
	}
}
