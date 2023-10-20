package cz.muni.fi.pv168.project.todoapp.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Event extends AbstractCoreEvent {
    private LocalDate date;

    public Event(
            boolean isDone,
            String name,
            List<Category> categories,
            String location,
            LocalDate date,
            LocalTime time,
            Duration duration
    ) {
        super(isDone, name, categories, location, time, duration);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(
            LocalDate date
    ) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "date=" + date +
                ", isDone=" + isDone +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                ", location='" + location + '\'' +
                ", time=" + time +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;

        return date.equals(event.date) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, super.hashCode());
    }
}
