package cz.muni.fi.pv168.project.todoapp.ui.renderer;

import cz.muni.fi.pv168.project.todoapp.model.Category;

import java.util.List;

public class CategoryListRenderer {
    public static String renderListCategory(List<Category> categories) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < categories.size(); i++) {
            stringBuilder.append(categories.get(i).getName());
            if (i != categories.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
