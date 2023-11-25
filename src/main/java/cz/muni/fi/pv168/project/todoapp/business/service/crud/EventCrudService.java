package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.EntityAlreadyExistsException;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
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
    private final Validator<Event> eventValidator = new EventValidator();

    public EventCrudService(Repository<Event> EventRepository) {
        this.eventRepository = EventRepository;
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
            // todo add renaming
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
            eventRepository.update(entity);
        } else {
            throw new ValidationException("Edited event not valid", validationResult.getValidationErrors());
        }
        return validationResult.isValid();
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
