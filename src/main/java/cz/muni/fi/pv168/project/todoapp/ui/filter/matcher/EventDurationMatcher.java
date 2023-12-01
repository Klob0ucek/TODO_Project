package cz.muni.fi.pv168.project.todoapp.ui.filter.matcher;

import cz.muni.fi.pv168.project.todoapp.business.model.Event;

public class EventDurationMatcher extends EntityMatcher<Event> {
    private int lower;
    private int upper;

    public EventDurationMatcher(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public void setLower(int lower) {
        this.lower = lower;
    }

    public void setUpper(int upper) {
        this.upper = upper;
    }

    @Override
    public boolean evaluate(Event event) {
        long minutes = event.getDuration().toMinutes();
        return lower <= minutes && upper >= minutes;
    }
}
