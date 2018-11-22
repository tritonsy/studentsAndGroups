package myFirstMVC.model;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class listGroups extends Group implements Serializable {
    private static final long serialVersionUID = 1759266677557508159L;
    private ArrayList<Group> groups = new ArrayList<>();

    public void addGroup(String group, String faculty) {
        Group newGroup = new Group();
        newGroup.setGrNumb(group);
        newGroup.setNameFac(faculty);
        if (alreadyExists(group, faculty)) {
            System.out.println("Already exists");
        } else groups.add(newGroup);
    }

    public boolean alreadyExists(String groupNum, String faculty) {
        for (Group group : groups) {
            String stGrNumb = group.getGroupNumber();
            String fac = group.getFaculty();
            if (stGrNumb.equals(groupNum) && fac.equals(faculty)) {
                return true;
            }
        }
        return false;
    }

    //Метод, который возвращает объект группы из списка групп4
    public Group findGroup(String groupN) {
        for (Group group : groups) {
            String bufGroup = group.getGroupNumber();
            if (bufGroup.equals(groupN)) {
                return group;
            }
        }
        return null;
    }

    public void deleteByGroup(String groupNum) {
        groups.remove(findGroup(groupNum));
    }

    public Group getGroup(int n) {
        return groups.get(n);
    }

    public boolean isThereAGroupNumber(String groupNum) {
        for (Group group : groups) {
            String stGrNumb = group.getGroupNumber();
            if (stGrNumb.equals(groupNum)) {
                return true;
            }
        }
        return false;
    }

    public boolean isThereAFaculty(String fac) {
        for (Group group : groups) {
            String stFac = group.getFaculty();
            if (stFac.equals(fac)) {
                return true;
            }
        }
        return false;
    }

    public Group checkGroupByFaculty(String fac, int temp) {
        if (groups.get(temp).getFaculty().equals(fac)){
            return groups.get(temp);
        }
        return null;
    }

    public int sizeOfList() {
        return groups.size();
    }

    public Group getFacultyName(int i) {
        return groups.get(i);
    }
}
