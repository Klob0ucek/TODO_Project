package cz.muni.fi.pv168.project.todoapp.business.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniqueNameProvider {

    public static String getUniqueName(String name, List<String> existing) {
        if (checkUniqueName(name, existing)) {
            return name;
        }
        int i = 1;
        String newName = "";

        Pattern pattern = Pattern.compile("^\\w+\\(\\d+\\)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            Pattern lastNumber = Pattern.compile("\\(\\d+\\)$");
            Matcher endMatcher = lastNumber.matcher(name);
            if (endMatcher.find()) {
                String parentheses = endMatcher.group();
                i = Integer.parseInt(parentheses.substring(1, parentheses.length() - 1));
                name = endMatcher.replaceAll("");
            }
        }

        do {
            newName = name + "(" + i + ")";
            i++;
        } while (!checkUniqueName(newName, existing));
        return newName;
    }

    private static boolean checkUniqueName(String name, List<String> existing) {
        return existing.stream().noneMatch(n -> n.equals(name));
    }
}
