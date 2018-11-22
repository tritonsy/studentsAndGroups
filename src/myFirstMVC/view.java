package myFirstMVC;

import myFirstMVC.model.listGroups;
import myFirstMVC.model.listStudents;

/**
 * В обязанности Представления входит отображение данных полученных от Модели.
 * Стоит отметить, что представление может только читать данные
 * В данном случае цикл проходится по всем студентам из списка,
 * последовательно выводя их на экран
 */
public class view {
    public void printStudentInfo(listStudents students) {
        for (int i = 0; i < students.sizeOfList(); i++) {
            System.out.println("Student: " + students.getStudent(i).getName());
            System.out.println("Group: " + students.getStudent(i).getGrNumb());
            System.out.printf("%1$s %2$td %2$tm %2$tY", "Date of entry:", students.getStudent(i).getDateOfEntry());
            System.out.println();
        }
    }

    public void printGroupInfo(listGroups groups) {
        for (int i = 0; i < groups.sizeOfList(); i++) {
            System.out.println("Group Number: " + groups.getGroup(i).getGroupNumber());
            System.out.println("Faculty: " + groups.getGroup(i).getFaculty());
        }
    }

}
