package cz.muni.fi.pv168.project.todoapp.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.UniqueIdProvider;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.EventCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.EntityAlreadyExistsException;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.ValidationResult;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;


class EventCrudServiceUnitTest {
    private static final Category FOOD_DEPARTMENT = new Category("Food", CategoryColor.YELLOW);

    private EventCrudService eventCrudService;
    private Repository<Event> eventRepository;
    private UniqueIdProvider guidProvider;

    private EventValidator eventValidator;


    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        eventRepository = Mockito.mock(Repository.class);
        eventValidator = new EventValidator();
        eventCrudService = new EventCrudService(eventRepository);

    }

    @Test
    void createWithGuidSucceeds() {
        var employee = createEvent("e-1");

        when(eventValidator.validate(employee))
                .thenReturn(ValidationResult.success());

        var result = eventCrudService.create(employee);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(eventRepository, times(1))
                .create(employee);
    }

    @Test
    void createWithoutGuidSucceeds() {
        var employee = createEvent(null);
        var newGuid = "new-guid";
        var expectedEmployee = createEvent(newGuid);

        when(guidProvider.newId())
                .thenReturn(newGuid);

        when(eventValidator.validate(employee))
                .thenReturn(ValidationResult.success());

        var result = eventCrudService.create(employee);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(eventRepository, times(1))
                .create(refEq(expectedEmployee));
    }

    @Test
    void createValidationError() {
        var employee = createEvent("e-1");

        when(eventValidator.validate(employee))
                .thenReturn(ValidationResult.failed("validation failed"));

        var result = eventCrudService.create(employee);

        assertThat(result).isEqualTo(ValidationResult.failed("validation failed"));
        verify(eventRepository, times(0))
                .create(employee);
    }

    @Test
    void createFailsForDuplicateGuid() {
        var employee = createEvent("e-1");

        when(eventValidator.validate(employee))
                .thenReturn(ValidationResult.success());
        when(eventRepository.existByGuid("e-1"))
                .thenReturn(true);

        assertThatExceptionOfType(EntityAlreadyExistsException.class)
                .isThrownBy(() -> eventCrudService.create(employee))
                .withMessage("Employee with given guid already exists: e-1");
    }

    @Test
    void updateWithGuidSucceeds() {
        var employee = createEvent("e-1");

        when(eventValidator.validate(employee))
                .thenReturn(ValidationResult.success());

        var result = eventCrudService.update(employee);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(eventRepository, times(1))
                .update(employee);
    }

    @Test
    void updateValidationError() {
        var employee = createEvent("e-1");

        when(eventValidator.validate(employee))
                .thenReturn(ValidationResult.failed("validation failed"));

        var result = eventCrudService.update(employee);

        assertThat(result).isEqualTo(ValidationResult.failed("validation failed"));
        verify(eventRepository, times(0))
                .update(employee);
    }

    @Test
    void deleteByGuid() {
        eventCrudService.deleteByGuid("guid");

        verify(eventRepository, times(1))
                .deleteByGuid("guid");
    }

    @Test
    void findAll() {
        var expectedEmployeeList = List.of(createEvent("e-1"));
        when(eventRepository.findAll())
                .thenReturn(expectedEmployeeList);

        var foundEmployees = eventCrudService.findAll();

        assertThat(foundEmployees).isEqualTo(expectedEmployeeList);
    }

    @Test
    void deleteAll() {
        eventCrudService.deleteAll();

        verify(eventRepository, times(1))
                .deleteAll();
    }

    private static Event createEvent(String guid) {
        var event = new Event(
                false,
                "Name",
                List.of(new Category("Sport", CategoryColor.ORANGE)),
                "Home",
                LocalDate.now(),
                LocalTime.now(),
                Duration.ZERO
        );
        event.setGuid(guid);
        return event;
    }
}