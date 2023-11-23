package cz.muni.fi.pv168.project.todoapp.business.service.validation.common;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.CategoryValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.ValidationResult;
import java.util.List;

public class CategoryListValidator extends PropertyValidator<List<Category>> {
    public CategoryListValidator(String name) {
        super(name);
    }

    @Override
    public ValidationResult validate(List<Category> categories) {
        var result = new ValidationResult();
        var size = categories.size();

        if (size > 0) {
            result.add("Event or template does not have any categories");
        }

        return result;
    }
}
