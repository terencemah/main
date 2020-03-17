package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents the time spent with a Person in the address book.
 * Guarantees: time spent is always non-negative.
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS = "Time Spent must be a non-negative integer";

    private int timeSpent;

    public Time(int timeSpent) {
        requireNonNull(timeSpent);
        this.timeSpent = timeSpent;
    }

    @Override
    public String toString() {
        String output = "";
        output += timeSpent;
        return output;
    }

    /**
     * Access timeSpent variable
     * @return int timeSpent
     */
    public int getTimeSpent() {
        return timeSpent;
    }

    //    @Override
    //    public int hashCode() {
    //        return timeSpent.hashCode();
    //    }
}
