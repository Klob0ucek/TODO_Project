package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.EntityAlreadyExistsException;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
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
            // TODO test if name already exist
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
            // TODO test if name already exist
            templateRepository.update(entity);
        } else {
            throw new ValidationException("Added template not valid", validationResult.getValidationErrors());
        }
        return validationResult.isValid();
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
