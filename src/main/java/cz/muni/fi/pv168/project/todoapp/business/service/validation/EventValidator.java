package cz.muni.fi.pv168.project.todoapp.business.service.validation;

import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.CategoryListValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.GuidValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.StringLengthValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator.extracting;

public class EventValidator implements Validator<Event> {

    @Override
    public ValidationResult validate(Event model) {
        var validators = new ArrayList<>(List.of(
                extracting(Event::getName, new StringLengthValidator(2, 60, "Name")),
                extracting(Event::getCategories, new CategoryListValidator("Category List")),
                extracting(Event::getGuid, new GuidValidator("Guid"))
        ));

        if (model.getLocation() != null && !model.getLocation().isEmpty()) {
            validators.add(extracting(Event::getLocation, new StringLengthValidator(2, 150, "Location")));
        }

        return Validator.compose(validators).validate(model);
    }
}
