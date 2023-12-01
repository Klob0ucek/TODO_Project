package cz.muni.fi.pv168.project.todoapp.service.validation;

import cz.muni.fi.pv168.project.todoapp.business.model.UniqueNameProvider;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.StringLengthValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UniqueNameProviderTest {

    private static List<String> library;
    private StringLengthValidator stringLengthValidator;

    @BeforeEach
    void setUp() {
        stringLengthValidator = new StringLengthValidator(2, 10);
        library = List.of("Name", "Name(1)", "Name(2)", "Name(3)",
                "Gap(1)", "Gap(3)", "Gap(4)", "Gap(6)",
                "big", "Far(199)", "TooBig(42069)");
    }


    @Test
    void testNameOne() {
        String newName = UniqueNameProvider.getUniqueName("New", library);

        var result = stringLengthValidator.validate(newName);

        assertThat(result).isEqualTo(ValidationResult.success());
        assertThat(newName).isEqualTo("New");
    }

    @Test
    void testNameTwo() {
        String newName = UniqueNameProvider.getUniqueName("Name", library);

        var result = stringLengthValidator.validate(newName);

        assertThat(result).isEqualTo(ValidationResult.success());
        assertThat(newName).isEqualTo("Name(4)");
    }

    @Test
    void testNameThree() {
        String newName = UniqueNameProvider.getUniqueName("Name(2)", library);

        var result = stringLengthValidator.validate(newName);

        assertThat(result).isEqualTo(ValidationResult.success());
        assertThat(newName).isEqualTo("Name(4)");
    }

    @Test
    void testNameFour() {
        String newName = UniqueNameProvider.getUniqueName("Gap", library);

        var result = stringLengthValidator.validate(newName);

        assertThat(result).isEqualTo(ValidationResult.success());
        assertThat(newName).isEqualTo("Gap");
    }

    @Test
    void testNameFive() {
        String newName = UniqueNameProvider.getUniqueName("Gap(1)", library);

        var result = stringLengthValidator.validate(newName);

        assertThat(result).isEqualTo(ValidationResult.success());
        assertThat(newName).isEqualTo("Gap(2)");
    }

    @Test
    void testNameSix() {
        String newName = UniqueNameProvider.getUniqueName("Gap(3)", library);

        var result = stringLengthValidator.validate(newName);

        assertThat(result).isEqualTo(ValidationResult.success());
        assertThat(newName).isEqualTo("Gap(5)");
    }

    @Test
    void testNameSeven() {
        String newName = UniqueNameProvider.getUniqueName("BIG", library);

        var result = stringLengthValidator.validate(newName);

        assertThat(result).isEqualTo(ValidationResult.success());
        assertThat(newName).isEqualTo("BIG");
    }

    @Test
    void testNameEight() {
        String newName = UniqueNameProvider.getUniqueName("Far(198)", library);

        var result = stringLengthValidator.validate(newName);

        assertThat(result).isEqualTo(ValidationResult.success());
        assertThat(newName).isEqualTo("Far(198)");
    }

    @Test
    void testNameNine() {
        String newName = UniqueNameProvider.getUniqueName("Far(199)", library);

        var result = stringLengthValidator.validate(newName);

        assertThat(result).isEqualTo(ValidationResult.success());
        assertThat(newName).isEqualTo("Far(200)");
    }

    @Test
    void testNameTen() {
        String newName = UniqueNameProvider.getUniqueName("TooBig(42069)", library);

        var result = stringLengthValidator.validate(newName);

        assertThat(result.isValid()).isFalse();
    }

}
