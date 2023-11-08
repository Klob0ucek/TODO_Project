package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.business.model.Interval;

import java.time.Duration;
import java.util.List;

public class IntervalTableModel extends BasicTableModel<Interval> {
    public IntervalTableModel() {
        columns = List.of(
                Column.readonly("Name", String.class, Interval::getName),
                Column.readonly("Abbreviation", String.class, Interval::getAbbreviation),
                Column.readonly("Duration", Duration.class, Interval::getDuration)
        );
        rows.add(new Interval("School Lesson", "vh", Duration.ofMinutes(45)));
    }
}
