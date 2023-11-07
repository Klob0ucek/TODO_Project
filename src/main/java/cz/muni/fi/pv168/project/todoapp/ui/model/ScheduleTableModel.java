package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.model.Event;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ScheduleTableModel extends BasicTableModel<Event> {
    public ScheduleTableModel() {
        columns = List.of(
                Column.readonly("Done?", Boolean.class, Event::isDone),
                Column.readonly("Name", String.class, Event::getName),
                Column.readonly("Category", List.class, Event::getCategories),
                Column.readonly("Location", String.class, Event::getLocation),
                Column.readonly("Date", LocalDate.class, Event::getDate),
                Column.readonly("Time", LocalTime.class, Event::getTime),
                Column.readonly("Duration", Duration.class, Event::getDuration)

        );
        // Local[Date|Time] isn't really editable
        LocalDate date = LocalDate.of(2023, 10, 10);
        LocalTime time = LocalTime.of(10, 0);
        Duration duration = Duration.ofMinutes(145);
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
