package cz.muni.fi.pv168.project.todoapp.business.service.validation;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.CategoryListValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.GuidValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.common.StringLengthValidator;
import java.util.List;

import static cz.muni.fi.pv168.project.todoapp.business.service.validation.Validator.extracting;


public class CategoryValidator implements Validator<Category> {
    @Override
    public ValidationResult validate(Category model) {
        var validators = List.of(
                extracting(Category::getName, new StringLengthValidator(2, 60, "Name")),
                extracting(Category::getGuid, new GuidValidator("Guid"))
        );

        return Validator.compose(validators).validate(model);
    }
}
