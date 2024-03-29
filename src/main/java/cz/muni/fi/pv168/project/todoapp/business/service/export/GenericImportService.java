package cz.muni.fi.pv168.project.todoapp.business.service.export;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.export.batch.Batch;
import cz.muni.fi.pv168.project.todoapp.business.service.export.batch.BatchImporter;
import cz.muni.fi.pv168.project.todoapp.business.service.export.batch.BatchOperationException;
import cz.muni.fi.pv168.project.todoapp.business.service.export.format.Format;
import cz.muni.fi.pv168.project.todoapp.business.service.export.format.FormatMapping;
import cz.muni.fi.pv168.project.todoapp.ui.util.ImportOption;

import java.util.Collection;

/**
 * Generic synchronous implementation of the {@link ImportService}.
 */
public class GenericImportService implements ImportService {
    private final CrudService<Event> eventCrudService;
    private final CrudService<Category> categoryCrudService;
    private final CrudService<Template> templateCrudService;
    private final CrudService<Interval> intervalCrudService;
    private final FormatMapping<BatchImporter> importers;

    public GenericImportService(CrudService<Event> eventCrudService,
                                CrudService<Category> categoryCrudService,
                                CrudService<Template> templateCrudService,
                                CrudService<Interval> intervalCrudService,
                                Collection<BatchImporter> importers) {
        this.eventCrudService = eventCrudService;
        this.categoryCrudService = categoryCrudService;
        this.templateCrudService = templateCrudService;
        this.intervalCrudService = intervalCrudService;
        this.importers = new FormatMapping<>(importers);
    }

    @Override
    public void importData(String filePath, ImportOption option) {
        Batch batch = getJsonImporter().importBatch(filePath);
        switch (option) {
            case REWRITE -> {
                eventCrudService.deleteAll();
                templateCrudService.deleteAll();
                categoryCrudService.deleteAll();
                intervalCrudService.deleteAll();

                // Categories have to be stored first - events and templates depends on them
                batch.categories().forEach(this::createCategory);
                batch.events().forEach(this::createEvent);
                batch.templates().forEach(this::createTemplate);
                batch.intervals().forEach(this::createInterval);
            }
            case MERGE -> {
                batch.categories().forEach(this::mergeCategory);
                batch.events().forEach(this::mergeEvent);
                batch.templates().forEach(this::mergeTemplate);
                batch.intervals().forEach(this::mergeInterval);
            }
        }
    }

    private void createEvent(Event event) {
        eventCrudService.create(event);
    }

    private void createCategory(Category category) {
        categoryCrudService.create(category);
    }

    private void createTemplate(Template template) {
        templateCrudService.create(template);
    }

    private void createInterval(Interval interval) {
        intervalCrudService.create(interval);
    }

    private void mergeEvent(Event event) {
        if (eventCrudService.existsByGuid(event.getGuid())) eventCrudService.update(event);
        else eventCrudService.create(event);
    }

    private void mergeCategory(Category category) {
        if (categoryCrudService.existsByGuid(category.getGuid())) categoryCrudService.update(category);
        else categoryCrudService.create(category);
    }

    private void mergeTemplate(Template template) {
        if (templateCrudService.existsByGuid(template.getGuid())) templateCrudService.update(template);
        else templateCrudService.create(template);
    }

    private void mergeInterval(Interval interval) {
        if (intervalCrudService.existsByGuid(interval.getGuid())) intervalCrudService.update(interval);
        else intervalCrudService.create(interval);
    }

    @Override
    public Collection<Format> getFormats() {
        return importers.getFormats();
    }

    private BatchImporter getJsonImporter() {
        return importers.findByExtension("json");
    }

    private BatchImporter getImporter(String filePath) {
        var extension = filePath.substring(filePath.lastIndexOf('.') + 1);
        var importer = importers.findByExtension(extension);
        if (importer == null) {
            throw new BatchOperationException("Extension %s has no registered formatter".formatted(extension));
        }

        return importer;
    }
}
