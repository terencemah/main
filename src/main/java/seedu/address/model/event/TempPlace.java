package seedu.address.model.event;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TempPlace {
    StringProperty name;
    IntegerProperty times;

    public TempPlace(String name, int times) {
        this.name = new SimpleStringProperty(name);
        this.times = new SimpleIntegerProperty(times);
    }

    public StringProperty getNameProperty() {
        return this.name;
    }

    public StringProperty getTimesProperty() {
        return new SimpleStringProperty(this.times.toString()) ;
    }

    @Override
    public String toString() {
        return this.name + " " + this.times;
    }
}
