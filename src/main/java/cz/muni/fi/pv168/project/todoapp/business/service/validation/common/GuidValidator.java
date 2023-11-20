package cz.muni.fi.pv168.project.todoapp.business.service.validation.common;

import cz.muni.fi.pv168.project.todoapp.business.service.validation.ValidationResult;

public class GuidValidator extends PropertyValidator<String> {
    public GuidValidator(String name) {
        super(name);
    }

    @Override
    public ValidationResult validate(String string) {
        var result = new ValidationResult();
        var length = string.length();


        if (length != 36
                && string.charAt(8) != '-' &&
                string.charAt(13) == '-'
                && string.charAt(18) == '-'
                && string.charAt(23) == '-') {
            result.add("'%s' length is not in format XXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX");
        }

        return result;
    }
}
