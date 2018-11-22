/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import myFirstMVC.controller;
import myFirstMVC.model.listGroups;
import myFirstMVC.model.listStudents;
import myFirstMVC.view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Scanner in = new Scanner(System.in);
        Scanner inn = new Scanner(System.in); // Иначе конфликтует со свитчем
        Scanner innn = new Scanner(System.in); // Иначе конфликтует со свитчем
        listStudents modelStudent = uploadListST();
        listGroups modelGroup = uploadListGR();
        //System.out.println(modelStudent.getStudent(2));
        view view = new view();
        controller controller = new controller(modelStudent, modelGroup, view);
        boolean everythingIsOk = true;
        while (everythingIsOk) {
            System.out.println("1 - Students");
            System.out.println("2 - Groups");
            System.out.println("3 - Students DB");
            System.out.println("4 - Groups DB");
            System.out.println("5 - Quit");
            switch (in.nextLine()) {
                case "1":
                    System.out.println("1 - Add Student");
                    System.out.println("2 - Delete Student");
                    System.out.println("3 - Select Student");
                    switch (inn.nextLine()) {
                        case "1":
                            System.out.println("Enter Name of the student: ");
                            String stName = in.nextLine();
                            System.out.println("Enter group of the student: ");
                            String stGr = in.nextLine();
                            try {
                                System.out.println("Date of entry in format yyyy.mm.dd");
                                String entry = in.nextLine();
                                Date dateOfEntry = sdf.parse(entry);
                                controller.addNewStudent(stName, stGr, dateOfEntry);
                                exportListST(modelStudent);
                                controller.updateViewST();
                                break;

                            } catch (ParseException e) {
                                System.out.println("Wrong date");
                            }
                            break;

                        case "2":
                            System.out.println("1 - Delete by number");
                            System.out.println("2 - Delete by name");
                            switch (innn.nextLine()) {
                                case "1":
                                    controller.deleteStudentByNumber(inn.nextInt());
                                    exportListST(modelStudent);
                                    controller.updateViewST();
                                    break;

                                case "2":
                                    System.out.println("Enter name of the student: ");
                                    controller.deleteStudentByName(inn.nextLine());
                                    exportListST(modelStudent);
                                    controller.updateViewST();
                                    break;
                            }
                        case "3":
                            System.out.println("Please, enter name of wanted student: ");
                            String tmpName = inn.nextLine();
                            if (controller.studentExists(tmpName)) {
                                System.out.println("1 - Set name");
                                System.out.println("2 - Set group");
                                System.out.println("2 - Set entry date");
                                Scanner tmpIn = new Scanner(System.in); // Иначе конфликтует со свитчем
                                switch (tmpIn.nextLine()) {
                                    case "1":
                                        System.out.println("Enter new name: ");
                                        controller.setStudentName(tmpName, tmpIn.nextLine());
                                        exportListST(modelStudent);
                                        controller.updateViewST();
                                        break;
                                    case "2":
                                        System.out.println("Enter new group: ");
                                        controller.setStudentGr(tmpName, tmpIn.nextLine());
                                        exportListST(modelStudent);
                                        controller.updateViewST();
                                        break;
                                    case "3":
                                        System.out.println("Enter new date yyyy.mm.dd: ");
                                        try {
                                            String entry = tmpIn.nextLine();
                                            Date dateOfEntry = sdf.parse(entry);
                                            controller.setStudentEntryDate(tmpName, dateOfEntry);
                                            exportListST(modelStudent);
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
                    break;
                case "2":
                    System.out.println("1 - Add group");
                    System.out.println("2 - Delete group");
                    switch (inn.nextLine()) {
                        case "1":
                            System.out.println("Enter number of group: ");
                            String Grou = in.nextLine();
                            System.out.println("Enter name of faculty: ");
                            String Facul = in.nextLine();
                            controller.addNewGroup(Grou, Facul);
                            exportListGR(modelGroup);
                            controller.updateViewGR();
                            break;
                        case "2":
                            System.out.println("Enter number of group: ");
                            String tempGr = in.nextLine();
                            controller.deleteGroup(tempGr);
                            exportListGR(modelGroup);
                            controller.updateViewGR();
                            break;
                        default:
                            System.out.println("Incorrect info");
                            break;
                    }
                    break;
                case "3":
                    controller.updateViewST();
                    break;
                case "4":
                    controller.updateViewGR();
                    break;
                case "5":
                    System.out.println("Bye");
                    everythingIsOk = false;
                    break;
                default:
                    System.out.println("Incorrect info");
                    break;
            }
        }
    }

    private static void exportListST(listStudents allStuds) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("StudentsDB.ser"))) {
            out.writeObject(allStuds);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void exportListGR(listGroups allGroups) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("GroupsDB.ser"))) {
            out.writeObject(allGroups);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static listStudents uploadListST() {
        listStudents allStuds = new listStudents();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("StudentsDB.ser"))) {
            allStuds = (listStudents) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return allStuds;
    }

    private static listGroups uploadListGR() {
        listGroups groups = new listGroups();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("GroupsDB.ser"))) {
            groups = (listGroups) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return groups;
    }


}
