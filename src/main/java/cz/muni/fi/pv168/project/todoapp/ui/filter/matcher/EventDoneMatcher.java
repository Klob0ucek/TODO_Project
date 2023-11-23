package cz.muni.fi.pv168.project.todoapp.ui.filter.matcher;

import cz.muni.fi.pv168.project.todoapp.business.model.Event;

public class EventDoneMatcher extends EntityMatcher<Event> {
    private final boolean done;

    public EventDoneMatcher(boolean done) {
        this.done = done;
    }

    @Override
    public boolean evaluate(Event event) {
        return event.isDone() == done;
    }
}
