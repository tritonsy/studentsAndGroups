package myFirstMVC.controller;

import myFirstMVC.model.listGroups;
import myFirstMVC.model.listStudents;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class serialization implements Serializable {
    ///сериализация
    public void exportListST(listStudents allStuds) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("StudentsDB.ser"))) {
            out.writeObject(allStuds);
            out.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    //сериализация
    public void exportListGR(listGroups allGroups) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("GroupsDB.ser"))) {
            out.writeObject(allGroups);
            out.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
