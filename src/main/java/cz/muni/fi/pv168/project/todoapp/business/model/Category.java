package cz.muni.fi.pv168.project.todoapp.business.model;

public class Category extends Entity {
    private String name;
    private CategoryColor color;

    public Category() {
    }

    public Category(String name, CategoryColor color) {
        this.name = name;
        this.color = color;
    }

    public Category(String guid, String name, CategoryColor color) {
        this(name, color);
        this.guid = guid;
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
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}
