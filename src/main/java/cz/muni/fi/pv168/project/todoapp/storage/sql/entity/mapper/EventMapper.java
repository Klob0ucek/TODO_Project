package cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper;

import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.EventEntity;


/**
 * Mapper from the {@link EventEntity} to {@link Event}.
 */
public class EventMapper implements EntityMapper<EventEntity, Event> {
    public EventMapper() {
    }

    @Override
    public Event mapToBusiness(EventEntity entity) {
        return new Event(
                entity.guid(),
                entity.isDone(),
                entity.name(),
                entity.categories(),
                entity.location(),
                entity.date(),
                entity.time(),
                entity.duration()
        );
    }

    @Override
    public EventEntity mapNewEntityToDatabase(Event entity) {
        return new EventEntity(
                entity.getGuid(),
                null,
                entity.isDone(),
                entity.getName(),
                entity.getCategories(),
                entity.getLocation(),
                entity.getDate(),
                entity.getTime(),
                entity.getDuration()
        );
    }

    @Override
    public EventEntity mapExistingEntityToDatabase(Event entity, Long dbId) {
        return new EventEntity(
                entity.getGuid(),
                dbId,
                entity.isDone(),
                entity.getName(),
                entity.getCategories(),
                entity.getLocation(),
                entity.getDate(),
                entity.getTime(),
                entity.getDuration()
        );
    }
}
