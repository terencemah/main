package seedu.address.model.person;

import seedu.address.model.event.TempPlace;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of places
 */
public class PlaceList {
    public final List<String> placeList;
    private HashMap<String, Integer> placeSet = new HashMap<>();
    private ObservableList<TempPlace> observablePlaceList;

    public PlaceList(List<String> places) {
        this.placeList = places;
        for (String s: placeList) {
           if (placeSet.containsKey(s)) {
               int count = placeSet.get(s);
               count += 1;
               placeSet.put(s, count);
           } else {
               placeSet.put(s, 1);
           }
        }
        observablePlaceList = toObservablePlaceList();
    }

    public List<String> getPlaceList() {
        return this.placeList;
    }

    private HashMap<String, Integer> getPlaceSet() {
        return this.placeSet;
    }

    public ObservableList<TempPlace> getObservablePlaceList() {
        return this.observablePlaceList;
    }

    private ObservableList<TempPlace> toObservablePlaceList() {
        ObservableList<TempPlace> tmpLs = FXCollections.observableArrayList();
        Iterator itr = getPlaceSet().entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry mapElement = (Map.Entry) itr.next();
            tmpLs.add(new TempPlace((String) mapElement.getKey(), (int) mapElement.getValue()));
        }
        return tmpLs;
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
