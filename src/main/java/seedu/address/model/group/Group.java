package seedu.address.model.group;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.person.Name;
import seedu.address.model.person.Time;

/**
 * Represents the Social Group that a person is part of and spends time with.
 */
public class Group {

    /**
     * Represents the groupID for the next created group.
     */
    private static int groups = 1;

    private Name name;
    private int groupId;
    private Time timeSpent;
    private ArrayList<Integer> memberIDs;
    private ArrayList<Integer> eventIDs;

    public Group(Name name) {
        this.name = name;
        this.memberIDs = new ArrayList<>();
        this.eventIDs = new ArrayList<>();
        this.timeSpent = new Time(0, 0);
        this.groupId = groups;
        groups += 1;
    }

    public Name getName() {
        return this.name;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public Time getTimeSpent() {
        return this.timeSpent;
    }

    public void addPerson(int id) {
        this.memberIDs.add(id);
    }

    public void deletePerson(int id) {
        this.memberIDs.remove(Integer.valueOf(id));
    }

    public void setTimeSpent(Time time) {
        this.timeSpent = time;
    }

    public ArrayList<Integer> getMembers() {
        return this.memberIDs;
    }

    public void setMemberIDs(ArrayList<Integer> members) {
        this.memberIDs = members;
    }

    public ArrayList<Integer> getEvents() {
        return this.eventIDs;
    }

    public void setEventIDs(ArrayList<Integer> events) {
        this.eventIDs = events;
    }

    /**
     * Returns a string representation of member IDs.
     * @return string representation
     */
    public String printEventIds() {
        String result = "event IDs: ";
        for (int i = 0; i < this.eventIDs.size(); i++) {
            result += this.eventIDs.get(i) + " ";
        }
        return result;
    }

    /**
     * Returns a String output with all the names in a single line separated by whitespace.
     *
     * @return
     */
    public String printMemberList() {
        String build = "members: ";
        for (int i = 0; i < memberIDs.size(); i++) {
            build += memberIDs.get(i) + " ";
        }
        return build;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.memberIDs);
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

        return (this.getName().equals(g.getName()));
    }

    @Override
    public String toString() {
        if (this.memberIDs.isEmpty()) {
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
