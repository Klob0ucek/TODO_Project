package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;

import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator;
import java.util.List;

/**
 * Crud operations for the {@link Category} entity.
 */
public class CategoryCrudService implements CrudService<Category> {

    private final Repository<Category> categoryRepository;

    private final Validator<Category> categoryValidator;

    public CategoryCrudService(Repository<Category> categoryRepository, Validator<Category> categoryValidator) {
        this.categoryRepository = categoryRepository;
        this.categoryValidator = categoryValidator;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean create(Category newEntity) {
        categoryRepository.create(newEntity);

        return true;
    }

    @Override
    public boolean update(Category entity) {
        categoryRepository.update(entity);

        return true;
    }

    @Override
    public void deleteByGuid(String guid) {
        categoryRepository.deleteByGuid(guid);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
