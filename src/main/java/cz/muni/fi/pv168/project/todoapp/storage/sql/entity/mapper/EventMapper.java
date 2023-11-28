package cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.DataAccessObject;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.CategoryEntity;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.EventEntity;

import java.util.List;


/**
 * Mapper from the {@link EventEntity} to {@link Event}.
 */
public class EventMapper implements EntityMapper<EventEntity, Event> {

    private final DataAccessObject<CategoryEntity> categoryDao;
    private final EntityMapper<CategoryEntity, Category> categoryMapper;

    public EventMapper(
            DataAccessObject<CategoryEntity> categoryDao,
            EntityMapper<CategoryEntity, Category> categoryMapper) {
        this.categoryDao = categoryDao;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Event mapToBusiness(EventEntity entity) {
//        var category = categoryDao
//                .findById(entity.categoryId())
//                .map(categoryMapper::mapToBusiness)
//                .orElseThrow(() -> new DataStorageException("Category not found, id: " +
//                        entity.categoryId()));

        return new Event(
                entity.guid(),
                entity.isDone(),
                entity.name(),
                List.of(),
                entity.location(),
                entity.date(),
                entity.time(),
                entity.duration()
        );
    }

    @Override
    public EventEntity mapNewEntityToDatabase(Event entity) {
//        var categoryEntity = categoryDao
//                .findByGuid(entity.getCategory().getGuid())
//                .orElseThrow(() -> new DataStorageException("Category not found, guid: " +
//                        entity.getCategory().getGuid()));

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
//        var categoryEntity = categoryDao
//                .findByGuid(entity.getCategory().getGuid())
//                .orElseThrow(() -> new DataStorageException("Category not found, guid: " +
//                        entity.getCategory().getGuid()));

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
