package myFirstMVC;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import myFirstMVC.model.ListGroups;
import myFirstMVC.model.ListStudents;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Сериализация в файл студентов и групп
 */
public class Serialize implements Serializable {
    ///сериализация
    public static void exportListST(ListStudents allStuds) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("StudentsDB.ser"))) {
            out.writeObject(allStuds);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    //сериализация
    public static void exportListGR(ListGroups allGroups) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("GroupsDB.ser"))) {
            out.writeObject(allGroups);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
