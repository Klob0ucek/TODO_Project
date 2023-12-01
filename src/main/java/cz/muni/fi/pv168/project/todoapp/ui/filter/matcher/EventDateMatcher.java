package cz.muni.fi.pv168.project.todoapp.ui.filter.matcher;

import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import java.time.LocalDate;

public class EventDateMatcher extends EntityMatcher<Event> {
    private LocalDate from;
    private LocalDate to;

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    @Override
    public boolean evaluate(Event event) {
        LocalDate date = event.getDate();
        if (date == null || (from == null && to == null)) {
            return true;
        }
        if (from == null) {
            return date.isBefore(to);
        }
        if (to == null) {
            return date.isAfter(from);
        }

        return date.isAfter(from) && date.isBefore(to);
    }
}
