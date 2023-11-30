package cz.muni.fi.pv168.project.todoapp.service.export;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CategoryCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.EventCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.IntervalCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.TemplateCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.EntityAlreadyExistsException;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.service.export.GenericImportService;
import cz.muni.fi.pv168.project.todoapp.business.service.export.JsonImporter;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.CategoryValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.IntervalValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.TemplateValidator;
import cz.muni.fi.pv168.project.todoapp.storage.memory.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Integration tests for the {@link GenericImportService}
 */
class GenericImportServiceIntegrationTest {
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("project.basedir", "")).toAbsolutePath();
    private static final Path TEST_RESOURCES = PROJECT_ROOT.resolve(Path.of("src", "test", "resources"));

    private EventCrudService eventCrudService;
    private GenericImportService genericImportService;

    @BeforeEach
    void setUp() {
        InMemoryRepository<Event> eventRepository = new InMemoryRepository<>(List.of());
        InMemoryRepository<Category> categoryRepository = new InMemoryRepository<>(List.of());
        InMemoryRepository<Template> templateRepository = new InMemoryRepository<>(List.of());
        InMemoryRepository<Interval> intervalRepository = new InMemoryRepository<>(List.of());

        eventCrudService = new EventCrudService(eventRepository, new EventValidator());
        var categoryCrudService = new CategoryCrudService(categoryRepository, new CategoryValidator());
        var templateCrudService = new TemplateCrudService(templateRepository, new TemplateValidator());
        var intervalCrudService = new IntervalCrudService(intervalRepository, new IntervalValidator());

        genericImportService = new GenericImportService(eventCrudService, categoryCrudService,
                templateCrudService, intervalCrudService, List.of(new JsonImporter()));
    }

    @Test
    void importNoEvents() {
        Path importFilePath = TEST_RESOURCES.resolve("empty.json");
        genericImportService.importData(importFilePath.toString());

        assertThat(eventCrudService.findAll()).isEmpty();
    }

    @Test
    void singleEvent() {
        Path importFilePath = TEST_RESOURCES.resolve("single-event.json");
        genericImportService.importData(importFilePath.toString());

        var category = new Category(
                "Sport",
                CategoryColor.BLUE
        );
        category.setGuid("0c5a64aa-6003-4817-8948-7ab3fde76e28");

        var event = new Event(
                false,
                "Tennis",
                List.of(category),
                "Tennis Hall Lužánky",
                LocalDate.of(2020, 11, 11),
                LocalTime.of(12, 45),
                Duration.ofMinutes(45)
        );
        event.setGuid("ecde3bf8-464b-4103-a42d-460fc0372330");

        assertThat(eventCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(event);
    }

    @Test
    void multipleEvents() {
        Path importFilePath = TEST_RESOURCES.resolve("multiple-events.json");
        genericImportService.importData(importFilePath.toString());

        var category = new Category(
                "Sport",
                CategoryColor.BLUE
        );
        category.setGuid("0c5a64aa-6003-4817-8948-7ab3fde76e28");

        var tennis = new Event(
                false,
                "Tennis",
                List.of(category),
                "Tennis Hall Lužánky",
                LocalDate.of(2020, 11, 11),
                LocalTime.of(12, 45),
                Duration.ofMinutes(45)
        );
        tennis.setGuid("ecde3bf8-464b-4103-a42d-460fc0372330");

        var eating = new Event(
                false,
                "Eating",
                List.of(category),
                "Burger King",
                LocalDate.of(2020, 11, 11),
                LocalTime.of(13, 45),
                Duration.ofMinutes(30)
        );
        eating.setGuid("fa1d7c6e-3bfd-4d59-8519-36716fd5a006");

        assertThat(eventCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(tennis, eating);
    }

    @Test
    void invalidNameFails() {
        Path importFilePath = TEST_RESOURCES.resolve("invalid-name-event.json");

        var stringPath = importFilePath.toString();

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> genericImportService.importData(stringPath))
                .withMessageContaining("Validation failed: Added event not valid");
    }

    @Test
    void duplicateGuidFails() {
        Path importFilePath = TEST_RESOURCES.resolve("duplicate-guid-events.json");

        var stringPath = importFilePath.toString();

        assertThatExceptionOfType(EntityAlreadyExistsException.class)
                .isThrownBy(() -> genericImportService.importData(stringPath))
                .withMessage("Event with given guid already exists: ecde3bf8-464b-4103-a42d-460fc0372330");
    }
}
