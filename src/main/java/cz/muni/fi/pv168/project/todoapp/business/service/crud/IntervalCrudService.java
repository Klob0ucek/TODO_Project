package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.UniqueNameProvider;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.EntityAlreadyExistsException;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ExistingNameException;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ValidationException;

import cz.muni.fi.pv168.project.todoapp.business.model.UniqueIdProvider;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.IntervalValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator;

import java.util.List;


/**
 * Crud operations for the {@link Interval} entity.
 */
public class IntervalCrudService implements CrudService<Interval> {

    private final Repository<Interval> intervalRepository;
    private final Validator<Interval> intervalValidator;

    public IntervalCrudService(Repository<Interval> intervalRepository, Validator<Interval> intervalValidator) {
        this.intervalRepository = intervalRepository;
        this.intervalValidator = intervalValidator;
    }

    @Override
    public List<Interval> findAll() {
        return intervalRepository.findAll();
    }

    @Override
    public boolean create(Interval newEntity) {
        var validationResult = intervalValidator.validate(newEntity);
        if (newEntity.getGuid() == null || newEntity.getGuid().isBlank()) {
            newEntity.setGuid(UniqueIdProvider.newId());
        } else if (intervalRepository.existByGuid(newEntity.getGuid())) {
            throw new EntityAlreadyExistsException("Category with given guid already exists: " + newEntity.getGuid());
        }
        if (validationResult.isValid()) {
            if (nameNotUnique(newEntity)) {
                throw new ExistingNameException("\"" + newEntity.getName() + "\" already exists - please use unique name!",
                        "Entity name not Unique");
            }
            intervalRepository.create(newEntity);
        } else {
            throw new ValidationException("Added interval not valid", validationResult.getValidationErrors());
        }
        return validationResult.isValid();
    }

    @Override
    public boolean update(Interval entity) {
        var validationResult = intervalValidator.validate(entity);
        if (validationResult.isValid()) {
            if (nameNotUnique(entity)) {
                throw new ExistingNameException("\"" + entity.getName() + "\" already exists - please use unique name!",
                        "Entity name not Unique");
            }
            intervalRepository.update(entity);
        } else {
            throw new ValidationException("Edited interval not valid", validationResult.getValidationErrors());
        }

        return validationResult.isValid();
    }

    private boolean nameNotUnique(Interval newInterval) {
        for (Interval old : intervalRepository.findAll()) {
            if (!newInterval.getGuid().equals(old.getGuid()) && newInterval.getName().equals(old.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteByGuid(String guid) {
        intervalRepository.deleteByGuid(guid);
    }

    @Override
    public void deleteAll() {
        intervalRepository.deleteAll();
    }
}
