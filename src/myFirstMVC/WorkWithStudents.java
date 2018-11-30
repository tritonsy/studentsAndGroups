package myFirstMVC;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import myFirstMVC.model.ListStudents;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Вынесенная часть контроллера, которая работает со студентами
 */
public class WorkWithStudents {
    private Serialize ser = new Serialize();
    private Scanner in = new Scanner(System.in);

    public void addStudent(Controller controller, ListStudents modelStudent, SimpleDateFormat sdf) {
        String stName = "";
        boolean goodName = false;
        while (!goodName) {
            System.out.println("Enter Name of the student: ");
            stName = in.nextLine();
            if (!stName.trim().isEmpty()) {
                goodName = true;
            } else {
                System.out.println("Wrong name");
            }
        }
        String stGr = "";
        boolean goodGroup = false;
        while (!goodGroup) {
            System.out.println("Enter group of the student: ");
            stGr = in.nextLine();
            if (!stGr.trim().isEmpty()) {
                goodGroup = true;
            } else {
                System.out.println("Wrong group");
            }
        }
        LocalDate dateOfEntry = null;
        boolean goodDate = false;
        while (!goodDate) {
            System.out.println("Date of entry in format yyyy.mm.dd");
            String date = in.nextLine();
            try {
                if (!date.trim().isEmpty()) {
                    dateOfEntry = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                    if (dateOfEntry.isBefore(LocalDate.now().plusDays(1)) && dateOfEntry.isAfter(LocalDate.of(2010, 1, 1))) {
                        goodDate = true;
                    } else {
                        System.out.println("Date must be after 2010/01/01 and before tomorrow");
                    }
                } else {
                    System.out.println("Empty date");
                }
            } catch (DateTimeParseException ex) {
                System.out.println("Wrong date, try again!!!!");
            }
        }
        controller.addNewStudent(stName, stGr, dateOfEntry);
        Serialize.exportListST(modelStudent);
        controller.updateViewST();
    }

    public void deleteStudents(Controller controller, ListStudents modelStudent) {
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

    public void setStudent(Controller controller, ListStudents modelStudent, SimpleDateFormat sdf) {
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
                    String entry = tmpIn.nextLine();
                    LocalDate dateOfEntry = LocalDate.parse(entry);
                    controller.setStudentEntryDate(tmpName, dateOfEntry);
                    ser.exportListST(modelStudent);
                    controller.updateViewST();
                    break;
                default:
                    System.out.println("Incorrect info");
                    break;
            }
        } else System.out.println("There is no such students");
    }
}
