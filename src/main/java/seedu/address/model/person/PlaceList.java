package seedu.address.model.person;

import java.util.List;

/**
 * A list of places
 */
public class PlaceList {
    public final List<String> placeList;

    public PlaceList(List<String> places) {
        this.placeList = places;
    }

    public List getPlaceList() {
        return this.placeList;
    }

    /**
     * Adds a place to the current placeList
     * @return new PlaceList
     */
    public PlaceList addPlace(String place) {
        List<String> current = this.placeList;
        current.add(place);
        return new PlaceList(current);
    }

    /**
     * Adds a place to the current placeList
     * @return new PlaceList
     */
    public PlaceList addPlaceList(List<String> xs) {
        List<String> toEdit = this.placeList;
        toEdit.addAll(xs);
        return new PlaceList(toEdit);
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

        if (!(other instanceof PlaceList)) {
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
