package myFirstMVC.view;

import myFirstMVC.controller.controller;
import myFirstMVC.controller.serialization;
import myFirstMVC.model.listStudents;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class workWithStudents {
    serialization ser = new serialization();
    Scanner in = new Scanner(System.in);


    public void addStudent(controller controller, listStudents modelStudent, SimpleDateFormat sdf){
        System.out.println("Enter Name of the student: ");
        String stName = in.nextLine();
        System.out.println("Enter group of the student: ");
        String stGr = in.nextLine();
        try {
            System.out.println("Date of entry in format yyyy.mm.dd");
            String entry = in.nextLine();
            Date dateOfEntry = sdf.parse(entry);
            controller.addNewStudent(stName, stGr, dateOfEntry);
            ser.exportListST(modelStudent);
            controller.updateViewST();

        } catch (ParseException e) {
            System.out.println("Wrong date");
        }
    }

    public void deletStudents(controller controller,listStudents modelStudent){
        System.out.println("1 - Delete by number");
        System.out.println("2 - Delete by name");
        switch (in.nextLine()) {
            case "1":
                controller.deleteStudentByNumber(in.nextInt());
                ser.exportListST(modelStudent);
                controller.updateViewST();
                break;
            case "2":
                System.out.println("Enter name of the student: ");
                controller.deleteStudentByName(in.nextLine());
                ser.exportListST(modelStudent);
                controller.updateViewST();
                break;
        }
    }

    public void setStudent(controller controller,listStudents modelStudent,SimpleDateFormat sdf){
        System.out.println("Please, enter name of wanted student: ");
        String tmpName = in.nextLine();
        if (controller.studentExists(tmpName)) {
            System.out.println("1 - Set name");
            System.out.println("2 - Set group");
            System.out.println("2 - Set entry date");
            Scanner tmpIn = new Scanner(System.in); // Иначе конфликтует со свитчем
            switch (tmpIn.nextLine()) {
                case "1":
                    System.out.println("Enter new name: ");
                    controller.setStudentName(tmpName, tmpIn.nextLine());
                    ser.exportListST(modelStudent);
                    controller.updateViewST();
                    break;
                case "2":
                    System.out.println("Enter new group: ");
                    controller.setStudentGr(tmpName, tmpIn.nextLine());
                    ser.exportListST(modelStudent);
                    controller.updateViewST();
                    break;
                case "3":
                    System.out.println("Enter new date yyyy.mm.dd: ");
                    try {
                        String entry = tmpIn.nextLine();
                        Date dateOfEntry = sdf.parse(entry);
                        controller.setStudentEntryDate(tmpName, dateOfEntry);
                        ser.exportListST(modelStudent);
                        controller.updateViewST();
                        break;

                    } catch (ParseException e) {
                        System.out.println("Wrong date");
                    }
                    break;
                default:
                    System.out.println("Incorrect info");
                    break;
            }
        } else System.out.println("There is no such students");
    }
}
