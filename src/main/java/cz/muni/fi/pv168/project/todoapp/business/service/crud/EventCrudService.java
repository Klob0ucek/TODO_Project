package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.UniqueNameProvider;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.EntityAlreadyExistsException;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.EventRenameException;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ValidationException;

import cz.muni.fi.pv168.project.todoapp.business.model.UniqueIdProvider;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator;

import java.util.List;
import java.util.Optional;

/**
 * Crud operations for the {@link Event} entity.
 */
public class EventCrudService implements CrudService<Event> {

    private final Repository<Event> eventRepository;
    private final Validator<Event> eventValidator;

    public EventCrudService(Repository<Event> EventRepository, Validator<Event> eventValidator) {
        this.eventRepository = EventRepository;
        this.eventValidator = eventValidator;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public boolean create(Event newEntity) {
        var validationResult = eventValidator.validate(newEntity);

        if (newEntity.getGuid() == null || newEntity.getGuid().isBlank()) {
            newEntity.setGuid(UniqueIdProvider.newId());
        } else if (eventRepository.existByGuid(newEntity.getGuid())) {
            throw new EntityAlreadyExistsException("Event with given guid already exists: " + newEntity.getGuid());
        }
        if (validationResult.isValid()) {
            if (nameNotUnique(newEntity)) {
                String newName = UniqueNameProvider.getUniqueName(newEntity.getName(),
                        eventRepository.findAll().stream().map(Event::getName).toList());
                String oldName = newEntity.getName();
                newEntity.setName(newName);
                eventRepository.create(newEntity);
                throw new EventRenameException(oldName, newName, "New Entity renamed");
            }
            eventRepository.create(newEntity);
        } else {
            throw new ValidationException("Added event not valid", validationResult.getValidationErrors());
        }
        return validationResult.isValid();
    }

    @Override
    public boolean update(Event entity) {
        var validationResult = eventValidator.validate(entity);
        if (validationResult.isValid()) {
            if (nameNotUnique(entity)) {
                // TODO Newly generated name does not appear immediately
                // -> table updates only after edit with unique name
                String newName = UniqueNameProvider.getUniqueName(entity.getName(),
                        eventRepository.findAll().stream().map(Event::getName).toList());
                String oldName = entity.getName();
                entity.setName(newName);
                eventRepository.update(entity);
                throw new EventRenameException(oldName, newName, "New Entity renamed");
            }
            eventRepository.update(entity);
        } else {
            throw new ValidationException("Edited event not valid", validationResult.getValidationErrors());
        }
        return validationResult.isValid();
    }

    private boolean nameNotUnique(Event newEvent) {
        for (Event old : eventRepository.findAll()) {
            if (!newEvent.getGuid().equals(old.getGuid()) && newEvent.getName().equals(old.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteByGuid(String guid) {
        eventRepository.deleteByGuid(guid);
    }

    @Override
    public void deleteAll() {
        eventRepository.deleteAll();
    }
}
