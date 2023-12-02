package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.error.EntityAlreadyExistsException;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.error.ExistingNameException;
import cz.muni.fi.pv168.project.todoapp.business.error.ValidationException;

import cz.muni.fi.pv168.project.todoapp.business.model.UniqueIdProvider;
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
        var validationResult = categoryValidator.validate(newEntity);
        if (newEntity.getGuid() == null || newEntity.getGuid().isBlank()) {
            newEntity.setGuid(UniqueIdProvider.newId());
        } else if (categoryRepository.existsByGuid(newEntity.getGuid())) {
            throw new EntityAlreadyExistsException("Category with given guid already exists: " + newEntity.getGuid());
        }
        if (validationResult.isValid()) {
            if (nameNotUnique(newEntity)) {
                throw new ExistingNameException("\"" + newEntity.getName() + "\" already exists - please use unique name!",
                        "Entity name not Unique");
            }
            categoryRepository.create(newEntity);
        } else {
            throw new ValidationException("Added category not valid", validationResult.getValidationErrors());
        }
        return validationResult.isValid();
    }

    @Override
    public boolean update(Category entity) {
        var validationResult = categoryValidator.validate(entity);
        if (validationResult.isValid()) {
            if (nameNotUnique(entity)) {
                throw new ExistingNameException("\"" + entity.getName() + "\" already exists - please use unique name!",
                        "Entity name not Unique");
            }
            categoryRepository.update(entity);
        } else {
            throw new ValidationException("Edited category not valid", validationResult.getValidationErrors());
        }

        return validationResult.isValid();
    }

    private boolean nameNotUnique(Category newCategory) {
        for (Category old : categoryRepository.findAll()) {
            if (!newCategory.getGuid().equals(old.getGuid()) && newCategory.getName().equals(old.getName())) {
                return true;
            }
        }
        return false;
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
