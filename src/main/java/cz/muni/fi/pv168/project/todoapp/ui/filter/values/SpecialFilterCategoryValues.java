package cz.muni.fi.pv168.project.todoapp.ui.filter.values;

import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EntityMatcher;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EntityMatchers;
import java.util.Objects;

public enum SpecialFilterCategoryValues {
    ALL(EntityMatchers.all());

    private final EntityMatcher<Event> matcher;

    SpecialFilterCategoryValues(EntityMatcher<Event> matcher) {
        this.matcher = Objects.requireNonNull(matcher, "matcher cannot be null");
    }

    public EntityMatcher<Event> getMatcher() {
        return matcher;
    }
}
