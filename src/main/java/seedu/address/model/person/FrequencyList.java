package seedu.address.model.person;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FrequencyList {

    private ObservableList<EventDescriptor> frequencyList = FXCollections.observableArrayList();
    private HashMap<String, Integer> uniqueNameList;

    public FrequencyList() {
        this.uniqueNameList = new HashMap<>();
    }

    public void add(String name) {
        if (uniqueNameList.containsKey(name)) {
            Integer oldValue = uniqueNameList.get(name);
            int listIndex = frequencyList.indexOf(new EventDescriptor(name, oldValue));
            frequencyList.get(listIndex).increment();
            uniqueNameList.replace(name, oldValue, oldValue + 1);
        } else {
            uniqueNameList.put(name, 1);
            frequencyList.add(new EventDescriptor(name));
        }
        frequencyList.sort(new DescriptorComparator());
    }

    public ObservableList<EventDescriptor> getFrequencyList() {
        return frequencyList;
    }
}
