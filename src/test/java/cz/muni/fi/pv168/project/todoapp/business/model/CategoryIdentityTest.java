package cz.muni.fi.pv168.project.todoapp.business.model;

import org.junit.jupiter.api.Test;

public class CategoryIdentityTest {

    /**
     * This is regression test.
     * Two categories are same if they have their guid same.
     * Guid is auto-generated.
     */
    @Test
    void sameValuesDifferentGuid() {
        Category category1 = new Category("Sport", CategoryColor.YELLOW);
        Category category2 = new Category("Sport", CategoryColor.YELLOW);

        assert !category1.equals(category2);
    }

    /**
     * This is regression test.
     * Two categories are same if they have their guid same.
     */
    @Test
    void sameGuidDifferentValues() {
        Category category1 = new Category("guid", "Red", CategoryColor.RED);
        Category category2 = new Category("guid", "Sport", CategoryColor.YELLOW);

        assert category1.equals(category2);
    }

}
