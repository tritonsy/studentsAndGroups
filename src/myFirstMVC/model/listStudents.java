package myFirstMVC.model;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Класс, который реализует список из студентов (является дочерним
 * к классу Student)
 * Имеется только одно поле типа ArrayList, в котором хранятся студенты
 */
public class ListStudents extends Student implements Serializable {
    private static final long serialVersionUID = 1759266677557508159L;
    private ArrayList<Student> students = new ArrayList<>();

    /**
     * Метод добавления студента в список с проверкой на дубликаты
     *
     * @param name        ФИО студента
     * @param group       номер группы
     * @param dateOfEntry дата зачисления
     */
    public void addStudents(String name, String group, LocalDate dateOfEntry) {
        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setGrNumb(group);
        newStudent.setDateOfEntry(dateOfEntry);
        if (alreadyExists(name, group, dateOfEntry)) {
            System.out.println("Already exists");
        } else students.add(newStudent);
    }

    /**
     * Метод проверки на дубликат (если есть идентичный студент)
     *
     * @param name      ФИО студента
     * @param group     номер группы
     * @param entryDate дата поступления
     * @return возвращает true если идентичный студент есть, false в ином случае
     */
    public boolean alreadyExists(String name, String group, LocalDate entryDate) {
        for (Student student : students) {
            String stName = student.getName();
            String stGrNumb = student.getGrNumb();
            LocalDate stDate = student.getDateOfEntry();
            if (stName.equals(name) && stGrNumb.equals(group) && stDate.equals(entryDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который возвращает объект студента из списка по имени студента
     *
     * @param name ФИО студента (без учета регистра)
     * @return возвращает объект студента, если таковой существует, null в ином случае
     */
    public Student findStudent(String name) {
        for (Student student : students) {
            String stName = student.getName();
            if (stName.equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Находит студента по дате зачисления
     *
     * @param bufDate дата зачисления
     * @param temp    номер студента в списке
     * @return возвращает объект студента, если таковой существует, null в ином случае
     */
    public Student checkStudentByDate(LocalDate bufDate, int temp) {
        if (students.get(temp).getDateOfEntry().equals(bufDate)) {
            return students.get(temp);
        }
        return null;
    }

    /**
     * Находит студента по группе
     *
     * @param Gr   номер группы
     * @param temp номер студента в списке
     * @return возвращает объект студента, если таковой существует, null в ином случае
     */
    public Student checkStudentByGroup(String Gr, int temp) {
        if (students.get(temp).getGrNumb().equals(Gr)) {
            return students.get(temp);
        }
        return null;
    }

    /**
     * Метод удаления студента из списка по имени студента
     *
     * @param name ФИО студента
     */
    public void deleteStByName(String name) {
        students.remove(findStudent(name));
    }

    /**
     * Метод удаления студента по порядковому номеру
     *
     * @param num номер студента в списке
     */
    public void deleteStByNum(int num) {
        students.remove(num);
    }

    /**
     * Метод получения объекта студента по порядковому номеру
     *
     * @param n номер студента в списке
     * @return возвращает объект студента
     */
    public Student getStudent(int n) {
        return students.get(n);
    }

    /**
     * Метод получения размера списка
     *
     * @return возвращает размер списка
     */
    public int sizeOfList() {
        return students.size();
    }
}