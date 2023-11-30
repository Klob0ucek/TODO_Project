package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.UniqueNameProvider;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.EntityAlreadyExistsException;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ExistingNameException;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ValidationException;

import cz.muni.fi.pv168.project.todoapp.business.model.UniqueIdProvider;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.TemplateValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator;
import java.util.List;

/**
 * Crud operations for the {@link Template} entity.
 */
public class TemplateCrudService implements CrudService<Template> {

    private final Repository<Template> templateRepository;

    private final Validator<Template> templateValidator = new TemplateValidator();

    public TemplateCrudService(Repository<Template> templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public List<Template> findAll() {
        return templateRepository.findAll();
    }

    @Override
    public boolean create(Template newEntity) {
        var validationResult = templateValidator.validate(newEntity);
        if (newEntity.getGuid() == null || newEntity.getGuid().isBlank()) {
            newEntity.setGuid(UniqueIdProvider.newId());
        } else if (templateRepository.existByGuid(newEntity.getGuid())) {
            throw new EntityAlreadyExistsException("Category with given guid already exists: " + newEntity.getGuid());
        }
        if (validationResult.isValid()) {
            if (nameNotUnique(newEntity)) {
                throw new ExistingNameException("\"" + newEntity.getName() + "\" already exists - please use unique name!",
                        "Entity name not Unique");
            }
            templateRepository.create(newEntity);
        } else {
            throw new ValidationException("Added template not valid", validationResult.getValidationErrors());
        }
        return validationResult.isValid();
    }

    @Override
    public boolean update(Template entity) {
        var validationResult = templateValidator.validate(entity);
        if (validationResult.isValid()) {
            if (nameNotUnique(entity)) {
                throw new ExistingNameException("\"" + entity.getName() + "\" already exists - please use unique name!",
                        "Entity name not Unique");
            }
            templateRepository.update(entity);
        } else {
            throw new ValidationException("Added template not valid", validationResult.getValidationErrors());
        }
        return validationResult.isValid();
    }

    private boolean nameNotUnique(Template newTemplate) {
        for (Template old : templateRepository.findAll()) {
            if (!newTemplate.getGuid().equals(old.getGuid()) && newTemplate.getTemplateName().equals(old.getTemplateName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteByGuid(String guid) {
        templateRepository.deleteByGuid(guid);
    }

    @Override
    public void deleteAll() {
        templateRepository.deleteAll();
    }
}
