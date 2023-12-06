package cz.muni.fi.pv168.project.todoapp.service.validation;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.TemplateValidator;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TemplateValidatorTests {

    private TemplateValidator templateValidator;

    private List<Category> categories;

    @BeforeEach
    void setUp() {
        templateValidator = new TemplateValidator();
        categories = List.of(new Category("Social", CategoryColor.RED),
                new Category("Sport", CategoryColor.BLUE),
                new Category("Food", CategoryColor.PINK));
    }

    @Test
    void completeTemplate() {
        var newTemplate = new Template("Tennis training template",
                false,
                "Tennis training",
                List.of(categories.get(0), categories.get(1)),
                "Tennis hall",
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void noEventName() {
        var newTemplate = new Template("Tennis training template",
                true,
                null,
                List.of(categories.get(0), categories.get(1)),
                "Tennis hall",
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void noEventLocation() {
        var newTemplate = new Template("Tennis training template",
                true,
                "Tennis",
                List.of(categories.get(0), categories.get(1)),
                null,
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void noTimeAndDuration() {
        var newTemplate = new Template("Tennis training template",
                false,
                "Tennis",
                List.of(categories.get(0), categories.get(1)),
                "Tennis hall",
                null,
                null);

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void shortTemplateName() {
        var newTemplate = new Template("Te",
                true,
                "Tennis",
                List.of(categories.get(0), categories.get(1)),
                null,
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void templateNameTooShort() {
        var newTemplate = new Template("T",
                true,
                "Tennis",
                List.of(categories.get(0), categories.get(1)),
                "Home",
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();

    }

    @Test
    void TemplateNameTooLong() {
        var newTemplate = new Template("Tennis training template name should never be that long - think about it",
                true,
                "Tennis",
                List.of(categories.get(0), categories.get(1)),
                null,
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();
    }

    @Test
    void shortEventName() {
        var newTemplate = new Template("Template",
                true,
                "Te",
                List.of(categories.get(0), categories.get(1)),
                "Home",
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void eventNameTooShort() {
        var newTemplate = new Template("Template",
                true,
                "t",
                List.of(categories.get(0), categories.get(1)),
                "Home",
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();

    }

    @Test
    void eventNameTooLong() {
        var newTemplate = new Template("Template",
                true,
                "Event Created by this template would be too long to fit in and validate",
                List.of(categories.get(0), categories.get(1)),
                "Home",
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();
    }

    @Test
    void locationNameTooShort() {
        var newTemplate = new Template("Template",
                true,
                "Event name",
                List.of(categories.get(0), categories.get(1)),
                "H",
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 1).isTrue();

    }

    @Test
    void optionalsTooShort() {
        var newTemplate = new Template("Template",
                true,
                "E",
                List.of(categories.get(0), categories.get(1)),
                "H",
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 2).isTrue();
    }

    @Test
    void multipleValidations() {
        var newTemplate = new Template("T",
                true,
                "E",
                List.of(categories.get(0), categories.get(1)),
                "H",
                LocalTime.of(15, 0),
                Duration.ofMinutes(120));

        var result = templateValidator.validate(newTemplate);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getValidationErrors().size() == 3).isTrue();

    }
}
