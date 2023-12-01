package cz.muni.fi.pv168.project.todoapp.ui.filter.matcher;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;

public class EventCategoryMatcher extends EntityMatcher<Event> {
    private final Category category;

    public EventCategoryMatcher(Category categories) {
        this.category = categories;
    }

    @Override
    public boolean evaluate(Event event) {
        return event.getCategories().stream().anyMatch((category) -> category.equals(this.category));
    }
}
