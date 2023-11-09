package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudService;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.ColorRowRenderer;

import javax.swing.*;
import java.util.List;

public class CategoryTableModel extends BasicTableModel<Category> {
    public CategoryTableModel(CrudService<Category> crudService) {
        super(crudService);
        columns = List.of(
                Column.readonly("Color", CategoryColor.class, Category::getColor),
                Column.readonly("Name", String.class, Category::getName)
        );
    }

    public void setRowBackgroundColors(JTable table) {
        table.setDefaultRenderer(Object.class, new ColorRowRenderer());
    }

    public List<Category> getCategories() {
        return rows;
    }
}
