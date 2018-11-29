package myFirstMVC.model;
/**
 * Created by Alexander K., Anastasia S., Michael O. on 16.11.2018.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class ListGroups extends Group implements Serializable {
    private static final long serialVersionUID = 1759266677557508159L;
    private ArrayList<Group> groups = new ArrayList<>();

    /**
     * Метод добавления группы
     *
     * @param group   номер группы
     * @param faculty факультет
     */
    public void addGroup(String group, String faculty) {
        Group newGroup = new Group();
        newGroup.setGrNumb(group);
        newGroup.setNameFac(faculty);
        if (alreadyExists(group, faculty)) {
            System.out.println("Already exists");
        } else groups.add(newGroup);
    }

    /**
     * Метод, который проверяет на дубликаты группы
     *
     * @param groupNum номер группы
     * @param faculty  факультет
     * @return возвращает true, если группа уже существует, false в ином случае
     */
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

    /**
     * Метод, который возвращает объект группы из списка групп
     *
     * @param groupN номер группы
     * @return возвращает объект группы, если таковая есть, null в ином случае
     */
    public Group findGroup(String groupN) {
        for (Group group : groups) {
            String bufGroup = group.getGroupNumber();
            if (bufGroup.equals(groupN)) {
                return group;
            }
        }
        return null;
    }

    /**
     * Метод удаления группы
     *
     * @param groupNum номер группы
     */
    public void deleteByGroup(String groupNum) {
        groups.remove(findGroup(groupNum));
    }

    /**
     * Метод полученя объекта группы по порядковому номеру
     *
     * @param n номер группы в списке
     * @return возвращает объект группы
     */
    public Group getGroup(int n) {
        return groups.get(n);
    }

    /**
     * Метод, который проверяет, есть ли группа с идентичным номером
     *
     * @param groupNum номер группы
     * @return возвращает true, если группа есть, false в ином случае
     */
    public boolean isThereAGroupNumber(String groupNum) {
        for (Group group : groups) {
            String stGrNumb = group.getGroupNumber();
            if (stGrNumb.equals(groupNum)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который проверяет, есть ли факультет с идентичным именем
     *
     * @param fac факультет
     * @return возвращает true, если факультет есть, false в ином случае
     */
    public boolean isThereAFaculty(String fac) {
        for (Group group : groups) {
            String stFac = group.getFaculty();
            if (stFac.equals(fac)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который находит группу из факультета
     *
     * @param fac  факультет
     * @param temp номер группы в списке
     * @return возвращает объект группы, если такая есть, null в ином случае
     */
    public Group checkGroupByFaculty(String fac, int temp) {
        if (groups.get(temp).getFaculty().equals(fac)) {
            return groups.get(temp);
        }
        return null;
    }

    /**
     * Метод, который возвращает размер списка
     *
     * @return возвращает размер спика
     */
    public int sizeOfList() {
        return groups.size();
    }

}
