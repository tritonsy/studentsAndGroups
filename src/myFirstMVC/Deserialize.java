package myFirstMVC;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import myFirstMVC.model.ListGroups;
import myFirstMVC.model.ListStudents;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Deserialize implements Serializable {
    //десериализация
    public ListStudents uploadListST() {
        ListStudents allStuds = new ListStudents();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("StudentsDB.ser"))) {
            allStuds = (ListStudents) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return allStuds;
    }
    //десериализация
    public static ListGroups uploadListGR() {
        ListGroups groups = new ListGroups();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("GroupsDB.ser"))) {
            groups = (ListGroups) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return groups;
    }
}
