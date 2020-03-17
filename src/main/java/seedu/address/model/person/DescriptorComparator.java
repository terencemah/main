package seedu.address.model.person;

import java.util.Comparator;

public class DescriptorComparator implements Comparator<EventDescriptor> {
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

