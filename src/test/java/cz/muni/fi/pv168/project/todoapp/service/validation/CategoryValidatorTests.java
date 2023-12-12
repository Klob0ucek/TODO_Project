package cz.muni.fi.pv168.project.todoapp.service.validation;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.CategoryValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryValidatorTests {
    private CategoryValidator categoryValidator;

    @BeforeEach
    void setUp() {
        categoryValidator = new CategoryValidator();
    }

    @Test
    void validCategory() {
        var newCategory = new Category("Sport", CategoryColor.PINK);

        var result = categoryValidator.validate(newCategory);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void nameTooShort() {
        var newCategory = new Category("S", CategoryColor.BLUE);

        var result = categoryValidator.validate(newCategory);

        assertThat(result.isValid()).isFalse();
    }

    @Test
    void nullName() {
        var newCategory = new Category(null, CategoryColor.BLUE);

        var result = categoryValidator.validate(newCategory);

        assertThat(result.isValid()).isFalse();
    }

    @Test
    void nameTooLong() {
        var newCategory = new Category("SooooooThisNameIsSoooLongThatItWillNotPassValidationNoINeed60Chars", CategoryColor.BLUE);

        var result = categoryValidator.validate(newCategory);

        assertThat(result.isValid()).isFalse();
    }

    @Test
    void noName() {
        var newCategory = new Category("", CategoryColor.RED);

        var result = categoryValidator.validate(newCategory);

        assertThat(result.isValid()).isFalse();
    }
}
