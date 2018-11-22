package myFirstMVC.view;

import myFirstMVC.controller.controller;
import myFirstMVC.controller.serialization;
import myFirstMVC.model.listGroups;

import java.util.Scanner;

public class workWithGroup {
    serialization ser = new serialization();
    Scanner in = new Scanner(System.in);

    public void addGroup(controller controller, listGroups modelGroup){
        System.out.println("Enter number of group: ");
        String Grou = in.nextLine();
        System.out.println("Enter name of faculty: ");
        String Facul = in.nextLine();
        controller.addNewGroup(Grou, Facul);
        ser.exportListGR(modelGroup);
        controller.updateViewGR();
    }

    public void deleteGroup(controller controller,listGroups modelGroup){
        System.out.println("Enter number of group: ");
        String tempGr = in.nextLine();
        controller.deleteGroup(tempGr);
        ser.exportListGR(modelGroup);
        controller.updateViewGR();
    }
}
