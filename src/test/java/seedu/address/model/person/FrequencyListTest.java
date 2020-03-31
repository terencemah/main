package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class FrequencyListTest {

    private FrequencyList list1;
    private FrequencyList list2;
    private ArrayList<String> strings;

    /**
     * Fills the test FrequencyLists with arbitrary strings.
     */
    public void fillLists() {
        strings = new ArrayList<>();
        this.strings.add("abc");
        this.strings.add("def");
        this.strings.add("ghi");
    }

    @Test
    public void generateTest() {
        fillLists();
        list1 = new FrequencyList();
        list2 = new FrequencyList();
        list1.generate(strings);
        list2.generate(strings);
        assertTrue(list1.equals(list2));
    }
}
