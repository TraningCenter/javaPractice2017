package input;

import java.util.Scanner;

import models.HouseInfoModel;
import models.PassengerInfoModel;

public interface InputConsole {
	HouseInfoModel getHouseInfo();
	PassengerInfoModel[] getPassengersInfo();
}
