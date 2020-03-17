package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents the time spent with a Person in the address book.
 * Guarantees: time spent is always non-negative.
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS = "Time Spent must be a non-negative integer";

    public final int value;

    public Time(int timeSpent) {
        requireNonNull(timeSpent);
        value = timeSpent;
    }

    @Override
    public String toString() {
        String output = "";
        output += value;
        return output;
    }

    /**
     * Access timeSpent variable
     * @return int timeSpent
     */
    public int getTimeSpent() {
        return value;
    }

    //    @Override
    //    public int hashCode() {
    //        return timeSpent.hashCode();
    //    }
}
