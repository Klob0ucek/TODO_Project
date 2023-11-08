package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.business.model.Interval;

import java.time.Duration;
import java.util.List;

public class IntervalTableModel extends BasicTableModel<Interval> {
    public IntervalTableModel() {
        columns = List.of(
                Column.editable("Name", String.class, Interval::getName, Interval::setName),
                Column.editable("Abbreviation", String.class, Interval::getAbbreviation, Interval::setAbbreviation),
                Column.editable("Duration", Duration.class, Interval::getDuration, Interval::setDuration)
        );
        rows.add(new Interval("School Lesson", "vh", Duration.ofMinutes(45)));
    }
}
