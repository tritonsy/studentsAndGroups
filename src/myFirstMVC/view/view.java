package myFirstMVC.view;

import myFirstMVC.controller.controller;
import myFirstMVC.controller.deserialization;
import myFirstMVC.controller.serialization;
import myFirstMVC.model.Group;
import myFirstMVC.model.Student;
import myFirstMVC.model.listGroups;
import myFirstMVC.model.listStudents;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * В обязанности Представления входит отображение данных полученных от Модели.
 * Стоит отметить, что представление может только читать данные
 * В данном случае цикл проходится по всем студентам из списка,
 * последовательно выводя их на экран
 */
public class view {
    public void myInterface() throws IOException, ClassNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Scanner in = new Scanner(System.in);
        Scanner inn = new Scanner(System.in); // Иначе конфликтует со свитчем
        Scanner innn = new Scanner(System.in); // Иначе конфликтует со свитчем
        workWithStudents wws = new workWithStudents();
        workWithGroup wwg = new workWithGroup();
        serialization ser = new serialization();
        deserialization deser = new deserialization();
        listStudents modelStudent = deser.uploadListST();
        listGroups modelGroup = deser.uploadListGR();
        view view = new view();
        controller controller = new controller(modelStudent, modelGroup, view);
        boolean everythingIsOk = true;
        while (everythingIsOk) {
            System.out.println("1 - Students");
            System.out.println("2 - Groups");
            System.out.println("3 - Students DB");
            System.out.println("4 - Groups DB");
            System.out.println("5 - Upload file");
            System.out.println("6 - Search");
            System.out.println("7 - Quit");
            switch (in.nextLine()) {
                case "1":
                    System.out.println("1 - Add Student");
                    System.out.println("2 - Delete Student");
                    System.out.println("3 - Select Student");
                    switch (inn.nextLine()) {
                        case "1":
                            wws.addStudent(controller,modelStudent,sdf);
                            break;
                        case "2":
                            wws.deletStudents(controller,modelStudent);
                            break;
                        case "3":
                            wws.setStudent(controller,modelStudent,sdf);
                            break;
                    }
                    break;
                case "2":
                    System.out.println("1 - Add group");
                    System.out.println("2 - Delete group");
                    switch (inn.nextLine()) {
                        case "1":
                            wwg.addGroup(controller,modelGroup);
                            break;
                        case "2":
                            wwg.deleteGroup(controller,modelGroup);
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
                    //System.out.println(controller.addNewFile().toString());
                    System.out.println("1 - Add students file");
                    System.out.println("2 - Add group file");
                    switch (innn.nextLine()){
                        case "1":
                            controller.addNewFileStudents();
                            ser.exportListST(modelStudent);
                            controller.updateViewST();
                            break;
                        case "2":
                            controller.addNewFileGroups();
                            ser.exportListGR(modelGroup);
                            controller.updateViewGR();
                            break;
                        default:
                            System.out.println("Incorrect info");
                            break;
                    }
                    break;
                case "6":
                    System.out.println("Enter name/group/faculty");
                    controller.findInfoFrom(in.nextLine());
                    break;
                case "7":
                    System.out.println("Bye");
                    everythingIsOk = false;
                    break;
                default:
                    System.out.println("Incorrect info");
                    break;
            }
        }
    }

    public void printStudentInfo(listStudents students) {
        for (int i = 0; i < students.sizeOfList(); i++) {
            System.out.println("Student: " + students.getStudent(i).getName());
            System.out.println("Group: " + students.getStudent(i).getGrNumb());
            System.out.printf("%1$s %2$td %2$tm %2$tY", "Date of entry:", students.getStudent(i).getDateOfEntry());
            System.out.println();
        }
    }
    public void printStudent(Student student) {
            System.out.println("Student: " + student.getName());
            System.out.println("Group: " + student.getGrNumb());
            System.out.printf("%1$s %2$td %2$tm %2$tY", "Date of entry:", student.getDateOfEntry());
            System.out.println();
    }

    public void printGroupInfo(listGroups groups) {
        for (int i = 0; i < groups.sizeOfList(); i++) {
            System.out.println("Group Number: " + groups.getGroup(i).getGroupNumber());
            System.out.println("Faculty: " + groups.getGroup(i).getFaculty());
        }
    }

    public void printGroup(Group group) {
            System.out.println("Group Number: " + group.getGroupNumber());
            System.out.println("Faculty: " + group.getFaculty());
    }



}
