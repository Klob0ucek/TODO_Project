package cz.muni.fi.pv168.project.todoapp.storage.sql.entity;

import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;

import java.util.Objects;

/**
 * Representation of Category entity in a SQL database.
 */
public record CategoryEntity(
        String guid,
        Long id,
        String name,
        CategoryColor color
) {
    public CategoryEntity(
            String guid,
            Long id,
            String name,
            CategoryColor color
    ) {
        this.id = id;
        this.guid = Objects.requireNonNull(guid, "guid must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.color = Objects.requireNonNull(color, "color must not be null");
    }

    public CategoryEntity(
            String guid,
            Long id,
            String name,
            String stringColor
    ) {
        this(guid, id, name, CategoryColor.valueOf(stringColor));
    }
}
