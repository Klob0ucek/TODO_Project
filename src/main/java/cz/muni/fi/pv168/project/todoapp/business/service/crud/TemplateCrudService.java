package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;

import cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator;
import java.util.List;

/**
 * Crud operations for the {@link Template} entity.
 */
public class TemplateCrudService implements CrudService<Template> {

    private final Repository<Template> templateRepository;

    private final Validator<Template> templateValidator;

    public TemplateCrudService(Repository<Template> templateRepository, Validator<Template> templateValidator) {
        this.templateRepository = templateRepository;
        this.templateValidator = templateValidator;
    }

    @Override
    public List<Template> findAll() {
        return templateRepository.findAll();
    }

    @Override
    public boolean create(Template newEntity) {
        templateRepository.create(newEntity);

        return true;
    }

    @Override
    public boolean update(Template entity) {
        templateRepository.update(entity);

        return true;
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
