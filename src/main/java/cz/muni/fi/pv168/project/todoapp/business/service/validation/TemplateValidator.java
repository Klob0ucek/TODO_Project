package cz.muni.fi.pv168.project.todoapp.business.service.validation;


import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.GuidValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.StringLengthValidator;
import java.util.ArrayList;
import java.util.List;
import static cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator.extracting;

public class TemplateValidator implements Validator<Template> {
    @Override
    public ValidationResult validate(Template model) {
        var validators = new ArrayList<>(List.of(
                extracting(Template::getTemplateName, new StringLengthValidator(2, 60, "Template name")),
                extracting(Template::getGuid, new GuidValidator("Guid"))
        ));

        if (model.getName() != null && !model.getName().isEmpty()) {
            validators.add(extracting(Template::getName, new StringLengthValidator(2, 60, "Event Name")));
        }

        if (model.getLocation() != null && !model.getLocation().isEmpty()) {
            validators.add(extracting(Template::getLocation, new StringLengthValidator(2, 150, "Location")));
        }

        return Validator.compose(validators).validate(model);
    }
}
