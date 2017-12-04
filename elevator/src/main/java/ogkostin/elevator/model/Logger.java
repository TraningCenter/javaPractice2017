package ogkostin.elevator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Print main information about application's execution
 * on the console
 *
 *  @author Oleg Kostin
 */
public class Logger  {

    private StringBuilder sb;

    private List<String> stringList = new ArrayList<>();

    public void append(String str) {
        if (stringList.size() > 15) {
            stringList.remove(0);

        }
        stringList.add(str);
    }

         public void print(){
             sb= new StringBuilder();
           for (String s : stringList) {
            sb.append(s);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
