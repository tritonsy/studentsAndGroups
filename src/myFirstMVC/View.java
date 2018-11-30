package myFirstMVC;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import myFirstMVC.model.Group;
import myFirstMVC.model.ListGroups;
import myFirstMVC.model.ListStudents;
import myFirstMVC.model.Student;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * В обязанности Представления входит отображение данных полученных от Модели.
 * Стоит отметить, что представление может только читать данные
 * В данном случае цикл проходится по всем студентам из списка,
 * последовательно выводя их на экран
 */
public class View {
    public void myInterface() throws IOException, ClassNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Scanner in = new Scanner(System.in);
        Scanner inn = new Scanner(System.in); // Иначе конфликтует со свитчем
        Scanner innn = new Scanner(System.in); // Иначе конфликтует со свитчем
        WorkWithStudents wws = new WorkWithStudents();
        WorkWithGroup wwg = new WorkWithGroup();
        Serialize ser = new Serialize();
        Deserialize deser = new Deserialize();
        ListStudents modelStudent = deser.uploadListST();
        ListGroups modelGroup = deser.uploadListGR();
        View View = new View();
        Controller controller = new Controller(modelStudent, modelGroup, View);
        boolean everythingIsOk = true;
        while (everythingIsOk) {
            System.out.println("1 - Students");
            System.out.println("2 - Groups");
            System.out.println("3 - Students DB");
            System.out.println("4 - Groups DB");
            System.out.println("5 - Upload file");
            System.out.println("6 - Search");
            System.out.println("7 - Quit");
            String buf1 = in.nextLine();
            switch (buf1) {
                case "1":
                    System.out.println("1 - Add Student");
                    System.out.println("2 - Delete Student");
                    System.out.println("3 - Select Student");
                    System.out.println("4 - Go back");
                    String buf2 = inn.nextLine();
                    while (!buf2.equalsIgnoreCase("4")) {
                        switch (buf2) {
                            case "1":
                                wws.addStudent(controller, modelStudent, sdf);
                                break;
                            case "2":
                                wws.deleteStudents(controller, modelStudent);
                                break;
                            case "3":
                                wws.setStudent(controller, modelStudent, sdf);
                                break;

                        }//buf2="";
                        System.out.println("1 - Add Student");
                        System.out.println("2 - Delete Student");
                        System.out.println("3 - Select Student");
                        System.out.println("4 - Go back");
                        buf2 = inn.nextLine();
                    }
                    break;
                case "2":
                    System.out.println("1 - Add group");
                    System.out.println("2 - Delete group");
                    System.out.println("3 - Go back");
                    String buf3 = inn.nextLine();
                    while (!buf3.equalsIgnoreCase("3")) {
                        switch (buf3) {
                            case "1":
                                wwg.addGroup(controller, modelGroup);
                                break;
                            case "2":
                                wwg.deleteGroup(controller, modelGroup);
                                break;
                            default:
                                System.out.println("Incorrect info");
                                break;
                        }
                        System.out.println("1 - Add group");
                        System.out.println("2 - Delete group");
                        System.out.println("3 - Go back");
                        buf3 = inn.nextLine();
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
                    System.out.println("3 - Go back");
                    String buf4 = innn.nextLine();
                    while (!buf4.equalsIgnoreCase("3")) {
                        switch (buf4) {
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
                        System.out.println("1 - Add students file");
                        System.out.println("2 - Add group file");
                        System.out.println("3 - Go back");
                        buf4 = innn.nextLine();
                    }
                    break;
                case "6":
                    System.out.println("Enter name/group/faculty/date(yyyy.MM.dd)");
                    boolean searching = true;
                    while (searching) {
                        String searchStr = inn.nextLine();
                        if (searchStr.equals("404")){
                            searching = false;
                        }else {
                            controller.findInfoFrom(searchStr);
                            System.out.println("Enter name/group/faculty/date(yyyy.MM.dd)");
                            System.out.println("Enter 404 to exit");
                        }
                    }
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

    public void printStudentInfo(ListStudents students) {
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

    public void printGroupInfo(ListGroups groups) {
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