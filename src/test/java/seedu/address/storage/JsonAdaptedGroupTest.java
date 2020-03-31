package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.Group;
import seedu.address.model.person.Name;


public class JsonAdaptedGroupTest {

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        Group g1 = new Group(new Name("soc friends"));
        JsonAdaptedGroup group = new JsonAdaptedGroup(g1);
        assertEquals(g1, group.toModelType());
    }
}
