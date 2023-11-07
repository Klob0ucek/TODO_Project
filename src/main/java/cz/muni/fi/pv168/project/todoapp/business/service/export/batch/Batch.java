package cz.muni.fi.pv168.project.todoapp.business.service.export.batch;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;

import java.util.Collection;

public record Batch(Collection<Event> events,
                    Collection<Category> categories,
                    Collection<Template> templates,
                    Collection<Interval> intervals) {
}
