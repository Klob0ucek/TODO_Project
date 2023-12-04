package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.ComboBoxRenderer;

import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class CategoryDialog extends EntityDialog<Category> {
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<CategoryColor> categoryColorModel;

    private final Category category = new Category();

    public CategoryDialog(CrudHolder crudHolder) {
        this.categoryColorModel = new DefaultComboBoxModel<>(crudHolder.getAvailableColors());
        addFields();
    }

    public CategoryDialog(Category category, CrudHolder crudHolder) {
        this.categoryColorModel = new DefaultComboBoxModel<>(crudHolder.getAvailableColors());
        addFields();
        makeCopy(category);
        setFields(category);
    }

    private void makeCopy(Category category) {
        this.category.setGuid(category.getGuid());
        this.category.setName(category.getName());
        this.category.setColor(category.getColor());
    }

    private void setFields(Category category) {
        nameField.setText(category.getName());
        categoryColorModel.setSelectedItem(category.getColor());
    }

    private void addFields() {
        add("Name:", nameField);
        var comboBox = new JComboBox<>(categoryColorModel);
        comboBox.setRenderer(new ComboBoxRenderer());
        add("Color:", comboBox);
    }

    @Override
    Category getEntity() {
        category.setName(nameField.getText());
        category.setColor((CategoryColor) categoryColorModel.getSelectedItem());
        return category;
    }
}
