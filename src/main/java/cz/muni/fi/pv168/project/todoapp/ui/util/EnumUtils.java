package cz.muni.fi.pv168.project.todoapp.ui.util;

public class EnumUtils {
    private EnumUtils() {
        // no meant for instantiating
    }

    public static <T extends Enum<T>> String toTitle(
            Enum<T> enumValue
    ) {
        return enumValue.name().charAt(0)
                + enumValue.name().substring(1).toLowerCase();
    }
}
