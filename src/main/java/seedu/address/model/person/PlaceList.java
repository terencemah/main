package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

public class PlaceList {
    public ArrayList<String> placeList = new ArrayList<>();
    public final List<String> value;

    public PlaceList(List<String> places) {
        this.value = places;
    }

    public ArrayList getPlaceList() {
        return this.placeList;
    }

    public void addPlace(String place) {
        this.value.add(place);
    }

    @Override
    public String toString() {
        String output = "";
        int i = 0;
        for (String s : placeList) {
            if (i < placeList.size() - 1) {
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

        if(!(other instanceof  PlaceList)) {
            return false;
        }

        PlaceList p = (PlaceList) other;
        return this.placeList.equals(p.placeList);
    }

    @Override
    public int hashCode() {
        return placeList.hashCode();
    }
}
