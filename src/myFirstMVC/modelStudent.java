package myFirstMVC;

import java.io.Serializable;
import java.util.Date;

/**
 * Класс, описывающий студента, который имеет поля:
 * 1) ФИО
 * 2) Группа
 * 3) Дата зачисления
 */
public class modelStudent implements Serializable {
    private static final long serialVersionUID = -8915012820618963012L;
    private String fullName;
    private String grNumb;
    private Date dateOfEntry;

    //Метод получения ФИО студента
    public String getName() {
        return this.fullName;
    }

    //Метод изменения ФИО студента
    public void setName(String fullName) {
        this.fullName = fullName;
    }

    //Метод получения группы студента
    public String getGrNumb() {
        return this.grNumb;
    }

    //Метод изменения группы студента
    public void setGrNumb(String grNumb) {
        this.grNumb = grNumb;
    }

    //Метод получения даты зачисления
    public Date getDateOfEntry() {
        return this.dateOfEntry;
    }

    //Метод изменения даты зачисления
    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }
}