package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;

public class UniqueGroupList {

    private HashSet<Group> groups;

    public UniqueGroupList() {
        this.groups = new HashSet<>();
    }

    public boolean contains(Group toCheck) {
        requireNonNull(toCheck);
        return this.groups.contains(toCheck);
    }

    public void addGroup(Group toAdd) {
        requireNonNull(toAdd);
        this.groups.add(toAdd);
    }

    public void removeGroup(Group toRemove){
        requireNonNull(toRemove);
        this.groups.remove(toRemove);
    }
}
