package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Place;

/**
 * Jackson-friendly version of {@link Place}.
 */
class JsonAdaptedPlace {

    private final String placeName;

    /**
     * Constructs a {@code JsonAdaptedPlace} with the given {@code placeName}.
     */
    @JsonCreator
    public JsonAdaptedPlace(String placeName) {
        this.placeName = placeName;
    }

    /**
     * Converts a given {@code Place} into this class for Jackson use.
     */
    public JsonAdaptedPlace(Place source) {
        placeName = source.placeName;
    }

    @JsonValue
    public String getPlaceName() {
        return placeName;
    }

    /**
     * Converts this Jackson-friendly adapted place object into the model's {@code Place} object.
     *
     */
    public Place toModelType() throws IllegalValueException {
        return new Place(placeName);
    }
}
