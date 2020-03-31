package seedu.address.model.person.exceptions;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException() {
        super("Did not find the specified event");
    }
}
