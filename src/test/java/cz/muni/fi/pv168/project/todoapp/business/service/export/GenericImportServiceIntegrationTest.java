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
import cz.muni.fi.pv168.project.todoapp.business.error.EntityAlreadyExistsException;
import cz.muni.fi.pv168.project.todoapp.business.error.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.CategoryValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.IntervalValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.TemplateValidator;
import cz.muni.fi.pv168.project.todoapp.storage.memory.InMemoryRepository;
import cz.muni.fi.pv168.project.todoapp.ui.util.ImportOption;
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

class GenericImportServiceTest {
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("project.basedir", "")).toAbsolutePath();
    private static final Path TEST_RESOURCES = PROJECT_ROOT.resolve(Path.of("src", "test", "resources"));

    private static Category food;
    private static Category sport;
    private static Event eating_kfc;
    private static Event tennis;
    private static Event eating_burger_king;
    private static Template my_sleep;
    private static Template golf;
    private static Interval short_break;
    private static Interval long_break;

    private EventCrudService eventCrudService;
    private CategoryCrudService categoryCrudService;
    private TemplateCrudService templateCrudService;
    private IntervalCrudService intervalCrudService;
    private GenericImportService genericImportService;

    @BeforeEach
    void setUp() {
        food = new Category(
                "d3e8a63c-8fd2-49c7-bb90-af105a915a2d",
                "Food",
                CategoryColor.RED
        );

        sport = new Category(
                "0c5a64aa-6003-4817-8948-7ab3fde76e28",
                "Sport",
                CategoryColor.BLUE
        );

        eating_kfc = new Event(
                "9b5fb19e-76a1-4f0e-af43-ec7c8d88a127",
                false,
                "Eating kfc",
                List.of(food),
                "KFC",
                LocalDate.of(2020, 11, 11),
                LocalTime.of(12, 45),
                Duration.ofMinutes(30)
        );

        tennis = new Event(
                "ecde3bf8-464b-4103-a42d-460fc0372330",
                false,
                "Tennis",
                List.of(sport),
                "Tennis Hall Lužánky",
                LocalDate.of(2020, 11, 11),
                LocalTime.of(12, 45),
                Duration.ofMinutes(45)
        );

        eating_burger_king = new Event(
                "fa1d7c6e-3bfd-4d59-8519-36716fd5a006",
                false,
                "Eating",
                List.of(food),
                "Burger King",
                LocalDate.of(2020, 11, 11),
                LocalTime.of(13, 45),
                Duration.ofMinutes(30)
        );

        my_sleep = new Template(
                "a29f12c4-aecc-478d-91cf-d9b6c97a9f8f",
                "My sleep",
                false,
                "Sleeping",
                List.of(),
                "Lovely bed",
                null,
                Duration.ofMinutes(69)
        );

        golf = new Template(
                "34b7995c-7a7e-4a7c-9a1c-8ac0047943d6",
                "Golfing",
                false,
                "Golf",
                List.of(sport),
                "Golf course",
                null,
                Duration.ofMinutes(45)
        );

        short_break = new Interval(
                "7e6f5d81-02ef-4d74-83af-0d21b90c87b6",
                "short break",
                "sb",
                Duration.ofMinutes(15)
        );

        long_break = new Interval(
                "d81b9e5e-7757-4d51-8b4b-6cbcf47c1221",
                "long break",
                "lb",
                Duration.ofMinutes(30)
        );

        InMemoryRepository<Event> eventRepository = new InMemoryRepository<>(List.of(eating_kfc));
        InMemoryRepository<Category> categoryRepository = new InMemoryRepository<>(List.of(food));
        InMemoryRepository<Template> templateRepository = new InMemoryRepository<>(List.of(my_sleep));
        InMemoryRepository<Interval> intervalRepository = new InMemoryRepository<>(List.of(short_break));

        eventCrudService = new EventCrudService(eventRepository, new EventValidator());
        categoryCrudService = new CategoryCrudService(categoryRepository, new CategoryValidator());
        templateCrudService = new TemplateCrudService(templateRepository, new TemplateValidator());
        intervalCrudService = new IntervalCrudService(intervalRepository, new IntervalValidator());

        genericImportService = new GenericImportService(eventCrudService, categoryCrudService,
                templateCrudService, intervalCrudService, List.of(new JsonImporter()));
    }

    @Test
    void emptyImportRewrite() {
        Path importFilePath = TEST_RESOURCES.resolve("empty.json");
        genericImportService.importData(importFilePath.toString(), ImportOption.REWRITE);

        assertThat(eventCrudService.findAll()).isEmpty();
        assertThat(categoryCrudService.findAll()).isEmpty();
        assertThat(templateCrudService.findAll()).isEmpty();
        assertThat(intervalCrudService.findAll()).isEmpty();
    }

    @Test
    void emptyImportMerge() {
        Path importFilePath = TEST_RESOURCES.resolve("empty.json");
        genericImportService.importData(importFilePath.toString(), ImportOption.MERGE);

        assertThat(eventCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(eating_kfc);
        assertThat(categoryCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(food);
        assertThat(templateCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(my_sleep);
        assertThat(intervalCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(short_break);
    }

    @Test
    void singleEventRewrite() {
        Path importFilePath = TEST_RESOURCES.resolve("single-event.json");
        genericImportService.importData(importFilePath.toString(), ImportOption.REWRITE);

        assertThat(eventCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(tennis);
        assertThat(categoryCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(sport);
        assertThat(templateCrudService.findAll()).isEmpty();
        assertThat(intervalCrudService.findAll()).isEmpty();
    }

    @Test
    void singleEventMerge() {
        Path importFilePath = TEST_RESOURCES.resolve("single-event.json");
        genericImportService.importData(importFilePath.toString(), ImportOption.MERGE);

        assertThat(eventCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(eating_kfc, tennis);
        assertThat(categoryCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(sport, food);
        assertThat(templateCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(my_sleep);
        assertThat(intervalCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(short_break);
    }

    @Test
    void multipleEventsRewrite() {
        Path importFilePath = TEST_RESOURCES.resolve("multiple-events.json");
        genericImportService.importData(importFilePath.toString(), ImportOption.REWRITE);

        assertThat(eventCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(eating_burger_king, tennis);
        assertThat(categoryCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(sport, food);
        assertThat(templateCrudService.findAll()).isEmpty();
        assertThat(intervalCrudService.findAll()).isEmpty();
    }

    @Test
    void multipleEventsMerge() {
        Path importFilePath = TEST_RESOURCES.resolve("multiple-events.json");
        genericImportService.importData(importFilePath.toString(), ImportOption.MERGE);

        assertThat(eventCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(eating_kfc, eating_burger_king, tennis);
        assertThat(categoryCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(sport, food);
        assertThat(templateCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(my_sleep);
        assertThat(intervalCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(short_break);
    }

    @Test
    void complexityRewrite() {
        Path importFilePath = TEST_RESOURCES.resolve("complexity.json");
        genericImportService.importData(importFilePath.toString(), ImportOption.REWRITE);

        assertThat(eventCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(eating_burger_king, tennis);
        assertThat(categoryCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(sport, food);
        assertThat(templateCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(golf);
        assertThat(intervalCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(long_break);
    }

    @Test
    void complexityMerge() {
        Path importFilePath = TEST_RESOURCES.resolve("complexity.json");
        genericImportService.importData(importFilePath.toString(), ImportOption.MERGE);

        assertThat(eventCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(eating_kfc, eating_burger_king, tennis);
        assertThat(categoryCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(sport, food);
        assertThat(templateCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(golf, my_sleep);
        assertThat(intervalCrudService.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(long_break, short_break);
    }

    @Test
    void invalidNameFails() {
        Path importFilePath = TEST_RESOURCES.resolve("invalid-name-event.json");

        var stringPath = importFilePath.toString();

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> genericImportService.importData(stringPath, ImportOption.REWRITE))
                .withMessageContaining("Validation failed: Added event not valid");
    }

    @Test
    void duplicateGuidFails() {
        Path importFilePath = TEST_RESOURCES.resolve("duplicate-guid-events.json");

        var stringPath = importFilePath.toString();

        assertThatExceptionOfType(EntityAlreadyExistsException.class)
                .isThrownBy(() -> genericImportService.importData(stringPath, ImportOption.REWRITE))
                .withMessage("Event with given guid already exists: ecde3bf8-464b-4103-a42d-460fc0372330");
    }
}
