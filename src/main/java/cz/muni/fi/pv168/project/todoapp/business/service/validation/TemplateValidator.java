package cz.muni.fi.pv168.project.todoapp.business.service.validation;


import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.GuidValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.StringLengthValidator;
import java.util.List;
import static cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator.extracting;

public class TemplateValidator implements Validator<Template> {
    @Override
    public ValidationResult validate(Template model) {
        var validators = List.of(
                extracting(Template::getTemplateName, new StringLengthValidator(2, 150, "Template name")),
                extracting(Template::getName, new StringLengthValidator(2, 150, "Event Name")),
                extracting(Template::getLocation, new StringLengthValidator(2, 150, "Location")),
                extracting(Template::getGuid, new GuidValidator("Guid"))
        );

        return Validator.compose(validators).validate(model);
    }
}
