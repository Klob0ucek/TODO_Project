package cz.muni.fi.pv168.project.todoapp.storage.sql.entity;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * Representation of Event entity in a SQL database.
 */
public record EventEntity(
        String guid,
        Long id,
        boolean isDone,
        String name,
        List<Category> categories,
        String location,
        LocalDate date,
        LocalTime time,
        Duration duration) {
    public EventEntity(
            String guid,
            Long id,
            boolean isDone,
            String name,
            List<Category> categories,
            String location,
            LocalDate date,
            LocalTime time,
            Duration duration) {
        this.id = id;
        this.guid = Objects.requireNonNull(guid, "guid must not be null");
        this.isDone = isDone;
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.categories = Objects.requireNonNull(categories, "categories must not be null");
        this.location = Objects.requireNonNull(location, "location must not be null");
        this.date = date;
        this.time = time;
        this.duration = Objects.requireNonNull(duration, "duration must not be null");
    }
}
