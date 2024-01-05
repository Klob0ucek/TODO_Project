package cz.muni.fi.pv168.project.todoapp.ui.filter;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudService;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EntityMatcher;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EntityMatchers;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EventCategoryMatcher;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EventDateMatcher;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EventDoneMatcher;
import cz.muni.fi.pv168.project.todoapp.ui.filter.matcher.EventDurationMatcher;
import cz.muni.fi.pv168.project.todoapp.ui.filter.values.SpecialFilterCategoryValues;
import cz.muni.fi.pv168.project.todoapp.ui.filter.values.SpecialFilterDoneValues;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.utils.Either;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.table.TableRowSorter;

/**
 * Class holding all filters for the ScheduleTable.
 */
public final class EventTableFilter {
    private final EventCompoundMatcher eventCompoundMatcher;

    public EventTableFilter(TableRowSorter<ScheduleTableModel> rowSorter, List<Event> events) {
        eventCompoundMatcher = new EventCompoundMatcher(rowSorter, events);
        rowSorter.setRowFilter(eventCompoundMatcher);
    }

    public void filterDone(Either<SpecialFilterDoneValues, Boolean> selectedItem) {
        selectedItem.apply(
                l -> eventCompoundMatcher.setDoneMatcher(l.getMatcher()),
                r -> eventCompoundMatcher.setDoneMatcher(new EventDoneMatcher(r))
        );
    }

    public void filterCategory(Either<SpecialFilterCategoryValues, Category> selectedItem) {
        selectedItem.apply(
                l -> eventCompoundMatcher.setCategoryMatcher(l.getMatcher()),
                r -> eventCompoundMatcher.setCategoryMatcher(new EventCategoryMatcher(r))
        );
    }

    public void filterLowerDuration(int lower) {
        eventCompoundMatcher.setLowerDuration(lower);
    }

    public void filterUpperDuration(int upper) {
        eventCompoundMatcher.setUpperDuration(upper);
    }

    public void filterFromDate(LocalDate fromDate) {
        eventCompoundMatcher.setFromDate(fromDate);
    }

    public void filterToDate(LocalDate toDate) {
        eventCompoundMatcher.setToDate(toDate);
    }

    public static int getLowestDuration(List<Event> events) {
        var min = events.stream().mapToLong(e -> e.getDuration().toMinutes()).min();
        return min.isEmpty() ? 0 : (int) min.getAsLong();
    }

    public static int getHighestDuration(List<Event> events) {
        var max = events.stream().mapToLong(e -> e.getDuration().toMinutes()).max();
        return max.isEmpty() ? 0 : (int) max.getAsLong();
    }

    /**
     * Container class for all matchers for the ScheduleTable.
     * <p>
     * This Matcher evaluates to true, if all contained {@link EntityMatcher} instances
     * evaluate to true.
     */
    private static class EventCompoundMatcher extends EntityMatcher<Event> {

        private final TableRowSorter<ScheduleTableModel> rowSorter;
        private EntityMatcher<Event> doneMatcher = EntityMatchers.all();
        private EntityMatcher<Event> categoryMatcher = EntityMatchers.all();
        private final EventDurationMatcher durationMatcher;
        private final EventDateMatcher dateMatcher;

        private EventCompoundMatcher(TableRowSorter<ScheduleTableModel> rowSorter, List<Event> events) {
            this.rowSorter = rowSorter;

            durationMatcher = new EventDurationMatcher(getLowestDuration(events), getHighestDuration(events));
            dateMatcher = new EventDateMatcher();
        }

        private void setDoneMatcher(EntityMatcher<Event> doneMatcher) {
            this.doneMatcher = doneMatcher;
            rowSorter.sort();
        }

        public void setCategoryMatcher(EntityMatcher<Event> categoryMatcher) {
            this.categoryMatcher = categoryMatcher;
            rowSorter.sort();
        }

        public void setLowerDuration(int lower) {
            durationMatcher.setLower(lower);
            rowSorter.sort();
        }

        public void setUpperDuration(int upper) {
            durationMatcher.setUpper(upper);
            rowSorter.sort();
        }

        public void setFromDate(LocalDate fromDate) {
            dateMatcher.setFrom(fromDate);
            rowSorter.sort();
        }

        public void setToDate(LocalDate toDate) {
            dateMatcher.setTo(toDate);
            rowSorter.sort();
        }

        @Override
        public boolean evaluate(Event event) {
            return Stream.of(doneMatcher, categoryMatcher, durationMatcher, dateMatcher)
                    .allMatch(m -> m.evaluate(event));
        }
    }
}
