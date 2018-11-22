package myFirstMVC.model;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import java.io.Serializable;

public class Group implements Serializable {
    private static final long serialVersionUID = -8915012820618963012L;
    private String groupNumber;
    private String faculty;

    public String getGroupNumber() {
        return this.groupNumber;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public void setNameFac(String faculty) {
        this.faculty = faculty;
    }

    public void setGrNumb(String group) {
        this.groupNumber = group;

    }
}