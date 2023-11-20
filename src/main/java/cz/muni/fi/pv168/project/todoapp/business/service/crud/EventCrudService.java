package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;

import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator;
import java.util.List;

/**
 * Crud operations for the {@link Event} entity.
 */
public class EventCrudService implements CrudService<Event> {

    private final Repository<Event> eventRepository;
    private final Validator<Event> eventValidator;

    public EventCrudService(Repository<Event> EventRepository, Validator<Event> validator) {
        this.eventRepository = EventRepository;
        this.eventValidator = validator;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public boolean create(Event newEntity) {
        eventRepository.create(newEntity);

        return true;
    }

    @Override
    public boolean update(Event entity) {
        eventRepository.update(entity);

        return true;
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
