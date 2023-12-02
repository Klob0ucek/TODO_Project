package cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.CategoryEntity;


/**
 * Mapper from the {@link CategoryEntity} to {@link Category}.
 */
public class CategoryMapper implements EntityMapper<CategoryEntity, Category> {

    @Override
    public Category mapToBusiness(CategoryEntity entity) {
        return new Category(
                entity.guid(),
                entity.name(),
                entity.color()
        );
    }

    @Override
    public CategoryEntity mapNewEntityToDatabase(Category entity) {
        return new CategoryEntity(
                entity.getGuid(),
                null,
                entity.getName(),
                entity.getColor()
        );
    }

    @Override
    public CategoryEntity mapExistingEntityToDatabase(Category entity, Long dbId) {
        return new CategoryEntity(
                entity.getGuid(),
                dbId,
                entity.getName(),
                entity.getColor()
        );
    }
}
