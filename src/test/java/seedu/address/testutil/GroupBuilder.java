package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Time;

/**
 * A utility class to help with building Group Objects.
 */
public class GroupBuilder {

    public static final String DEFAULT_NAME = "SoC Friends";
    public static final int DEFAULT_ID = 1;
    public static final int DEFAULT_MINS = 1;
    public static final int DEFAULT_HOURS = 1;
    public static final ArrayList<Integer> DEFAULT_LIST = new ArrayList<>();

    private Name name;
    private int id;
    private Time timeSpent;
    private ArrayList<Integer> memberIds;
    private ArrayList<Integer> eventIDs;

    public GroupBuilder() {
        this.name = new Name(DEFAULT_NAME);
        this.id = DEFAULT_ID;
        this.timeSpent = new Time(DEFAULT_MINS, DEFAULT_HOURS);
        this.memberIds = DEFAULT_LIST;
        this.eventIDs = DEFAULT_LIST;
    }

    public GroupBuilder(Group groupToCopy) {
        name = groupToCopy.getName();
        id = groupToCopy.getGroupId();
        timeSpent = groupToCopy.getTimeSpent();
        memberIds = groupToCopy.getMembers();
        eventIDs = groupToCopy.getEvents();
    }

    /**
     * Sets the code for name.
     * @param name
     * @return
     */
    public GroupBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the code for group Id.
     * @param id
     * @return
     */
    public GroupBuilder withId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the code for time.
     * @param time
     * @return
     */
    public GroupBuilder withTime(String time) {
        this.timeSpent = ParserUtil.parseTime(time);
        return this;
    }

    /**
     * Sets the code for withMembers.
     * @param memberIds
     * @return
     */
    public GroupBuilder withMembers(ArrayList<Integer> memberIds){
        this.memberIds = memberIds;
        return this;
    }

    /**
     * Sets the code for withEvents.
     * @param eventIDs
     * @return
     */
    public GroupBuilder withEvents(ArrayList<Integer> eventIDs) {
        this.eventIDs = eventIDs;
        return this;
    }

    /**
     * Builds a group with params in this class.
     * @return
     */
    public Group build() {
        Group group = new Group(name);
        group.setGroupId(id);
        group.setTimeSpent(timeSpent);
        group.setMemberIDs(memberIds);
        group.setEventIDs(eventIDs);
        return group;
    }

}
