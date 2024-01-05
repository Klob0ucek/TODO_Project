package cz.muni.fi.pv168.project.todoapp.business.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Entity;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;

import java.util.List;

/**
 * Class for holding all crud services
 * From here you can update or get all data from storage
 */
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

    public boolean create(Entity entity) {
        if (entity instanceof Event) {
            return eventCrudService.create((Event) entity);
        }
        if (entity instanceof Category) {
            return categoryCrudService.create((Category) entity);
        }
        if (entity instanceof Template) {
            return templateCrudService.create((Template) entity);
        }
        if (entity instanceof Interval) {
            return intervalCrudService.create((Interval) entity);
        }
        return false;
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

    public CrudService<Event> getEventCrudService() {
        return eventCrudService;
    }

    public CrudService<Category> getCategoryCrudService() {
        return categoryCrudService;
    }

    public CrudService<Template> getTemplateCrudService() {
        return templateCrudService;
    }

    public CrudService<Interval> getIntervalCrudService() {
        return intervalCrudService;
    }
}
