package myFirstMVC;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import myFirstMVC.model.Group;
import myFirstMVC.model.ListGroups;
import myFirstMVC.model.ListStudents;
import myFirstMVC.model.Student;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Контроллер перехватывает событие извне и в соответствии
 * с заложенной в него логикой, реагирует на это событие изменяя
 * модель, посредством вызова соответствующего метода.
 */
public class Controller {
    private ListStudents modelStudent;
    private ListGroups modelGroup;
    private View View;

    //Конструктор контроллера, в качестве полей принимает модель
    //и представление
    public Controller(ListStudents modelStudent, ListGroups modelGroup, View View) {
        this.modelStudent = modelStudent;
        this.modelGroup = modelGroup;
        this.View = View;
    }

    //Метод добавления студента
    public void addNewStudent(String name, String group, LocalDate dateOfEntry) {
        if (modelGroup.isThereAGroupNumber(group)) {
            modelStudent.addStudents(name, group, dateOfEntry);
        } else System.out.println("There is no such group");
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
        } else System.out.println("There is no such group");
    }

    //Метод изменения даты зачисления студента с именем name
    public void setStudentEntryDate(String name, LocalDate newDate) {
        modelStudent.findStudent(name).setDateOfEntry(newDate);
    }

    //Метод получения номера группы студента под порядковым номером n
    public String getStudentGr(int n) {
        return modelStudent.getStudent(n).getGrNumb();
    }

    //Метод добавления факультета
    public void addNewGroup(String group, String faculty) {
        modelGroup.addGroup(group, faculty);
    }

    public void deleteGroup(String group) {
        modelGroup.deleteByGroup(group);
    }

    //Методы обновления представления
    public void updateViewST() {
        View.printStudentInfo(modelStudent);
    }

    public void updateViewGR() {
        View.printGroupInfo(modelGroup);
    }

    /**
     * Метод, который ищет информацию по атрибуту
     * @param bufString атрибут типа Sting (Дата, Факультет, Номер группы, Имя студента)
     */
    public void findInfoFrom(String bufString) {
        boolean isItDate = true;
        try {
            LocalDate.parse(bufString, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        } catch (Exception DateTimeParseException) {
            System.out.println("Please, enter correct date");
            isItDate = false;
        }
        if (isItDate) {
            for (int i = 0; i < modelStudent.sizeOfList(); i++) {
                if (modelStudent.checkStudentByDate(LocalDate.parse(bufString, DateTimeFormatter.ofPattern("yyyy.MM.dd")), i) != null)
                    View.printStudent(modelStudent.checkStudentByDate(LocalDate.parse(bufString, DateTimeFormatter.ofPattern("yyyy.MM.dd")), i));
            }
        } else if (modelGroup.isThereAGroupNumber(bufString)) {
            for (int i = 0; i < modelStudent.sizeOfList(); i++) {
                if (modelStudent.checkStudentByGroup(bufString, i) != null)
                    View.printStudent(modelStudent.checkStudentByGroup(bufString, i));
            }
        } else if (modelGroup.isThereAFaculty(bufString)) {
            for (int i = 0; i < modelGroup.sizeOfList(); i++) {
                if (modelGroup.checkGroupByFaculty(bufString, i) != null)
                    View.printGroup(modelGroup.checkGroupByFaculty(bufString, i));
            }
            for (int i = 0; i < modelStudent.sizeOfList(); i++) {
                for (int j = 0; j < modelGroup.sizeOfList(); j++) {
                    if ((modelStudent.getStudent(i).getGrNumb().equals(modelGroup.getGroup(j).getGroupNumber())) &&
                            (modelGroup.getGroup(j).getFaculty().equals(bufString))) {
                        View.printStudent(modelStudent.getStudent(i));
                    }
                }
            }
        } else if (modelStudent.findStudent(bufString) != null) {
            View.printStudent(modelStudent.findStudent(bufString));
        } else System.out.println("No info has been found");
    }

    ////добавление данных студентов из другого файла
    public void addNewFileStudents() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file name...");
        String nameFile = sc.nextLine();
        //BufferedInputStream bis = new BufferedInputStream()
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameFile));
        Object newListStudents = (ListStudents) ois.readObject();
        boolean flagempty = false;

        for (int i = 0; i < ((ListStudents) newListStudents).sizeOfList(); i++) {
            Student stbuf = (Student) ((ListStudents) newListStudents).getStudent(i);
            if (modelStudent.sizeOfList() == 0) {
                modelStudent.addStudents(stbuf.getName(), stbuf.getGrNumb(), stbuf.getDateOfEntry());
            } else if (!modelStudent.alreadyExists(stbuf.getName(), stbuf.getGrNumb(), stbuf.getDateOfEntry())) {
                modelStudent.addStudents(stbuf.getName(), stbuf.getGrNumb(), stbuf.getDateOfEntry());
            } else {
                System.out.println("Обнаружен дубликат студента, но он отсеялся :D");
            }
        }
    }

    ////добавление данных групп из другого файла
    public void addNewFileGroups() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file name...");
        String nameFile = sc.nextLine();
        //BufferedInputStream bis = new BufferedInputStream()
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameFile));
        Object newListGroups = (ListGroups) ois.readObject();
        boolean flagempty = false;
        for (int i = 0; i < ((ListGroups) newListGroups).sizeOfList(); i++) {
            Group grbuf = (Group) ((ListGroups) newListGroups).getGroup(i);
            if (modelGroup.sizeOfList() == 0) {
                modelGroup.addGroup(grbuf.getGroupNumber(), grbuf.getFaculty());
            } else if (!modelGroup.alreadyExists(grbuf.getGroupNumber(), grbuf.getFaculty()) || flagempty) {
                modelGroup.addGroup(grbuf.getGroupNumber(), grbuf.getFaculty());
            } else {
                System.out.println("Обнаружен дубликат группы, но он отсеялся :D");
            }
        }
    }
}
