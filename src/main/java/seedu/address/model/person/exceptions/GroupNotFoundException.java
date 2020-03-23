package seedu.address.model.person.exceptions;
/**
 * Signals that the operation is unable to find the specified group.
 */
public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
        super("Operation would result in duplicate groups");
    }
}
