package cz.muni.fi.pv168.project.todoapp.business.service.validation;

import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.GuidValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.StringLengthValidator;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GuidValidatorTest {
    private GuidValidator guidValidator;

    @BeforeEach
    void setUp() {
        guidValidator = new GuidValidator("Guid validator");
    }

    @Test
    void validGuid() {
        var guid = "ee4a790c-b614-4c1d-b39e-e9e4dc532de2";

        var result = guidValidator.validate(guid);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void validGuidWithNumbers() {
        var guid = "33665843-7927-4383-8991-682125502633";

        var result = guidValidator.validate(guid);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void guidTooShort() {
        var guid = "3c6accf3-f927-4c83-8ad1-6ee1b5502b3";

        var result = guidValidator.validate(guid);

        assertThat(result.isValid()).isFalse();
    }

    @Test
    void guidTooLong() {
        var guid = "3c6accf3-f927-4c83-8ad1-6ee1b5502b3s6";

        var result = guidValidator.validate(guid);

        assertThat(result.isValid()).isFalse();
    }

    @Test
    void noDash() {
        var guid = "3c6accf3df927f4c83e8ad1a6ee1b5502b33";

        var result = guidValidator.validate(guid);

        assertThat(result.isValid()).isFalse();
    }

    @Test
    void misplacedDash() {
        var guid = "3c6accf3-f927-4c83-8ad16-ee1b5502b33";

        var result = guidValidator.validate(guid);

        assertThat(result.isValid()).isFalse();
    }

    @Test
    void missingDash() {
        var guid = "3c6accf3-f927-4c83-8ad1f6ee1b5502b33";

        var result = guidValidator.validate(guid);

        assertThat(result.isValid()).isFalse();
    }

}
