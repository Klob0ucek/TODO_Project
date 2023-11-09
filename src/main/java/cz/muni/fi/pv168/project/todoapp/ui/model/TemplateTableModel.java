package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudService;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class TemplateTableModel extends BasicTableModel<Template> {

    public TemplateTableModel(CrudService<Template> crudService) {
        super(crudService);
        columns = List.of(
                Column.readonly("Template Name", String.class, Template::getTemplateName),
                Column.readonly("Done?", Boolean.class, Template::isDone),
                Column.readonly("Event Name", String.class, Template::getName),
                Column.readonly("Categories", List.class, Template::getCategories),
                Column.readonly("Location", String.class, Template::getLocation),
                Column.readonly("Time", LocalTime.class, Template::getTime),
                Column.readonly("Duration", Duration.class, Template::getDuration)
        );
    }
}
