package cz.muni.fi.pv168.project.todoapp.business.service.validation.common;

import cz.muni.fi.pv168.project.todoapp.business.service.validation.ValidationResult;

public class GuidValidator extends PropertyValidator<String> {
    public GuidValidator(String name) {
        super(name);
    }

    @Override
    public ValidationResult validate(String guid) {
        var result = new ValidationResult();

        if (guid == null) {
            return result;
        }

        if (guid.length() != 36
                && guid.charAt(8) != '-'
                && guid.charAt(13) != '-'
                && guid.charAt(18) != '-'
                && guid.charAt(23) != '-') {
            result.add("'%s' length is not in format XXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX");
        }

        return result;
    }
}
