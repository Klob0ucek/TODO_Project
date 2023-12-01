package cz.muni.fi.pv168.project.todoapp.ui.renderer;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;

import java.util.List;
import java.util.StringJoiner;

public class CategoryListRenderer {
    public static String renderListCategory(List<Category> categories) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        stringJoiner.setEmptyValue("");
        for (Category category : categories) {
            stringJoiner.add(category.getName());
        }
        return stringJoiner.toString();
    }
}
