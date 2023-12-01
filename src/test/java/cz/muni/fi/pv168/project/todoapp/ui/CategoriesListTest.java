package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.CategoryListRenderer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesListTest {
    @Test
    void testThreeCategories() {
        List<Category> categories = List.of(new Category("Sleep", CategoryColor.BLUE),
                new Category("Rodeo", CategoryColor.YELLOW), new Category("Sport", CategoryColor.PINK));

        String result = CategoryListRenderer.renderListCategory(categories);

        assertThat(result).isEqualTo("Sleep, Rodeo, Sport");
    }

    @Test
    void testNoCategories() {
        String result = CategoryListRenderer.renderListCategory(List.of());

        assertThat(result).isEqualTo("no categories - should not be allowed");
    }
}
