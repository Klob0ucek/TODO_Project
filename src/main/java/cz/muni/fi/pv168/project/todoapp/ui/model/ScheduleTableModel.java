package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.model.Event;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScheduleTableModel extends BasicTableModel<Event> {
    public ScheduleTableModel() {
        columns = List.of(
                Column.editable("Done?", Boolean.class, Event::isDone, Event::setDone),
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

        rows.addAll(
                IntStream.rangeClosed(1, 20)
                        .mapToObj(
                                (number) -> new Event(
                                        false,
                                        "Coding - " + number,
                                        List.of(new Category("Nerding", CategoryColor.BLUE)),
                                        "FI",
                                        date, time, Duration.ofMinutes(45)
                                )
                        )
                        .toList()
        );
    }
}
