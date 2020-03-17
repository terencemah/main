package seedu.address.model.group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents the Social Group that a person is part of and spends time with.
 */
public class Group {

    /**
     * Represents the groupID for the next created group.
     */
    private static int groups = 0;

    private String name;
    private int groupId;
    private ArrayList<Integer> members;
    private float totalTime;

    public Group(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.totalTime = 0;
    }

    public void addPerson(int id) {
        this.members.add(id);
    }

    public void deletePerson(int id) {
        this.members.remove(Integer.valueOf(id));
    }

    public void addTime(float additionalTime) {
        this.totalTime += additionalTime;
    }

    public void subtractTime(float subtractTime) {
        this.totalTime -= subtractTime;
    }

    public float getTotalTime() {
        return this.totalTime;
    }

    public ArrayList<Integer> getMembers() {
        return this.members;
    }

    public int getGroups() {
        return groups;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.members);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Group)) {
            return false;
        }

        Group g = (Group) o;

        return (new HashSet<Integer>(this.getMembers())).equals(new HashSet<Integer>(g.getMembers()));

    }
}
