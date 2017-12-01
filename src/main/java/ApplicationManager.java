import java.util.Scanner;

public class ApplicationManager {
    private static int countFloors;
    private static int countElevators;
    private static final int maxCountOfFloors = 10;
    private static final int maxCountOfElevators = 6;

    public static void main(String[] args) {
        inputConfiguration();
        System.out.println(countFloors);
        System.out.println(countElevators);
    }

    private static void inputConfiguration() {
        countFloors = tryInputInt("Введите количество этажей в доме: ", 2, maxCountOfFloors);
        countElevators = tryInputInt("Введите количество лифтов в доме: ", 1, maxCountOfElevators);
    }

    private static int tryInputInt(String msg, int min, int max) {
        Scanner in = new Scanner(System.in);
        int i;
        while (true) {
            System.out.print(msg);
            String line = in.nextLine();
            try {
                i = Integer.parseInt(line);
                if (i >= min && i <= max)
                    break;
                else
                    System.out.println(String.format("Введите число в диапазоне (%d;%d)", min, max));

            } catch (NumberFormatException nfe) {
                System.out.println("Ошибка! Введите число! ");
            }
        }
        return i;
    }

}
