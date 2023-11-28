package cz.muni.fi.pv168.project.todoapp.storage.sql;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.DataStorageException;
import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.DataAccessObject;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.IntervalEntity;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper.EntityMapper;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link Repository} for {@link Interval} entity using SQL database.
 */
public class IntervalSqlRepository implements Repository<Interval> {

    private final DataAccessObject<IntervalEntity> intervalDao;
    private final EntityMapper<IntervalEntity, Interval> intervalMapper;

    public IntervalSqlRepository(
            DataAccessObject<IntervalEntity> intervalDao,
            EntityMapper<IntervalEntity, Interval> intervalMapper) {
        this.intervalDao = intervalDao;
        this.intervalMapper = intervalMapper;
    }


    @Override
    public List<Interval> findAll() {
        return intervalDao
                .findAll()
                .stream()
                .map(intervalMapper::mapToBusiness)
                .toList();
    }

    @Override
    public void create(Interval newEntity) {
        intervalDao.create(intervalMapper.mapNewEntityToDatabase(newEntity));
    }

    @Override
    public void update(Interval entity) {
        var existingInterval = intervalDao.findByGuid(entity.getGuid())
                .orElseThrow(() -> new DataStorageException("Interval not found, guid: " + entity.getGuid()));
        var updatedIntervalEntity = intervalMapper
                .mapExistingEntityToDatabase(entity, existingInterval.id());

        intervalDao.update(updatedIntervalEntity);
    }

    @Override
    public void deleteByGuid(String guid) {
        intervalDao.deleteByGuid(guid);
    }

    @Override
    public void deleteAll() {
        intervalDao.deleteAll();
    }

    public boolean existsByGuid(String guid) {
        return intervalDao.existsByGuid(guid);
    }

    public Optional<Interval> findByGuid(String guid) {
        return intervalDao
                .findByGuid(guid)
                .map(intervalMapper::mapToBusiness);
    }
}
