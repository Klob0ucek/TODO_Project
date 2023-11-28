package cz.muni.fi.pv168.project.todoapp.business.model;

import java.util.Objects;

public class Category extends Entity {
    private String name;
    private CategoryColor color;

    public Category(String name, CategoryColor color) {
        this.name = name;
        this.color = color;
    }

    public Category() {

    }

    public Category(String guid, String name, CategoryColor color) {
        super(guid);
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryColor getColor() {
        return color;
    }

    public void setColor(CategoryColor color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(color, category.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}
