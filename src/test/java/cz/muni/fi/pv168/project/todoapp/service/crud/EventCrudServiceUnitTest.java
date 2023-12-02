package cz.muni.fi.pv168.project.todoapp.service.crud;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.UniqueIdProvider;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.EventCrudService;
import cz.muni.fi.pv168.project.todoapp.business.error.EntityAlreadyExistsException;
import cz.muni.fi.pv168.project.todoapp.business.error.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link EventCrudService}
 */
class EventCrudServiceUnitTest {
    private static final Category FOOD_CATEGORY = new Category("Food", CategoryColor.YELLOW);
    private static final Category SPORT_CATEGORY = new Category("Sport", CategoryColor.BLUE);

    private EventCrudService eventCrudService;
    private Repository<Event> eventRepository;
    private EventValidator eventValidator;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        eventRepository = Mockito.mock(Repository.class);
        eventValidator = Mockito.mock(EventValidator.class);
        eventCrudService = new EventCrudService(eventRepository, eventValidator);
    }

    @Test
    void createWithGuidSucceeds() {
        var event = createEvent(UniqueIdProvider.newId());

        when(eventValidator.validate(event))
                .thenReturn(ValidationResult.success());

        var result = eventCrudService.create(event);

        assertThat(result).isEqualTo(true);
        verify(eventRepository, times(1))
                .create(event);
    }

    @Test
    void createWithoutGuidSucceeds() {
        var event = createEvent(null);

        when(eventValidator.validate(event))
                .thenReturn(ValidationResult.success());

        var result = eventCrudService.create(event);

        assertThat(event.getGuid()).isNotEmpty();
        assertThat(result).isEqualTo(true);
        verify(eventRepository, times(1))
                .create(event);
    }

    @Test
    void createValidationError() {
        var event = createEvent(UniqueIdProvider.newId());

        when(eventValidator.validate(event))
                .thenReturn(ValidationResult.failed("validation failed"));

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> eventCrudService.create(event))
                .withMessage("Validation failed: Added event not valid");

        verify(eventRepository, times(0))
                .create(event);
    }

    @Test
    void createFailsForDuplicateGuid() {
        var guid = UniqueIdProvider.newId();
        var event = createEvent(guid);

        when(eventRepository.existsByGuid(guid))
                .thenReturn(true);

        assertThatExceptionOfType(EntityAlreadyExistsException.class)
                .isThrownBy(() -> eventCrudService.create(event))
                .withMessage("Event with given guid already exists: " + guid);
    }

    @Test
    void updateWithGuidSucceeds() {
        var event = createEvent(UniqueIdProvider.newId());

        when(eventValidator.validate(event))
                .thenReturn(ValidationResult.success());

        var result = eventCrudService.update(event);

        assertThat(result).isEqualTo(true);
        verify(eventRepository, times(1))
                .update(event);
    }

    @Test
    void updateValidationError() {
        var event = createEvent(UniqueIdProvider.newId());

        when(eventValidator.validate(event))
                .thenReturn(ValidationResult.failed("validation failed"));

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> eventCrudService.update(event))
                .withMessage("Validation failed: Edited event not valid");

        verify(eventRepository, times(0))
                .update(event);
    }

    @Test
    void deleteByGuid() {
        var guid = UniqueIdProvider.newId();
        eventCrudService.deleteByGuid(guid);

        verify(eventRepository, times(1))
                .deleteByGuid(guid);
    }

    @Test
    void findAll() {
        var expectedEvents = List.of(createEvent(UniqueIdProvider.newId()));

        when(eventRepository.findAll())
                .thenReturn(expectedEvents);

        var foundEmployees = eventCrudService.findAll();

        assertThat(foundEmployees).isEqualTo(expectedEvents);
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
                List.of(FOOD_CATEGORY, SPORT_CATEGORY),
                "Location",
                LocalDate.now(),
                LocalTime.now(),
                Duration.ZERO
        );
        event.setGuid(guid);
        return event;
    }
}
