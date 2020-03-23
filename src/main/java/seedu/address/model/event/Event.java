package seedu.address.model.event;

import java.util.ArrayList;

/**
 * Container class for adding activity, time spent and place to a person or a group
 */
public class Event {

    /**
     * Represents the eventID for the next created group
     */
    private static int events = 0;
    private static ArrayList<Event> eventList = new ArrayList<>();

    private int eventId;
    private String activity;
    private String place;
    private int toAdd;
    //private int withGroup;
    //private int withPerson;
    private int time;

    public Event(String activity, String place, int toAdd, int time) {
        this.eventId = events++;
        this.activity = activity;
        this.place = place;
        this.toAdd = toAdd;
        this.time = time;
    }

    public int getEventId() {
        return this.eventId;
    }

    public String getActivity() {
        return this.activity;
    }

    public String getPlace() {
        return this.place;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    //public void addEvent(Event e) {
    //eventList.add(e);
    //}
}
