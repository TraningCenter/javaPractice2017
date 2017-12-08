package com.netcracker.unc.tools;


import java.util.Scanner;

public class UserInteractor {

    public static int inputInt(Scanner scanner, int min, int max) throws Exception {
        int i;
        String line = scanner.nextLine();
        try {
            i = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new Exception("Ошибка! Введите число");
        }
        if (i >= min && i <= max) {
            return i;
        } else {
            throw new Exception(String.format("Превышение диапазона. Введите число в диапазоне (%d;%d)", min, max));
        }
    }
}
