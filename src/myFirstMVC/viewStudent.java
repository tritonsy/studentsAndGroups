package myFirstMVC;

/**
 * В обязанности Представления входит отображение данных полученных от Модели.
 * Стоит отметить, что представление может только читать данные
 * В данном случае цикл проходится по всем студентам из списка,
 * последовательно выводя их на экран
 */
public class viewStudent {
    public void printStudentInfo(modelListStudents students) {
        for (int i = 0; i < students.sizeOfList(); i++) {
            System.out.println("Student: " + students.getStudent(i).getName());
            System.out.println("Group: " + students.getStudent(i).getGrNumb());
            System.out.println("Date of entry: " + students.getStudent(i).getDateOfEntry());
        }
    }
}
