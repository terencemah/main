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

    public RecentEvent(String place, String activity) {
        this.place = new SimpleStringProperty(place);
        this.activity = new SimpleStringProperty(activity);
    }

    public String getPlace() {
        return place.get();
    }

    public String getActivity() {
        return activity.get();
    }

    public StringProperty placeProperty() {
        return place;
    }

    public StringProperty activityProperty() {
        return activity;
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
                && ((RecentEvent) o).getActivity().equals(this.getActivity());
    }
}
