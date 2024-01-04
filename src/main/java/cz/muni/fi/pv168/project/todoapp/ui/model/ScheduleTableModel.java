package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudService;

import cz.muni.fi.pv168.project.todoapp.ui.renderer.ColorRowRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.EventColorRenderer;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JTable;

public class ScheduleTableModel extends BasicTableModel<Event> {
    public ScheduleTableModel(CrudService<Event> crudService) {
        super(crudService);
        columns = List.of(
                Column.editable("Done?", Boolean.class, Event::isDone, Event::setDone),
                Column.readonly("Name", String.class, Event::getName),
                Column.readonly("Category", List.class, Event::getCategories),
                Column.readonly("Location", String.class, Event::getLocation),
                Column.readonly("Date", LocalDate.class, Event::getDate),
                Column.readonly("Time", LocalTime.class, Event::getTime),
                Column.readonly("Duration", Duration.class, Event::getDuration)

        );
    }

    public void setRowBackgroundColors(JTable table, CrudHolder crudHolder) {
        table.setDefaultRenderer(Object.class, new EventColorRenderer(crudHolder));
    }
}
