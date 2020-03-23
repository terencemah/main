package seedu.address.model.group;

import seedu.address.model.person.Name;

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

    private Name name;
    private int groupId;
    private ArrayList<Integer> members;
    private float totalTime;

    public Group(Name name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.totalTime = 0;
        this.groupId = groups;
        groups += 1;
    }

    public Name getName() {
        return this.name;
    }

    public int getGroupId() {
        return this.groupId;
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

    /**
     * Returns a String output with all the names in a single line separated by whitespace.
     *
     * @return
     */
    public String printMemberList() {
        String build = "";
        for (int i = 0; i < members.size(); i++) {
            build += members.get(i) + " ";
        }
        return build;
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

    @Override
    public String toString() {
        if (this.members.isEmpty()) {
            return "group ID: " + this.groupId + ". Name: " + this.name;
        } else {
            return "group ID: "
                    + this.groupId
                    + ". Name: "
                    + this.name
                    + ". With members: \n"
                    + printMemberList();
        }
    }
}
