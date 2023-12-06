package cz.muni.fi.pv168.project.todoapp.service.validation;

import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.IntervalValidator;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IntervalValidatorTests {

    private IntervalValidator intervalValidator;

    @BeforeEach
    void setUp() {
        intervalValidator = new IntervalValidator();
    }

    @Test
    void validInterval() {
        var newInterval = new Interval("Lesson", "les", Duration.ofMinutes(45));

        var result = intervalValidator.validate(newInterval);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void shortName() {
        var newInterval = new Interval("Le", "lesson", Duration.ofMinutes(45));

        var result = intervalValidator.validate(newInterval);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void nameTooLong() {
        var newInterval = new Interval("Tennis training usually are not 5 hours long but who Im I to judge?",
                "tr", Duration.ofMinutes(300));

        var result = intervalValidator.validate(newInterval);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();
    }

    @Test
    void longValidAbbrev() {
        var newInterval = new Interval("Tennis training",
                "training5h", Duration.ofMinutes(300));

        var result = intervalValidator.validate(newInterval);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void longInvalidAbbrev() {
        var newInterval = new Interval("Tennis training",
                "training5ha", Duration.ofMinutes(300));

        var result = intervalValidator.validate(newInterval);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();
    }

    @Test
    void noAbbrev() {
        var newInterval = new Interval("Tennis training",
                "", Duration.ofMinutes(300));

        var result = intervalValidator.validate(newInterval);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();
    }

    @Test
    void multipleValidations() {
        var newInterval = new Interval("T",
                "", Duration.ofMinutes(300));

        var result = intervalValidator.validate(newInterval);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 2).isTrue();
    }
}
