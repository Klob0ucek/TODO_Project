package cz.muni.fi.pv168.project.todoapp.storage.sql.entity;

import java.time.Duration;
import java.util.Objects;

/**
 * Representation of Interval entity in a SQL database.
 */
public record IntervalEntity(
        String guid,
        Long id,
        String name,
        String abbreviation,
        Duration duration
) {
    public IntervalEntity(
            String guid,
            Long id,
            String name,
            String abbreviation,
            Duration duration
    ) {
        this.id = id;
        this.guid = Objects.requireNonNull(guid, "guid must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.abbreviation = Objects.requireNonNull(abbreviation, "abbreviation must not be null");
        this.duration = Objects.requireNonNull(duration, "duration must not be null");
    }
}
