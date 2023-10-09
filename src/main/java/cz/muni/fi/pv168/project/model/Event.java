package cz.muni.fi.pv168.project.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Event {
    private boolean done;
    private String name;
    private String category;
    private String location;
    private LocalDate date;
    private LocalTime time;

    public Event(boolean done, String name, String category, String location, LocalDate date, LocalTime time) {
        this.done = done;
        this.name = name;
        this.category = category;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return done == event.done && Objects.equals(name, event.name) && Objects.equals(category, event.category) && Objects.equals(location, event.location) && Objects.equals(date, event.date) && Objects.equals(time, event.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(done, name, category, location, date, time);
    }

    @Override
    public String toString() {
        return "event{" +
                "done=" + done +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
