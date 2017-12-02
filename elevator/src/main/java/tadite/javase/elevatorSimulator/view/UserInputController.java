package tadite.javase.elevatorSimulator.view;

import java.io.IOException;

public class UserInputController {

    private InputMenu inputMenu;

    public UserInputController(InputMenu inputMenu) {
        this.inputMenu = inputMenu;
    }

    public void start() {
        while(true) {
            showMenu();
            try {
                char ch = (char) System.in.read();
                inputMenu.execute(ch, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeMenu(InputMenu inputMenu){
        this.inputMenu=inputMenu;
    }

    private void showMenu() {
        System.out.println("****************MENU*****************");
        System.out.println(inputMenu.getCommandsLine());
        System.out.println("**************************************");
        System.out.println("Selection: ");
    }
}
