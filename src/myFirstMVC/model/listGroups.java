package myFirstMVC.model;

import myFirstMVC.model.Group;

import java.io.Serializable;
import java.util.ArrayList;

public class listGroups extends Group implements Serializable {
    private static final long serialVersionUID = 1759266677557508159L;
    private ArrayList<Group> groups = new ArrayList<>();

    public void addGroup(String faculty, String group) {
        Group newGroup = new Group();
        newGroup.setNameFac(faculty);
        newGroup.setGrNumb(group);
        if (alreadyExists(faculty, group)) {
            System.out.println("Already exists");
        } else groups.add(newGroup);
    }

    public boolean alreadyExists(String faculty, String groupNum) {
        for (Group group : groups) {
            String fac = group.getFaculty();
            String stGrNumb = group.getGroupNumber();
            if (fac.equals(faculty) && stGrNumb.equals(groupNum)) {
                return true;
            }
        }
        return false;
    }

    public void delGroup(String group){
        groups.remove
    }
    public Group getGroup(int n){
        return groups.get(n);
    }

    public boolean isThereAGroupNumber(String groupNum){
        for (Group group : groups) {
            String stGrNumb = group.getGroupNumber();
            if (stGrNumb.equals(groupNum)) {
                return true;
            }
        }
        return false;
    }

    public int sizeOfList() {
        return groups.size();
    }

    public Group getFacultyName(int i) {
        return groups.get(i);
    }
}
