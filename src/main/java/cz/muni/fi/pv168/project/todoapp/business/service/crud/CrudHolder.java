package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;

import java.util.List;

public class CrudHolder {
    private final CrudService<Event> eventCrudService;
    private final CrudService<Category> categoryCrudService;
    private final CrudService<Template> templateCrudService;
    private final CrudService<Interval> intervalCrudService;

    public CrudHolder(CrudService<Event> eventCrudService,
                      CrudService<Category> categoryCrudService,
                      CrudService<Template> templateCrudService,
                      CrudService<Interval> intervalCrudService) {
        this.eventCrudService = eventCrudService;
        this.categoryCrudService = categoryCrudService;
        this.templateCrudService = templateCrudService;
        this.intervalCrudService = intervalCrudService;
    }
    public List<Event> getEvents() {
        return eventCrudService.findAll();
    }
    public List<Category> getCategories() {
        return categoryCrudService.findAll();
    }
    public List<Template> getTemplates() {
        return templateCrudService.findAll();
    }
    public List<Interval> getIntervals() {
        return intervalCrudService.findAll();
    }
}
