package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;

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
        intervalRepository.create(newEntity);

        return true;
    }

    @Override
    public boolean update(Interval entity) {
        intervalRepository.update(entity);

        return true;
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
