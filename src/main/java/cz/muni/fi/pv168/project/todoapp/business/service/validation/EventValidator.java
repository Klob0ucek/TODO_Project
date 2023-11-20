package cz.muni.fi.pv168.project.todoapp.business.service.validation;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.GuidValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.StringLengthValidator;
import java.util.List;

import static cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator.extracting;

public class EventValidator implements Validator<Event> {

    @Override
    public ValidationResult validate(Event model) {
        var validators = List.of(
                extracting(Event::getName, new StringLengthValidator(2, 150, "Name")),
                extracting(Event::getLocation, new StringLengthValidator(2, 150, "Location")),
                extracting(Event::getGuid, new GuidValidator("Guid"))

        );

        return Validator.compose(validators).validate(model);
    }
}
