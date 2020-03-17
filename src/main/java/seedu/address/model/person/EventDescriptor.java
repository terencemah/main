package seedu.address.model.person;

import java.util.Comparator;

public class EventDescriptor {
    private String name;
    private int frequency;

    public EventDescriptor(String name) {
        this.name = name;
        frequency = 1;
    }

    public EventDescriptor(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void increment() {
        frequency++;
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

class DescriptorComparator implements Comparator<EventDescriptor> {
    public int compare(EventDescriptor d1, EventDescriptor d2) {
        if (d1.getFrequency() < d2.getFrequency()) {
            return 1;
        } else if (d1.getFrequency() == d2.getFrequency()
                && d1.getName().compareTo(d2.getName()) < 0) {
            return 1;
        } else {
            return -1;
        }
    }
}


