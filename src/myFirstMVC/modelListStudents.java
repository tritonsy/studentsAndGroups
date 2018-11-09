package myFirstMVC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Класс, который реализует список из студентов (является дочерним
 * к классу modelStudent)
 * Имеется только одно поле типа ArrayList, в котором хранится студенты
 */
public class modelListStudents extends modelStudent implements Serializable {
    private static final long serialVersionUID = 1759266677557508159L;
    private ArrayList<modelStudent> students = new ArrayList<>();

    //Метод добавления студента в список с проверкой на дубликаты
    public void addStudents(String name, String group, Date dateOfEntry) {
        modelStudent newStudent = new modelStudent();
        newStudent.setName(name);
        newStudent.setGrNumb(group);
        newStudent.setDateOfEntry(dateOfEntry);
        if (alreadyExists(name, group,dateOfEntry)) {
            System.out.println("Already exists");
        } else students.add(newStudent);
    }

    //Метод проверки на дубликат (если есть идентичный студент)
    public boolean alreadyExists(String name, String group, Date entryDate) {
        for (modelStudent student : students) {
            String stName = student.getName();
            String stGrNumb = student.getGrNumb();
            Date stDate = student.getDateOfEntry();
            if (stName.equals(name) && stGrNumb.equals(group)&&stDate.equals(entryDate)) {
                return true;
            }
        }
        return false;
    }

    //Метод, который возвращает объект студента из списка по имени студента
    public modelStudent findStudent(String name) {
        for (modelStudent student : students) {
            String stName = student.getName();
            if (stName.equals(name)) {
                return student;
            }
        }
        return null;
    }

    //Метод удаления студента из списка по имени студента
    public void deleteStByName(String name) {
        students.remove(findStudent(name));
    }

    //Метод удаления студента по порялковому номеру
    public void deleteStByNum(int num) {
        students.remove(num);
    }

    //Метод получения объекта студента по порядковому номеру
    public modelStudent getStudent(int n) {
        return students.get(n);
    }

    //Метод получения размера списка
    public int sizeOfList() {
        return students.size();
    }
}