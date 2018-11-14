package myFirstMVC;

import myFirstMVC.model.listGroups;
import myFirstMVC.model.listStudents;

import java.util.Date;

/**
 * Контроллер перехватывает событие извне и в соответствии
 * с заложенной в него логикой, реагирует на это событие изменяя
 * модель, посредством вызова соответствующего метода.
 */
public class controller {
    private listStudents modelStudent;
    private listGroups modelGroup;
    private view view;

    //Конструктор контроллера, в качестве полей принимает модель
    //и представление
    public controller(listStudents modelStudent, listGroups modelGroup, myFirstMVC.view view) {
        this.modelStudent = modelStudent;
        this.modelGroup = modelGroup;
        this.view = view;
    }

    //Метод добавления студента
    public void addNewStudent(String name, String group, Date dateOfEntry) {
        if (modelGroup.isThereAGroupNumber(group)) {
            modelStudent.addStudents(name, group, dateOfEntry);
        }else System.out.println("There is no such group");
    }

    //Метод на проверку существует ли студент с таким именем
    public boolean studentExists(String name) {
        return modelStudent.findStudent(name) != null;
    }

    //Метод удения студента по имени
    public void deleteStudentByName(String name) {
        modelStudent.deleteStByName(name);
    }

    //Метод удаления студента по порядковому номеру
    public void deleteStudentByNumber(int num) {
        modelStudent.deleteStByNum(num);
    }

    //Метод изменения имени студента
    public void setStudentName(String oldName, String newName) {
        modelStudent.findStudent(oldName).setName(newName);
    }

    //Метод получения имени студента под порядковым номером n
    public String getStudentName(int n) {
        return modelStudent.getStudent(n).getName();
    }

    //Метод изменения номеру группы студента с именем name
    public void setStudentGr(String name, String group) {
        if (modelGroup.isThereAGroupNumber(group)) {
            modelStudent.findStudent(name).setGrNumb(group);
        }else System.out.println("There is no such group");
    }

    //Метод изменения даты зачисления студента с именем name
    public void setStudentEntryDate(String name, Date newDate) {
        modelStudent.findStudent(name).setDateOfEntry(newDate);
    }

    //Метод получения номера группы студента под порядковым номером n
    public String getStudentGr(int n) {
        return modelStudent.getStudent(n).getGrNumb();
    }

    //Метод добавления факультета
    public void addNewGroup(String group, String faculty){
        modelGroup.addGroup(group, faculty);
    }

    public void deleteGroup(String group){
        modelGroup.
    }
    //Методы обновления представления
    public void updateViewST() {
        view.printStudentInfo(modelStudent);
    }
    public void updateViewGR() {
        view.printGroupInfo(modelGroup);
    }
}
