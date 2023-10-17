package cz.muni.fi.pv168.todo.project.ui.model;

import cz.muni.fi.pv168.todo.project.model.Interval;

import java.util.List;

public class IntervalTableModel extends BasicTableModel<Interval> {
    public IntervalTableModel() {
        columns = List.of(
                Column.editable("Name", String.class, Interval::getName, Interval::setName),
                Column.editable("Abbreviation", String.class, Interval::getAbbreviation, Interval::setAbbreviation),
                Column.editable("Duration in minutes", Integer.class, Interval::getDuration, Interval::setDuration)
        );
        rows.add(new Interval("School Lesson", "vh", 45));
    }
}
