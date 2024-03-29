package cz.muni.fi.pv168.project.todoapp.business.service.validation.common;

import cz.muni.fi.pv168.project.todoapp.business.service.validation.ValidationResult;

public final class StringLengthValidator extends PropertyValidator<String> {
    private final int min;
    private final int max;

    public StringLengthValidator(int min, int max) {
        this(min, max, null);
    }

    public StringLengthValidator(int min, int max, String name) {
        super(name);
        this.min = min;
        this.max = max;
    }

    @Override
    public ValidationResult validate(String string) {
        var result = new ValidationResult();

        if (string == null) {
            result.add("Name is null");
            return result;
        }

        var length = string.length();
        if (min > length || length > max) {
            result.add("'%s' length is not between %d (inclusive) and %d (inclusive)"
                    .formatted(getName(string), min, max)
            );
        }

        return result;
    }
}
