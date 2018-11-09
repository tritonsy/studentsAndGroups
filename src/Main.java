import myFirstMVC.controllerStudent;
import myFirstMVC.modelListStudents;
import myFirstMVC.modelStudent;
import myFirstMVC.viewStudent;

import java.io.*;
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
        modelListStudents model = uploadList();
        viewStudent view = new viewStudent();
        controllerStudent controller = new controllerStudent(model, view);
        boolean everythingIsOk = true;
        while (everythingIsOk) {
            System.out.println("1 - Add Student");
            System.out.println("2 - Delete Student");
            System.out.println("3 - Select student");
            System.out.println("4 - Students DB");
            System.out.println("5 - Quit");
            switch (in.nextLine()) {
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
                        exportList(model);
                        controller.updateView();
                        break;

                    } catch (ParseException e) {
                        System.out.println("Wrong date");
                    }
                    break;
                case "2":
                    System.out.println("1 - Delete by number");
                    System.out.println("2 - Delete by name");
                    switch (inn.nextLine()) {
                        case "1":
                            controller.deleteStudentByNumber(inn.nextInt());
                            exportList(model);
                            controller.updateView();
                            break;

                        case "2":
                            System.out.println("Enter name of the student: ");
                            controller.deleteStudentByName(inn.nextLine());
                            exportList(model);
                            controller.updateView();
                            break;
                        default:
                            System.out.println("Incorrect info");
                            break;
                    }
                case "3":
                    System.out.println("Please, enter name of wanted student: ");
                    String tmpName = inn.nextLine();
                    if (controller.studentExists(tmpName)) {
                        System.out.println("1 - Set name");
                        System.out.println("2 - Set group");
                        System.out.println("2 - Set entry date");
                        switch (innn.nextLine()) {
                            case "1":
                                System.out.println("Enter new name: ");
                                controller.setStudentName(tmpName, innn.nextLine());
                                exportList(model);
                                controller.updateView();
                                break;
                            case "2":
                                System.out.println("Enter new group: ");
                                controller.setStudentGr(tmpName, innn.nextLine());
                                exportList(model);
                                controller.updateView();
                                break;
                            case "3":
                                System.out.println("Enter new date yyyy.mm.dd: ");
                                try {
                                    String entry = in.nextLine();
                                    Date dateOfEntry = sdf.parse(entry);
                                    controller.setStudentEntryDate(tmpName, dateOfEntry);
                                    exportList(model);
                                    controller.updateView();
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
                    break;
                case "4":
                    controller.updateView();
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
        //  modelListStudents stud = new modelListStudents();
        //  modelStudent studi = new modelStudent();
        //  modelStudent studi2 = new modelStudent();
        //  studi2.setName("Lolich");
        //  studi2.setGrNumb("Kekich");
        //  studi.setName("Lol");
        //  studi.setGrNumb("Kek");
        //  stud.addStudents(createNewStudent());
        //  stud.addStudents(createNewStudent());
        //  System.out.println(stud.sizeOfList());
        //  System.out.println(stud.getStudent(1).getName());

    }

    private static void exportList(modelListStudents allStuds) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("MyData.ser"))) {
            out.writeObject(allStuds);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static modelListStudents uploadList() {
        modelListStudents allStuds = new modelListStudents();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("MyData.ser"))) {
            allStuds = (modelListStudents) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return allStuds;
    }

}
