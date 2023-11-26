package cz.muni.fi.pv168.project.todoapp.business.model;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniqueNameProvider {

    // TODO method worth testing
    public static Optional<String> checkAndRename(String name, List<String> existing) {
        if (checkUniqueName(name, existing)) {
            return Optional.empty();
        }
        return Optional.of(getUniqueName(name, existing));
    }

    // TODO method worth testing
    private static String getUniqueName(String name, List<String> library) {
        //TODO Patter does not match
        Pattern pattern = Pattern.compile("^[A-Za-z]+\\(d+\\)$");
        Matcher matcher = pattern.matcher(name);

        if (matcher.matches()) {
            System.out.println("Pattern matched!");
            return name;
        }

        int i = 1;
        String newName = "";
        do {
            newName = name + "(" + i + ")";
            i++;
        } while (!checkUniqueName(newName, library));
        return newName;
    }

    private static boolean checkUniqueName(String name, List<String> existing) {
        return existing.stream().noneMatch(n -> n.equals(name));
    }
}
