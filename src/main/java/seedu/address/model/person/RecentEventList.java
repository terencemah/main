package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * An observable list of RecentEvent objects that may contain
 * repeated objects. This allows the list to be displayed
 * and updated instantly on the GUI.
 */
public class RecentEventList {

    public static final int NUM_EVENTS = 5;
    private ObservableList<RecentEvent> recentEventList = FXCollections.observableArrayList();

    /**
     * This method generates the list of the 5 most recent events
     * with the target person, from their PlaceList and ActivityList.
     * @param pl The target person's PlaceList.
     * @param al The target person's ActivityList.
     */
    public void generate(PlaceList pl, ActivityList al) {
        recentEventList.clear();
        int size = pl.getPlaceList().size();
        if (size < NUM_EVENTS) {
            for (int i = size - 1; i >= 0; i--) {
                recentEventList.add(new RecentEvent(pl.getPlaceList().get(i), al.getActivityList().get(i)));
            }
        } else {
            for (int i = 0; i < NUM_EVENTS; i++) {
                recentEventList.add(new RecentEvent(pl.getPlaceList().get(size - 1 - i),
                        al.getActivityList().get(size - 1 - i)));
            }
        }
    }

    public ObservableList<RecentEvent> getList() {
        return recentEventList;
    }
}
