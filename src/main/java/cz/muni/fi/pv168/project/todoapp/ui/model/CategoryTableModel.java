package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.ColorRowRenderer;

import javax.swing.JTable;

import java.util.List;

public class CategoryTableModel extends BasicTableModel<Category> {
    public CategoryTableModel() {
        columns = List.of(
                Column.editable("Color", CategoryColor.class, Category::getColor, Category::setColor),
                Column.editable("Name", String.class, Category::getName, Category::setName)
        );
        rows.add(new Category("Work", CategoryColor.RED));
        rows.add(new Category("Exercise", CategoryColor.BLUE));
        rows.add(new Category("Work", CategoryColor.PINK));
    }

    public void setRowBackgroundColors(JTable table) {
        table.setDefaultRenderer(Object.class, new ColorRowRenderer());
    }

}
