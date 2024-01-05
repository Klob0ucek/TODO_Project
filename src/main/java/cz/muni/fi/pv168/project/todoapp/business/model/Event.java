package cz.muni.fi.pv168.project.todoapp.business.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    public Event(
            String guid,
            boolean isDone,
            String name,
            List<Category> categories,
            String location,
            LocalDate date,
            LocalTime time,
            Duration duration
    ) {
        super(guid, isDone, name, categories, location, time, duration);
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

}
