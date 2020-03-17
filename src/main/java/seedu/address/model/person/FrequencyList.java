package seedu.address.model.person;

import java.util.ArrayList;
import java.util.HashMap;

public class FrequencyList {

    private ArrayList<EventDescriptor> frequencyList;
    private HashMap<String, Integer> uniqueNameList;

    public FrequencyList() {
        this.frequencyList = new ArrayList<>();
        this.uniqueNameList = new HashMap<>();
    }

    public void add(String name) {
        if (uniqueNameList.containsKey(name)) {
            int oldValue = uniqueNameList.get(name);
            int listIndex = frequencyList.indexOf(new EventDescriptor(name, oldValue));
            frequencyList.get(listIndex).increment();
            uniqueNameList.replace(name, oldValue, oldValue + 1);
        } else {
            uniqueNameList.put(name, 1);
            frequencyList.add(new EventDescriptor(name));
        }
        frequencyList.sort(new DescriptorComparator());
    }
}
