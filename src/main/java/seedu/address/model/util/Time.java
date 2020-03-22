package seedu.address.model.util;

/**
 * Represents the time object that tracks the time spent for the user.
 */
public class Time {

    public static final String MINUTES_RANGE_ERROR = "Minutes must be within 0-59 (inclusive).";
    public static final String HOURS_RANGE_ERROR = "Hours must be 0 or more";
    private int minutes;
    private int hours;
    //private final String value;

    public Time(int minutes, int hours) throws IllegalArgumentException {
        if (!(minutes <= 59 && minutes >= 0)) {
            throw new IllegalArgumentException(MINUTES_RANGE_ERROR);
        } else if (hours < 0) {
            throw new IllegalArgumentException(HOURS_RANGE_ERROR);
        } else {
            this.minutes = minutes;
            this.hours = hours;
            //value = Integer.toString(hours) + (minutes);
        }
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getHours() {
        return this.hours;
    }

    public void setMinutes(int minutes) throws IllegalArgumentException {
        if (!(minutes <= 59 && minutes >= 0)) {
            throw new IllegalArgumentException(MINUTES_RANGE_ERROR);
        } else {
            this.minutes = minutes;
        }
    }

    public void setHours(int hours) throws IllegalArgumentException {
        if (hours < 0) {
            throw new IllegalArgumentException(HOURS_RANGE_ERROR);
        } else {
            this.hours = hours;
        }
    }

    //public String getValue() {
    //return this.value;
    //}

    @Override
    public String toString() {
        return String.format("%s:%s", this.hours, this.minutes);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Time)) {
            return false;
        }

        // state check
        Time t = (Time) other;
        return (this.minutes == (t.getMinutes())
                && this.hours == (t.getHours()));
    }

}
