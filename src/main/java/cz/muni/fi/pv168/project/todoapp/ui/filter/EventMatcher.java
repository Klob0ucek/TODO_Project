package cz.muni.fi.pv168.project.todoapp.ui.filter;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.model.Event;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventMatcher implements Matcher<Event> {
    private Boolean isDoneCriteria;
    private String nameCriteria;
    private Set<Category> categoryCriteria;
    private LocalDate fromCriteria;
    private LocalDate toCriteria;

    public EventMatcher() {
        // ...
    }

    public EventMatcher setCriteria(
            /*@Nullable*/ Boolean isDone,
            /*@Nullable*/ String name,
            /*@Nullable*/ List<Category> categories,
            /*@Nullable*/ LocalDate from,
            /*@Nullable*/ LocalDate to
    ) {
        this.isDoneCriteria = isDone;
        this.nameCriteria = name == null ? null : name.toUpperCase();
        this.categoryCriteria = new HashSet<>(categories);
        this.fromCriteria = from;
        this.toCriteria = to;
        return this;
    }

    @Override
    public boolean match(Event other) {
        return (isDoneCriteria == null || other.isDone() == isDoneCriteria)
                && (nameCriteria == null || other.getName().toUpperCase().contains(nameCriteria))
                && (categoryCriteria == null || categoryCriteria.contains(other.getCategories().get(0)))
                /*                              ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                 * Category filtering is not defined.
                 * - filter by primary category?
                 * - filter events that contain all categories in criteria?
                 * - filter events that contain at least one category from criteria?
                 */
                && (fromCriteria == null || fromCriteria.isBefore(other.getDate()))
                && (toCriteria == null || toCriteria.isAfter(other.getDate()));
    }
}
