package cz.muni.fi.pv168.project.todoapp.storage.sql.entity;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * Representation of Template entity in a SQL database.
 */
public record TemplateEntity(
        String guid,
        Long id,
        boolean isDone,
        String templateName,
        String eventName,
        List<Category> categories,
        String location,
        LocalTime time,
        Duration duration) {
    public TemplateEntity(
            String guid,
            Long id,
            boolean isDone,
            String templateName,
            String eventName,
            List<Category> categories,
            String location,
            LocalTime time,
            Duration duration) {
        this.guid = Objects.requireNonNull(guid, "guid must not be null");
        this.id = id;
        this.isDone = isDone;
        this.templateName = Objects.requireNonNull(templateName, "templateName must not be null");
        this.eventName = eventName;
        this.categories = categories;
        this.location = location;
        this.time = time;
        this.duration = duration;
    }
}
