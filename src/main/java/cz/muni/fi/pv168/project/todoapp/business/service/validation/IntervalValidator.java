package cz.muni.fi.pv168.project.todoapp.business.service.validation;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.GuidValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.StringLengthValidator;
import java.util.List;
import static cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator.extracting;

public class IntervalValidator implements Validator<Interval> {
    @Override
    public ValidationResult validate(Interval model) {
        var validators = List.of(
                extracting(Interval::getName, new StringLengthValidator(2, 60, "Name")),
                extracting(Interval::getAbbreviation, new StringLengthValidator(1, 10, "Abbreviation")),
                extracting(Interval::getGuid, new GuidValidator("Guid"))
        );

        return Validator.compose(validators).validate(model);
    }
}
