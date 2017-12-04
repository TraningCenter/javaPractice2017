package Avilova.Lift;

import java.io.Console;


public class LiftWork {
    public static void main(String[] args) {
        Console console = System.console();
        //проверяем что консоль не null
        if(console!=null) {
            UserInterfase userInterfase = new UserInterfase(console);
            userInterfase.write();
            userInterfase.run();
        }
    }
}
