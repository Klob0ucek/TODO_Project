package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudService;


import java.time.Duration;
import java.util.List;

public class IntervalTableModel extends BasicTableModel<Interval> {
    public IntervalTableModel(CrudService<Interval> crudService) {
        super(crudService);
        columns = List.of(
                Column.readonly("Name", String.class, Interval::getName),
                Column.readonly("Abbreviation", String.class, Interval::getAbbreviation),
                Column.readonly("Duration", Duration.class, Interval::getDuration)
        );
    }
}
