package seedu.address.model.person;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class is used to encompass a recent event,
 * consisting of a place and activity for display.
 */
public class RecentEvent {

    private final StringProperty place;
    private final StringProperty activity;
    private final StringProperty time;

    public RecentEvent(String place, String activity, String time) {
        this.place = new SimpleStringProperty(place);
        this.activity = new SimpleStringProperty(activity);
        this.time = new SimpleStringProperty(time);
    }

    public String getPlace() {
        return place.get();
    }

    public String getActivity() {
        return activity.get();
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty placeProperty() {
        return place;
    }

    public StringProperty activityProperty() {
        return activity;
    }

    public StringProperty timeProperty() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RecentEvent)) {
            return false;
        }
        return ((RecentEvent) o).getPlace().equals(this.getPlace())
                && ((RecentEvent) o).getActivity().equals(this.getActivity())
                && ((RecentEvent) o).getTime().equals(this.getTime());
    }
}
