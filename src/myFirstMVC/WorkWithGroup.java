package myFirstMVC;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import myFirstMVC.model.ListGroups;
import java.util.Scanner;

public class WorkWithGroup {
    Serialize ser = new Serialize();
    Scanner in = new Scanner(System.in);

    public void addGroup(Controller controller,ListGroups modelGroup){
        String Grou ="";
        boolean goodGroup = false;
        while(!goodGroup) {
            System.out.println("Enter number of group: ");
            Grou = in.nextLine();
            if (!Grou.trim().isEmpty()){
                goodGroup=true;
            }else{
                System.out.println("Wrong number");
            }
        }
        String Facul="";
        boolean goodFuc = false;
        while(!goodFuc) {
            System.out.println("Enter name of faculty: ");
            Facul = in.nextLine();
            if (!Facul.trim().isEmpty()){
                goodFuc=true;
            }else{
                System.out.println("Wrong name");
            }
        }
        controller.addNewGroup(Grou, Facul);
        ser.exportListGR(modelGroup);
        controller.updateViewGR();
    }

    public void deleteGroup(Controller controller,ListGroups modelGroup){
        System.out.println("Enter number of group: ");
        String tempGr = in.nextLine();
        controller.deleteGroup(tempGr);
        ser.exportListGR(modelGroup);
        controller.updateViewGR();
    }
}
