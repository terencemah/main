package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

public class ActivityList {
    public ArrayList<String> activityList = new ArrayList<>();
    public final List<String> value;

    public ActivityList(List<String> places) {
        this.value = places;
    }

    public ArrayList getPlaceList() {
        return this.activityList;
    }

    public void addActivity(String activity) {
        this.value.add(activity);
    }

    @Override
    public String toString() {
        String output = "";
        int i = 0;
        for (String s : activityList) {
            if (i < activityList.size() - 1) {
                output += s + ", ";
            } else {
                output += s;
            }
        }
        return output;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if(!(other instanceof ActivityList)) {
            return false;
        }

        ActivityList p = (ActivityList) other;
        return this.activityList.equals(p.activityList);
    }

    @Override
    public int hashCode() {
        return activityList.hashCode();
    }
}
