package cz.muni.fi.pv168.project.todoapp.business.service.crud;


import cz.muni.fi.pv168.project.todoapp.business.model.AbstractCoreEvent;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.model.Entity;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;

import java.util.Arrays;
import java.util.Collection;
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

    public long getDoneEventsCount() {
        return getEvents().stream().filter((AbstractCoreEvent::isDone)).count();
    }

    public long getPlannedEventsCount() {
        return getEvents().size() - getDoneEventsCount();
    }

    public int getLowestDuration() {
        var min = getEvents().stream().mapToLong(e -> e.getDuration().toMinutes()).min();
        return min.isEmpty() ? 0 : (int) min.getAsLong();
    }

    public int getHighestDuration() {
        var max = getEvents().stream().mapToLong(e -> e.getDuration().toMinutes()).max();
        return max.isEmpty() ? 0 : (int) max.getAsLong();
    }

    public Collection<CategoryColor> getUsedColors() {
        return getCategories().stream().map(Category::getColor).toList();
    }

    public CategoryColor[] getAvailableColors() {
        return Arrays.stream(CategoryColor.values()).filter(categoryColor -> !getUsedColors().contains(categoryColor)).toArray(CategoryColor[]::new);
    }
}
