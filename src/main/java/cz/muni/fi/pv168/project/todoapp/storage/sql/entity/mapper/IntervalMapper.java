package cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper;

import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.IntervalEntity;


/**
 * Mapper from the {@link IntervalEntity} to {@link Interval}.
 */
public class IntervalMapper implements EntityMapper<IntervalEntity, Interval> {

    @Override
    public Interval mapToBusiness(IntervalEntity entity) {
        return new Interval(
                entity.guid(),
                entity.name(),
                entity.abbreviation(),
                entity.duration()
        );
    }

    @Override
    public IntervalEntity mapNewEntityToDatabase(Interval entity) {
        return new IntervalEntity(
                entity.getGuid(),
                null,
                entity.getName(),
                entity.getAbbreviation(),
                entity.getDuration()
        );
    }

    @Override
    public IntervalEntity mapExistingEntityToDatabase(Interval entity, Long dbId) {
        return new IntervalEntity(
                entity.getGuid(),
                dbId,
                entity.getName(),
                entity.getAbbreviation(),
                entity.getDuration()
        );
    }
}
