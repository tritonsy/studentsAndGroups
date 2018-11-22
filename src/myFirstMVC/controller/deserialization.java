package myFirstMVC.controller;

import myFirstMVC.model.listGroups;
import myFirstMVC.model.listStudents;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class deserialization implements Serializable {
    //десериализация
    public listStudents uploadListST() {
        listStudents allStuds = new listStudents();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("StudentsDB.ser"))) {
            allStuds = (listStudents) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return allStuds;
    }
    //десериализация
    public listGroups uploadListGR() {
        listGroups groups = new listGroups();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("GroupsDB.ser"))) {
            groups = (listGroups) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return groups;
    }
}
