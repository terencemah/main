package seedu.address.model;

import seedu.address.model.person.Person;
import seedu.address.model.group.Group;
import java.util.List;
import java.util.ArrayList;

public class Event {
    private List <Event> eventList  = new ArrayList<>();
    private String name;
    private Group grpName;
    private Person person;
    private int timeSpent;
    private Place place;
    private Activity activity;

    /**
     * Constructor for a new Event object
     * @param name
     * @param grpName
     * @param place
     * @param activity
     * @param timeSpent
     * returns an event with a group.
     */
    public Event(String name, Group grpName, Place place, Activity activity, int timeSpent) {
        this.name = name;
        this.grpName = grpName;
        this.place = place;
        this.activity = activity;
        this.timeSpent = timeSpent;
    }

    /**
     * Constructor for a new Event object
     * @param name
     * @param person
     * @param place
     * @param activity
     * @param timeSpent
     * returns an event with a person.
     */
    public Event(String name, Person person, Place place, Activity activity, int timeSpent) {
        this.name = name;
        this.person = person;
        this.place = place;
        this.activity = activity;
        this.timeSpent = timeSpent;
    }

    /**
     * Returns the name of the event.
     * @return a String representing the name of the event.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the group that the event was held with.
     * @return a group object representing the group.
     */
    public Group getGroup() {
        return this.grpName;
    }

    /**
     * Returns the person that the event was held with.
     * @return a person object representing person.
     */
    public Person getPerson() {
        return this.person;
    }

    /**
     * Returns the place where the event was held.
     * @return a Place object representing the place.
     */
    public Place getPlace() {
        return this.place;
    }

    /**
     * Returns the time spent on the event.
     * @return an int representing the time spent.
     */
    public int timeSpent() {
        return this.timeSpent;
    }

    /**
     * Adds event to the event list stored in this class
     * @param event
     */
    public void addEvent(Event event) {
        eventList.add(event);
    }
}

