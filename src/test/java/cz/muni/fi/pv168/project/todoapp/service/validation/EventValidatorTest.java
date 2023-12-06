package cz.muni.fi.pv168.project.todoapp.service.validation;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EventValidatorTest {
    private EventValidator eventValidator;

    private List<Category> categories;

    @BeforeEach
    void setUp() {
        eventValidator = new EventValidator();
        categories = List.of(new Category("Social", CategoryColor.RED),
                new Category("Sport", CategoryColor.BLUE),
                new Category("Food", CategoryColor.PINK));
    }

    @Test
    void validEvent() {
        Event newEvent = new Event(false,
                "Tennis",
                List.of(categories.get(0)),
                "Tennis Hall Lužánky",
                LocalDate.of(1999, 10, 10), LocalTime.of(10, 0), Duration.ofMinutes(45));

        var result = eventValidator.validate(newEvent);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void withoutLocation() {
        Event newEvent = new Event(false,
                "Tennis",
                List.of(categories.get(0)),
                "",
                LocalDate.of(1999, 10, 10), LocalTime.of(10, 0), Duration.ofMinutes(45));

        var result = eventValidator.validate(newEvent);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void withoutDateTime() {
        Event newEvent = new Event(true,
                "Tennis",
                List.of(categories.get(0)),
                "Tennis Hall Lužánky",
                null, null, null);

        var result = eventValidator.validate(newEvent);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void locationTooShort() {
        Event newEvent = new Event(false,
                "Tennis",
                List.of(categories.get(0)),
                "T",
                LocalDate.of(1999, 10, 10), LocalTime.of(10, 0), Duration.ofMinutes(45));

        var result = eventValidator.validate(newEvent);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();
    }

    @Test
    void nameTooShort() {
        Event newEvent = new Event(false,
                "T",
                List.of(categories.get(0)),
                "",
                LocalDate.of(1999, 10, 10), LocalTime.of(10, 0), Duration.ofMinutes(45));

        var result = eventValidator.validate(newEvent);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();
    }

    @Test
    void noName() {
        Event newEvent = new Event(false,
                "",
                List.of(categories.get(0)),
                "TennisHall",
                LocalDate.of(1999, 10, 10), LocalTime.of(10, 0), Duration.ofMinutes(45));

        var result = eventValidator.validate(newEvent);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();
    }

    @Test
    void moreCategories() {
        Event newEvent = new Event(false,
                "Tennis",
                List.of(categories.get(0), categories.get(1)),
                "TennisHall",
                LocalDate.of(1999, 10, 10), LocalTime.of(10, 0), Duration.ofMinutes(45));

        var result = eventValidator.validate(newEvent);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void withoutCategories() {
        Event newEvent = new Event(true,
                "Tennis",
                List.of(),
                "TennisHall",
                LocalDate.of(1999, 10, 10), LocalTime.of(10, 0), Duration.ofMinutes(45));

        var result = eventValidator.validate(newEvent);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();
    }

    @Test
    void multipleValidations() {
        Event newEvent = new Event(false,
                "T",
                List.of(),
                "T",
                LocalDate.of(1999, 10, 10), LocalTime.of(10, 0), Duration.ofMinutes(45));

        var result = eventValidator.validate(newEvent);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 3).isTrue();
    }
}
