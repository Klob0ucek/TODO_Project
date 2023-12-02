package cz.muni.fi.pv168.project.todoapp.storage.sql;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.DataStorageException;
import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.DataAccessObject;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.EventEntity;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper.EntityMapper;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link Repository} for {@link Event} entity using SQL database.
 *
 * @author Vojtech Sassmann
 */
public class EventSqlRepository implements Repository<Event> {

    private final DataAccessObject<EventEntity> employeeDao;
    private final EntityMapper<EventEntity, Event> employeeMapper;

    public EventSqlRepository(
            DataAccessObject<EventEntity> employeeDao,
            EntityMapper<EventEntity, Event> employeeMapper) {
        this.employeeDao = employeeDao;
        this.employeeMapper = employeeMapper;
    }


    @Override
    public List<Event> findAll() {
        return employeeDao
                .findAll()
                .stream()
                .map(employeeMapper::mapToBusiness)
                .toList();
    }

    @Override
    public void create(Event newEntity) {
        employeeDao.create(employeeMapper.mapNewEntityToDatabase(newEntity));
    }

    @Override
    public void update(Event entity) {
        var existingEvent = employeeDao.findByGuid(entity.getGuid())
                .orElseThrow(() -> new DataStorageException("Event not found, guid: " + entity.getGuid()));
        var updatedEventEntity = employeeMapper
                .mapExistingEntityToDatabase(entity, existingEvent.id());

        employeeDao.update(updatedEventEntity);
    }

    @Override
    public void deleteByGuid(String guid) {
        employeeDao.deleteByGuid(guid);
    }

    @Override
    public void deleteAll() {
        employeeDao.deleteAll();
    }

    @Override
    public boolean existsByGuid(String guid) {
        return employeeDao.existsByGuid(guid);
    }

    public Optional<Event> findByGuid(String guid) {
        return employeeDao
                .findByGuid(guid)
                .map(employeeMapper::mapToBusiness);
    }
}
