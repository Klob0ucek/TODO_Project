package cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper;

import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.TemplateEntity;

import java.util.List;


/**
 * Mapper from the {@link TemplateEntity} to {@link Template}.
 */
public class TemplateMapper implements EntityMapper<TemplateEntity, Template> {


    public TemplateMapper() {

    }

    @Override
    public Template mapToBusiness(TemplateEntity entity) {
//        var category = categoryDao
//                .findById(entity.categoryId())
//                .map(categoryMapper::mapToBusiness)
//                .orElseThrow(() -> new DataStorageException("Category not found, id: " +
//                        entity.categoryId()));

        return new Template(
                entity.guid(),
                entity.templateName(),
                entity.isDone(),
                entity.eventName(),
                List.of(),
                entity.location(),
                entity.time(),
                entity.duration()
        );
    }

    @Override
    public TemplateEntity mapNewEntityToDatabase(Template entity) {
//        var categoryEntity = categoryDao
//                .findByGuid(entity.getCategory().getGuid())
//                .orElseThrow(() -> new DataStorageException("Category not found, guid: " +
//                        entity.getCategory().getGuid()));

        return new TemplateEntity(
                entity.getGuid(),
                null,
                entity.isDone(),
                entity.getTemplateName(),
                entity.getName(),
                entity.getCategories(),
                entity.getLocation(),
                entity.getTime(),
                entity.getDuration()
        );
    }

    @Override
    public TemplateEntity mapExistingEntityToDatabase(Template entity, Long dbId) {
//        var categoryEntity = categoryDao
//                .findByGuid(entity.getCategory().getGuid())
//                .orElseThrow(() -> new DataStorageException("Category not found, guid: " +
//                        entity.getCategory().getGuid()));

        return new TemplateEntity(
                entity.getGuid(),
                dbId,
                entity.isDone(),
                entity.getTemplateName(),
                entity.getName(),
                entity.getCategories(),
                entity.getLocation(),
                entity.getTime(),
                entity.getDuration()
        );
    }
}
