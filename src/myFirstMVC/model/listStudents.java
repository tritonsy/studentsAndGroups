package myFirstMVC.model;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */
import myFirstMVC.model.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Класс, который реализует список из студентов (является дочерним
 * к классу Student)
 * Имеется только одно поле типа ArrayList, в котором хранятся студенты
 */
public class listStudents extends Student implements Serializable {
    private static final long serialVersionUID = 1759266677557508159L;
    private ArrayList<Student> students = new ArrayList<>();

    //Метод добавления студента в список с проверкой на дубликаты
    public void addStudents(String name, String group, Date dateOfEntry) {
        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setGrNumb(group);
        newStudent.setDateOfEntry(dateOfEntry);
        if (alreadyExists(name, group,dateOfEntry)) {
            System.out.println("Already exists");
        } else students.add(newStudent);
    }

    //Метод проверки на дубликат (если есть идентичный студент)
    public boolean alreadyExists(String name, String group, Date entryDate) {
        for (Student student : students) {
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
    public Student findStudent(String name) {
        for (Student student : students) {
            String stName = student.getName();
            if (stName.equals(name)) {
                return student;
            }
        }
        return null;
    }


    public boolean isThereADate(Date bufDate) {
        for (Student student : students) {
            Date stDate = student.getDateOfEntry();
            if (stDate.equals(bufDate)) {
                return true;
            }
        }
        return false;
    }

    public Student checkStudentByDate(Date bufDate, int temp) {
        if (students.get(temp).getDateOfEntry().equals(bufDate)){
            return students.get(temp);
        }
        return null;
    }

    public Student checkStudentByGroup(String Gr, int temp) {
        if (students.get(temp).getGrNumb().equals(Gr)){
            return students.get(temp);
        }
        return null;
    }

    //Метод удаления студента из списка по имени студента
    public void deleteStByName(String name) {
        students.remove(findStudent(name));
    }

    //Метод удаления студента по порядковому номеру
    public void deleteStByNum(int num) {
        students.remove(num);
    }

    //Метод получения объекта студента по порядковому номеру
    public Student getStudent(int n) {
        return students.get(n);
    }

    //Метод получения размера списка
    public int sizeOfList() {
        return students.size();
    }
}