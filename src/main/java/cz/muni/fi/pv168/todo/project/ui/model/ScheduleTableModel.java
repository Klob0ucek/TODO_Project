package cz.muni.fi.pv168.todo.project.ui.model;

import cz.muni.fi.pv168.todo.project.model.Category;
import cz.muni.fi.pv168.todo.project.model.CategoryColor;
import cz.muni.fi.pv168.todo.project.model.Event;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ScheduleTableModel extends BasicTableModel<Event> {
    public ScheduleTableModel() {
        columns = List.of(
                Column.editable("Done?", Boolean.class, Event::isDone, Event::setDone),
                Column.editable("Name", String.class, Event::getName, Event::setName),
                Column.editable("Category", List.class, Event::getCategories, Event::setCategories),
                Column.editable("Location", String.class, Event::getLocation, Event::setLocation),
                Column.editable("Date", LocalDate.class, Event::getDate, Event::setDate),
                Column.editable("Time", LocalTime.class, Event::getTime, Event::setTime),
                Column.editable("Duration", Duration.class, Event::getDuration, Event::setDuration)

        );
        // Local[Date|Time] isn't really editable
        LocalDate date = LocalDate.of(2023, 10, 10);
        LocalTime time = LocalTime.of(10, 0);
        LocalTime duration = LocalTime.of(4, 0);
        rows.add(
                new Event(
                        false,
                        "Tennis",
                        List.of(new Category("Sport", CategoryColor.BLUE)),
                        "Tennis Hala Lužánky",
                        date, time, Duration.ofMinutes(45)
                )
        );
    }
}
