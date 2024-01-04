package cz.muni.fi.pv168.project.todoapp.business.service.export;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CategoryCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.EventCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.IntervalCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.TemplateCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.CategoryValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.IntervalValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.TemplateValidator;
import cz.muni.fi.pv168.project.todoapp.storage.memory.InMemoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GenericExportServiceTest {
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("project.basedir", "")).toAbsolutePath();
    private static final Path TEST_RESOURCES = PROJECT_ROOT.resolve(Path.of("src", "test", "resources"));

    private final Path exportFilePath = TEST_RESOURCES
            .resolve("output")
            .resolve(Instant.now().toString().replace(':', '_') + ".json");

    private GenericExportService genericExportService;
    private EventCrudService eventCrudService;

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

        genericExportService = new GenericExportService(eventCrudService, categoryCrudService,
                templateCrudService, intervalCrudService, List.of(new JsonExporter()));
    }

    @AfterEach
    void tearDown() throws IOException {
        if (Files.exists(exportFilePath)) {
            Files.delete(exportFilePath);
        }
    }

    @Test
    void exportEmpty() throws IOException {
        genericExportService.exportData(exportFilePath.toString());

        assertExportedContent("""
                {
                  "events": [],
                  "categories": [],
                  "templates": [],
                  "intervals": []
                }
                """);
    }

    @Test
    void exportSingleEvent() throws IOException {
        createEvents(
                setUpTennisEvent()
        );
        genericExportService.exportData(exportFilePath.toString());

        assertExportedContent(
                """
                        {
                          "events": [
                            {
                              "date": "2020-11-11",
                              "isDone": false,
                              "name": "Tennis",
                              "categories": [
                                {
                                  "name": "Sport",
                                  "color": "BLUE",
                                  "guid": "99605972-dc16-4ca1-9fa3-987b7da996f4"
                                }
                              ],
                              "location": "Tennis Hall",
                              "time": "12:45",
                              "duration": 45,
                              "guid": "76c59af3-6e9a-4fcb-bd7e-d0a163ed8b45"
                            }
                          ],
                          "categories": [],
                          "templates": [],
                          "intervals": []
                        }
                        """
        );
    }

    @Test
    void exportMultipleEvents() throws IOException {
        createEvents(
                setUpTennisEvent(),
                setUpEatingEvent()
        );
        genericExportService.exportData(exportFilePath.toString());

        assertExportedContent(
                """
                        {
                          "events": [
                            {
                              "date": "2020-11-11",
                              "isDone": false,
                              "name": "Tennis",
                              "categories": [
                                {
                                  "name": "Sport",
                                  "color": "BLUE",
                                  "guid": "99605972-dc16-4ca1-9fa3-987b7da996f4"
                                }
                              ],
                              "location": "Tennis Hall",
                              "time": "12:45",
                              "duration": 45,
                              "guid": "76c59af3-6e9a-4fcb-bd7e-d0a163ed8b45"
                            },
                            {
                              "date": "2020-11-11",
                              "isDone": false,
                              "name": "Eating",
                              "categories": [
                                {
                                  "name": "Sport",
                                  "color": "BLUE",
                                  "guid": "99605972-dc16-4ca1-9fa3-987b7da996f4"
                                }
                              ],
                              "location": "Burger King",
                              "time": "13:45",
                              "duration": 30,
                              "guid": "dc96a827-b56b-4252-bf24-bb8f25209f3e"
                            }
                          ],
                          "categories": [],
                          "templates": [],
                          "intervals": []
                        }
                        """
        );
    }

    private void assertExportedContent(String expectedContent) throws IOException {
        assertThat(Files.readString(exportFilePath))
                .isEqualToIgnoringNewLines(expectedContent);
    }

    private Category setUpCategory() {
        var category = new Category(
                "Sport",
                CategoryColor.BLUE
        );
        category.setGuid("99605972-dc16-4ca1-9fa3-987b7da996f4");
        return category;
    }

    private List<Category> setUpCategories() {
        return List.of(setUpCategory());
    }

    private void createEvents(Event... events) {
        for (Event event : events) {
            eventCrudService.create(event);
        }
    }

    private Event setUpTennisEvent() {
        var event = new Event(
                false,
                "Tennis",
                setUpCategories(),
                "Tennis Hall",
                LocalDate.of(2020, 11, 11),
                LocalTime.of(12, 45),
                Duration.ofMinutes(45)
        );
        event.setGuid("76c59af3-6e9a-4fcb-bd7e-d0a163ed8b45");
        return event;
    }

    private Event setUpEatingEvent() {
        var event = new Event(
                false,
                "Eating",
                setUpCategories(),
                "Burger King",
                LocalDate.of(2020, 11, 11),
                LocalTime.of(13, 45),
                Duration.ofMinutes(30)
        );
        event.setGuid("dc96a827-b56b-4252-bf24-bb8f25209f3e");
        return event;
    }
}
