package myFirstMVC;

import java.util.Date;

/**
 * Контроллер перехватывает событие извне и в соответствии
 * с заложенной в него логикой, реагирует на это событие изменяя
 * модель, посредством вызова соответствующего метода.
 */
public class controllerStudent {
    private modelListStudents model;
    private viewStudent view;

    //Конструктор контроллера, в качестве полей принимает модель
    //и представление
    public controllerStudent(modelListStudents model, viewStudent view) {
        this.model = model;
        this.view = view;
    }

    //Метод добавления студента
    public void addNewStudent(String name, String group, Date dateOfEntry) {
        model.addStudents(name, group, dateOfEntry);
    }

    //Метод на проверку существует ли студент с таким именем
    public boolean studentExists(String name) {
        return model.findStudent(name) != null;
    }

    //Метод удения студента по имени
    public void deleteStudentByName(String name) {
        model.deleteStByName(name);
    }

    //Метод удаления студента по порядковому номеру
    public void deleteStudentByNumber(int num) {
        model.deleteStByNum(num);
    }

    //Метод изменения имени студента
    public void setStudentName(String oldName, String newName) {
        model.findStudent(oldName).setName(newName);
    }

    //Метод получения имени студента под порядковым номером n
    public String getStudentName(int n) {
        return model.getStudent(n).getName();
    }

    //Метод изменения номеру группы студента с именем name
    public void setStudentGr(String name, String grNumb) {
        model.findStudent(name).setGrNumb(grNumb);
    }

    //Метод изменения даты зачисления студента с именем name
    public void setStudentEntryDate(String name, Date newDate) {
        model.findStudent(name).setDateOfEntry(newDate);
    }

    //Метод получения номера группы студента под порядковым номером n
    public String getStudentGr(int n) {
        return model.getStudent(n).getGrNumb();
    }

    //Метод обновления представления
    public void updateView() {
        view.printStudentInfo(model);
    }
}
