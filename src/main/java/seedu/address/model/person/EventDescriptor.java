package seedu.address.model.person;

import java.util.Comparator;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EventDescriptor {

    private final StringProperty name;
    private final IntegerProperty frequency;

    public EventDescriptor(String name) {
        this.name = new SimpleStringProperty(name);
        frequency = new SimpleIntegerProperty(1);
    }

    public EventDescriptor(String name, Integer frequency) {
        this.name = new SimpleStringProperty(name);
        this.frequency = new SimpleIntegerProperty(frequency);
    }

    public String getName() {
        return name.get();
    }

    public Integer getFrequency() {
        return frequency.get();
    }

    public void increment() {
        frequency.setValue(frequency.get() + 1);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty frequencyProperty() {
        return new SimpleStringProperty(Integer.toString(frequency.get()));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EventDescriptor)) {
            return false;
        }
        return ((EventDescriptor) o).getName().equals(this.getName())
                && ((EventDescriptor) o).getFrequency() == this.getFrequency();
    }
}

