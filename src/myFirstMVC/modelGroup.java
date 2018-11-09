package myFirstMVC;

import java.io.Serializable;

public class modelGroup implements Serializable {
    private static final long serialVersionUID = -8915012820618963012L;
    private int groupNumber;
    private String faculty;

    public int getGroupNumber() {
        return this.groupNumber;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public void setName(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

}