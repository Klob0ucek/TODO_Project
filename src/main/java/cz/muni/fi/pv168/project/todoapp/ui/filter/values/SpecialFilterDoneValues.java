package cz.muni.fi.pv168.project.todoapp.ui.filter.values;

import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EntityMatcher;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EntityMatchers;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EventDoneMatcher;

import java.util.Objects;

public enum SpecialFilterDoneValues {
    ALL(EntityMatchers.all()),
    PLANNED(new EventDoneMatcher(false)),
    DONE(new EventDoneMatcher(true));

    private final EntityMatcher<Event> matcher;

    SpecialFilterDoneValues(EntityMatcher<Event> matcher) {
        this.matcher = Objects.requireNonNull(matcher, "matcher cannot be null");
    }

    public EntityMatcher<Event> getMatcher() {
        return matcher;
    }
}
