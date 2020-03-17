package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;

/**
 * Represents a unique list of all groups currently in CoderLifeInsights
 */
public class UniqueGroupList {

    private HashSet<Group> groups;

    /**
     * Constructor for UniqueGroupList.
     */
    public UniqueGroupList() {
        this.groups = new HashSet<>();
    }

    /**
     * Checks whether the list contains a given group.
     *
     * @param toCheck a group to check
     * @return boolean status
     */
    public boolean contains(Group toCheck) {
        requireNonNull(toCheck);
        return this.groups.contains(toCheck);
    }

    /**
     * Adds a given group to the list.
     *
     * @param toAdd group to be added
     */
    public void addGroup(Group toAdd) {
        requireNonNull(toAdd);
        this.groups.add(toAdd);
    }

    /**
     * Removes a given group from the list.
     *
     * @param toRemove group to be removed.
     */
    public void removeGroup(Group toRemove) {
        requireNonNull(toRemove);
        this.groups.remove(toRemove);
    }
}
